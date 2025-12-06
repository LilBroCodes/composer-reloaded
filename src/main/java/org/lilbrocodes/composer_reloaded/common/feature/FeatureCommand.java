package org.lilbrocodes.composer_reloaded.common.feature;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.commands.ComposerCommand;
import org.lilbrocodes.composer_reloaded.api.feature.Features;
import org.lilbrocodes.composer_reloaded.api.feature.state.FeatureState;
import org.lilbrocodes.composer_reloaded.api.runtime.ServerHolder;

import java.util.concurrent.CompletableFuture;

public class FeatureCommand extends ComposerCommand {

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher,
                         CommandRegistryAccess registryAccess,
                         CommandManager.RegistrationEnvironment env) {

        dispatcher.register(
                CommandManager.literal("feature")
                        .requires(src -> src.hasPermissionLevel(2))
                        .then(CommandManager.argument("mod", StringArgumentType.word())
                                .suggests(this::mods)
                                .then(CommandManager.literal("enable").then(feature(this::enable)))
                                .then(CommandManager.literal("disable").then(feature(this::disable)))
                        )
        );
    }

    @Override
    protected Text buildPrefix() {
        return wrapBrackets(createGradient("Composer", 0xffaa00, 0xffff55));
    }

    private CompletableFuture<Suggestions> mods(CommandContext<ServerCommandSource> ctx, SuggestionsBuilder builder) {
        Features.getInstance().getAll().keySet().forEach(id -> builder.suggest(id.getNamespace()));
        return builder.buildFuture();
    }

    private CompletableFuture<Suggestions> features(CommandContext<ServerCommandSource> ctx, SuggestionsBuilder builder) {
        String mod = ctx.getArgument("mod", String.class);
        Features.getInstance().getAll().keySet().stream()
                .filter(id -> id.getNamespace().equals(mod))
                .map(Identifier::getPath)
                .forEach(builder::suggest);
        return builder.buildFuture();
    }

    private ArgumentBuilder<ServerCommandSource, ?> feature(Command<ServerCommandSource> exec) {
        return CommandManager.argument("feature", StringArgumentType.word())
                .suggests(this::features)
                .executes(exec);
    }

    private Identifier resolveFeatureId(CommandContext<ServerCommandSource> ctx) {
        String mod = ctx.getArgument("mod", String.class);
        String feat = ctx.getArgument("feature", String.class);
        return new Identifier(mod, feat);
    }

    private int enable(CommandContext<ServerCommandSource> ctx) {
        Identifier id = resolveFeatureId(ctx);
        FeatureState state = ServerHolder.features();

        if (!state.states.containsKey(id.toString())) {
            ctx.getSource().sendError(Text.literal("Unknown feature: " + id));
            return 0;
        }

        state.states.get(id.toString()).set(true);
        state.markDirty();

        return success(ctx, Text.literal("Enabled feature ").append(Text.literal(id.toString())));
    }

    private int disable(CommandContext<ServerCommandSource> ctx) {
        Identifier id = resolveFeatureId(ctx);
        FeatureState state = ServerHolder.features();

        if (!state.states.containsKey(id.toString())) {
            ctx.getSource().sendError(Text.literal("Unknown feature: " + id));
            return 0;
        }

        state.states.get(id.toString()).set(false);
        state.markDirty();

        error(ctx, Text.literal("Disabled feature ").append(Text.literal(id.toString())));
        return 1;
    }
}

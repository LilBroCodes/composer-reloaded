package org.lilbrocodes.composer_reloaded.api.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public abstract class ComposerCommand implements CommandRegistrationCallback {
    public int feedback(CommandContext<ServerCommandSource> src, Text message, boolean success) {
        src.getSource().sendFeedback(() -> appendPrefix(message), false);
        return success ? 1 : 0;
    }

    public int feedback(CommandContext<ServerCommandSource> src, Text message) {
        return feedback(src, message, true);
    }

    public int feedback(PlayerEntity player, Text message, boolean success) {
        player.sendMessage(appendPrefix(message), false);
        return success ? 1 : 0;
    }

    public int feedback(PlayerEntity player, Text message) {
        return feedback(player, message, true);
    }

    public int error(CommandContext<ServerCommandSource> src, Text message) {
        return feedback(src, message.copy().styled(style -> style.withColor(errorColor())), false);
    }

    public int error(PlayerEntity player, Text message) {
        return feedback(player, message.copy().styled(style -> style.withColor(errorColor())), false);
    }

    public int info(CommandContext<ServerCommandSource> src, Text msg) {
        src.getSource().sendFeedback(() -> msg, false);
        return 1;
    }

    public int info(PlayerEntity player, Text msg) {
        player.sendMessage(msg, false);
        return 1;
    }

    public void broadcast(ServerCommandSource source, Text message) {
        source.getServer().getPlayerManager().broadcast(appendPrefix(message), false);
    }

    public Text styled(Text base, java.util.function.UnaryOperator<Style> modifier) {
        return base.copy().styled(modifier);
    }

    public Text colored(String msg, int color) {
        return Text.literal(msg).styled(s -> s.withColor(color));
    }

    public Text success(String msg) {
        return colored(msg, successColor());
    }

    public Text warning(String msg) {
        return colored(msg, warnColor());
    }

    public Text error(String msg) {
        return colored(msg, errorColor());
    }

    public Text appendPrefix(Text text) {
        return buildPrefix().copy().append(ScreenTexts.space()).append(text);
    }

    public Text wrapBrackets(Text text) {
        return wrapBrackets(text, bracketsColor());
    }

    public Text wrapBrackets(Text text, int color) {
        return Text.literal("")
                .append(Text.literal("[").setStyle(Style.EMPTY.withColor(color)))
                .append(text)
                .append(Text.literal("]").setStyle(Style.EMPTY.withColor(color)));
    }

    public Text boolText(boolean value) {
        return value ? colored("true", successColor()) : colored("false", errorColor());
    }

    protected int errorColor() {
        return 0xFF5555;
    }

    protected int warnColor() {
        return 0xFFFF55;
    }

    protected int successColor() {
        return 0x55FF55;
    }

    protected int bracketsColor() {
        return 0xAAAAAA;
    }

    protected abstract Text buildPrefix();
}

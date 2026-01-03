package org.lilbrocodes.composer_reloaded.api.v1.util.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import org.lilbrocodes.composer_reloaded.api.v1.overlay.impl.AnimatedOverlay;

public class AnimatedOverlayAnimationArgumentType extends EnumArgumentType<AnimatedOverlay.Animation> {
    protected AnimatedOverlayAnimationArgumentType() {
        super(AnimatedOverlay.Animation.CODEC, AnimatedOverlay.Animation::values);
    }

    public static AnimatedOverlayAnimationArgumentType all() {
        return new AnimatedOverlayAnimationArgumentType();
    }

    public static AnimatedOverlay.Animation getAnchor(CommandContext<ServerCommandSource> ctx, String name) {
        return ctx.getArgument(name, AnimatedOverlay.Animation.class);
    }
}

package com.codex.composer.api.v1.util.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import com.codex.composer.api.v1.overlay.impl.Overlay;

public class OverlayAnchorArgumentType extends EnumArgumentType<Overlay.Anchor> {
    protected OverlayAnchorArgumentType() {
        super(Overlay.Anchor.CODEC, Overlay.Anchor::values);
    }

    public static OverlayAnchorArgumentType all() {
        return new OverlayAnchorArgumentType();
    }

    public static Overlay.Anchor getAnchor(CommandContext<ServerCommandSource> ctx, String name) {
        return ctx.getArgument(name, Overlay.Anchor.class);
    }
}

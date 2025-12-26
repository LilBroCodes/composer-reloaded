package org.lilbrocodes.composer_reloaded.api.v1.util.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import org.lilbrocodes.composer_reloaded.api.v1.toast.ToastManager;

public class ToastCornerArgumentType extends EnumArgumentType<ToastManager.Corner> {
    protected ToastCornerArgumentType() {
        super(ToastManager.Corner.CODEC, ToastManager.Corner::values);
    }

    public static ToastCornerArgumentType corners() {
        return new ToastCornerArgumentType();
    }

    public static ToastManager.Corner getCorner(CommandContext<ServerCommandSource> ctx, String name) {
        return ctx.getArgument(name, ToastManager.Corner.class);
    }
}

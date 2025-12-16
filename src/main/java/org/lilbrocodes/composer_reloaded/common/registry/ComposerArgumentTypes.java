package org.lilbrocodes.composer_reloaded.common.registry;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.util.command.ColorArgumentType;
import org.lilbrocodes.composer_reloaded.api.util.command.ToastCornerArgumentType;

public class ComposerArgumentTypes {
    public static final Identifier COLOR = ComposerReloaded.identify("color");
    public static final Identifier TOAST_CORNER = ComposerReloaded.identify("toast_corner");

    public static void initialize() {
        ArgumentTypeRegistry.registerArgumentType(COLOR, ColorArgumentType.class, ConstantArgumentSerializer.of(ColorArgumentType::color));
        ArgumentTypeRegistry.registerArgumentType(TOAST_CORNER, ToastCornerArgumentType.class, ConstantArgumentSerializer.of(ToastCornerArgumentType::corners));
    }
}

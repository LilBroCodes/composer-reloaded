package org.lilbrocodes.composer_reloaded.internal.registry;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.v1.util.command.AnimatedOverlayAnimationArgumentType;
import org.lilbrocodes.composer_reloaded.api.v1.util.command.ColorArgumentType;
import org.lilbrocodes.composer_reloaded.api.v1.util.command.OverlayAnchorArgumentType;
import org.lilbrocodes.composer_reloaded.api.v1.util.command.ToastCornerArgumentType;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModArgumentTypes {
    public static final Identifier COLOR = ComposerReloaded.identify("color");
    public static final Identifier TOAST_CORNER = ComposerReloaded.identify("toast_corner");
    public static final Identifier OVERLAY_ANCHOR = ComposerReloaded.identify("overlay_anchor");
    public static final Identifier ANIMATED_OVERLAY_ANIMATION = ComposerReloaded.identify("animated_overlay_animation");

    public static void initialize() {
        ArgumentTypeRegistry.registerArgumentType(COLOR, ColorArgumentType.class, ColorArgumentType.Serializer.INSTANCE);
        ArgumentTypeRegistry.registerArgumentType(TOAST_CORNER, ToastCornerArgumentType.class, ConstantArgumentSerializer.of(ToastCornerArgumentType::corners));
        ArgumentTypeRegistry.registerArgumentType(OVERLAY_ANCHOR, OverlayAnchorArgumentType.class, ConstantArgumentSerializer.of(OverlayAnchorArgumentType::all));
        ArgumentTypeRegistry.registerArgumentType(ANIMATED_OVERLAY_ANIMATION, AnimatedOverlayAnimationArgumentType.class, ConstantArgumentSerializer.of(AnimatedOverlayAnimationArgumentType::all));
    }
}

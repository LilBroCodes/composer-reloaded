package org.lilbrocodes.composer_reloaded.mixin.impl.duped_binds;

import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.client.config.ComposerConfig;
import org.lilbrocodes.composer_reloaded.client.duped_binds.BindTracker;
import org.lilbrocodes.composer_reloaded.client.duped_binds.RainbowColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ControlsListWidget.KeyBindingEntry.class)
public abstract class RecolorKeybindMixin {
    @Shadow @Final private KeyBinding binding;

    @Shadow protected abstract void update();

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"), index = 4)
    private int flowed_combat$recolorBinding(int value) {
        if (!ComposerReloaded.dupedBinds()) return value;
        if (BindTracker.bindAllowed(binding)) {
            RainbowColor.stepColor();
            this.update();
            return ComposerConfig.INSTANCE.rainbowDuplicateKeybinds ?
                    RainbowColor.currentColor :
                    Formatting.AQUA.getColorValue() == null ? 0xFF000000 : Formatting.AQUA.getColorValue();
        } else {
            return value;
        }
    }

    @ModifyArg(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;setMessage(Lnet/minecraft/text/Text;)V", ordinal = 1))
    private Text flowed_combat$updateBindingColor(Text value) {
        if (!ComposerReloaded.dupedBinds()) return value;
        if (BindTracker.bindAllowed(binding)) {
            return ComposerConfig.INSTANCE.rainbowDuplicateKeybinds ?
                    binding.getBoundKeyLocalizedText().copy().setStyle(Style.EMPTY.withColor(RainbowColor.currentColor)) :
                    binding.getBoundKeyLocalizedText().copy().formatted(Formatting.AQUA);
        } else {
            return value;
        }
    }
}

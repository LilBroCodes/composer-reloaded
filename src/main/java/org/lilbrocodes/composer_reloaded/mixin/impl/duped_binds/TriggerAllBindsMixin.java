package org.lilbrocodes.composer_reloaded.mixin.impl.duped_binds;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.client.duped_binds.BindTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class TriggerAllBindsMixin {
    @Inject(method = "onKeyPressed", at = @At("TAIL"))
    private static void flowed_combat$triggerOtherBinds(InputUtil.Key key, CallbackInfo ci, @Local KeyBinding keyBinding) {
        if (!ComposerReloaded.dupedBinds()) return;
        if (BindTracker.bindAllowed(keyBinding)) {
            for (KeyBinding keyBinding2 : MinecraftClient.getInstance().options.allKeys) {
                if (keyBinding != keyBinding2 && keyBinding.equals(keyBinding2) && BindTracker.bindAllowed(keyBinding2)) keyBinding2.timesPressed++;
            }
        }
    }

    @Inject(method = "setKeyPressed", at = @At("TAIL"))
    private static void flowed_combat$toggleOtherBinds(InputUtil.Key key, boolean pressed, CallbackInfo ci, @Local KeyBinding keyBinding) {
        if (!ComposerReloaded.dupedBinds()) return;
        if (BindTracker.bindAllowed(keyBinding)) {
            for (KeyBinding keyBinding2 : MinecraftClient.getInstance().options.allKeys) {
                if (keyBinding != keyBinding2 && keyBinding.equals(keyBinding2) && BindTracker.bindAllowed(keyBinding2)) keyBinding2.setPressed(pressed);
            }
        }
    }
}

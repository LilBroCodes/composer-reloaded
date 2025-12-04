package org.lilbrocodes.composer_reloaded.mixin.impl.datafix;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.lilbrocodes.composer_reloaded.api.datafix.DataFixerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "fromNbt", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private static void composerReloaded$runDataFixers(NbtCompound tag, CallbackInfoReturnable<ItemStack> cir) {
        if (tag.contains("id", NbtElement.STRING_TYPE)) {
            for (DataFixerRegistry.Item fixer : DataFixerRegistry.ITEM.getAll().values()) {
                Optional<ItemStack> opt = fixer.process(tag);

                if (opt.isPresent()) {
                    cir.setReturnValue(opt.get());
                    return;
                }
            }
        }
    }
}

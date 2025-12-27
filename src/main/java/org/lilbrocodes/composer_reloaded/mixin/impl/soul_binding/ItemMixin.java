package org.lilbrocodes.composer_reloaded.mixin.impl.soul_binding;

import net.minecraft.item.Item;
import org.lilbrocodes.composer_reloaded.mixin.accessor.ItemMethodAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

//? if minecraft: <=1.20.1 {
/*import org.lilbrocodes.composer_reloaded.api.v1.item.settings.ComposerItemSettings;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///? }

@SuppressWarnings("FieldCanBeLocal")
@Mixin(Item.class)
public class ItemMixin implements ItemMethodAccessor {
    @Unique private boolean soulbound = false;
    @Unique private boolean soulboundCanDrop = false;

    //? if minecraft: <=1.20.1 {
    /*@Inject(method = "<init>", at = @At("TAIL"))
    private void composerReloaded$settingsImpl(Item.Settings settings, CallbackInfo ci) {
        if (settings instanceof ComposerItemSettings c) {
            soulbound = c.soulbound;
            soulboundCanDrop = c.soulboundCanDrop;
        }
    }
    *///? }

    @Override
    public boolean composerReloaded$soulbound() {
        return soulbound;
    }

    @Override
    public boolean composerReloaded$soulboundCanDrop() {
        return soulboundCanDrop;
    }
}

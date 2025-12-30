package org.lilbrocodes.composer_reloaded.api.v1.item.settings;

import net.minecraft.item.Item;

//? if minecraft: >=1.20.6
import org.lilbrocodes.composer_reloaded.internal.registry.ModDataComponentTypes;

public class ComposerItemSettings extends Item.Settings {
    //? if minecraft: <=1.20.4 {
    /*public boolean soulbound = false;
    public boolean soulboundCanDrop = false;
    *///? }

    public ComposerItemSettings soulbound(boolean canDrop) {
        //? if minecraft: >=1.20.6 {
        component(ModDataComponentTypes.SOULBOUND, true);
        component(ModDataComponentTypes.SOULBOUND_CAN_DROP, canDrop);
        //? } else {
        /*soulbound = true;
        soulboundCanDrop = canDrop;
        *///? }
        return this;
    }

    public ComposerItemSettings soulbound() {
        return soulbound(true);
    }
}

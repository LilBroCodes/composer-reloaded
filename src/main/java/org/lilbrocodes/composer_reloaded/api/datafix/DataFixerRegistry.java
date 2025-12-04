package org.lilbrocodes.composer_reloaded.api.datafix;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.ApiStatus;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.util.misc.AbstractPseudoRegistry;

import java.util.Optional;

public class DataFixerRegistry {
    public static final AbstractPseudoRegistry<Item> ITEM = new AbstractPseudoRegistry.Impl<>();

    @ApiStatus.Internal
    public static void initialize() {
        AbstractPseudoRegistry.identify(ComposerReloaded.identify("datafixer_items"), ITEM);
    }

    @FunctionalInterface
    public interface Item {
        Optional<ItemStack> process(String id, NbtCompound tag);

        default Optional<ItemStack> process(NbtCompound raw) {
            return process(raw.getString("id"), raw);
        }
    }
}

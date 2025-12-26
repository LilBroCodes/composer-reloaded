package org.lilbrocodes.composer_reloaded.api.datafix;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.lilbrocodes.composer_reloaded.api.datafix.impl.SimpleItemFixer;
import org.lilbrocodes.composer_reloaded.api.util.misc.AbstractPseudoRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

import java.util.Optional;

import static org.lilbrocodes.composer_reloaded.internal.ComposerReloaded.*;

public class DataFixerRegistry {
    public static final AbstractPseudoRegistry<Item> ITEM = new AbstractPseudoRegistry.Impl<>() {
        @Override
        public Item register(Identifier id, Item value) {
            //? if minecraft: >=1.21.4
            //if (value instanceof SimpleItemFixer simple && simple.copyNbt()) LOGGER.warn("Copying raw NBT in a simple data fixer does not work on 1.21.4. Consider using a custom data fixer to copy components manually, or disable copyNbt!");
            return super.register(id, value);
        }
    };

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

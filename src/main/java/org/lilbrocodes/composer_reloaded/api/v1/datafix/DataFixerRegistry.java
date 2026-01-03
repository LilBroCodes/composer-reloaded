package org.lilbrocodes.composer_reloaded.api.v1.datafix;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.AbstractPseudoRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

import java.util.Optional;

//? minecraft: >=1.20.6 {
import org.lilbrocodes.composer_reloaded.api.v1.datafix.impl.SimpleItemFixer;
import static org.lilbrocodes.composer_reloaded.internal.ComposerReloaded.LOGGER;
//? }

public class DataFixerRegistry {
    public static final AbstractPseudoRegistry<Item> ITEM = new AbstractPseudoRegistry.Impl<>() {
        @Override
        protected void bootstrap() {
            identify(ComposerReloaded.identify("item_data_fixers"), this);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Item register(Identifier id, Item value) {
            //? if minecraft: >=1.20.6
            if (value instanceof SimpleItemFixer simple && simple.copyNbt()) LOGGER.warn("Copying raw NBT in a simple data fixer does not work on 1.21.4. Consider using a custom data fixer to copy components manually, or disable copyNbt!");
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

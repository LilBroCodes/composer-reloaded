package org.lilbrocodes.composer_reloaded.easytags;

import net.minecraft.nbt.NbtCompound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.v1.easytags.manager.ItemStackDataManager;
import org.lilbrocodes.composer_reloaded.api.v1.nbt.NbtSerializable;

import static org.junit.jupiter.api.Assertions.*;

class ItemStackDataManagerTest {
    static class TestData implements NbtSerializable<TestData> {
        final int value;

        TestData(int value) {
            this.value = value;
        }

        TestData(NbtCompound tag) {
            this(tag.getInt("value"));
        }

        @Override
        public NbtCompound writeNbt(NbtCompound tag) {
            tag.putInt("value", value);
            return tag;
        }
    }

    @BeforeEach
    void setup() {
        ItemStackDataManager.register(TestData.class, TestData::new);
    }

    @Test
    void testSaveAndGet() {
        NbtCompound tag = new NbtCompound();
        TestData data = new TestData(42);

        ItemStackDataManager.save(tag, data);
        TestData retrieved = ItemStackDataManager.get(tag, TestData.class);

        assertNotNull(retrieved);
        assertEquals(42, retrieved.value);
    }

    @Test
    void testGetEmptyReturnsNull() {
        NbtCompound tag = new NbtCompound();
        TestData retrieved = ItemStackDataManager.get(tag, TestData.class);
        assertNull(retrieved);
    }
}
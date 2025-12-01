package org.lilbrocodes.composer_reloaded.easytags;

import net.minecraft.nbt.NbtCompound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.easytags.manager.ItemStackDataManager;
import org.lilbrocodes.composer_reloaded.api.nbt.BasicNbtContainer;
import org.lilbrocodes.composer_reloaded.api.nbt.NbtSerializable;

import static org.junit.jupiter.api.Assertions.*;

class ItemStackDataManagerTest {
    static class TestData implements NbtSerializable<TestData> {
        int value;

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
        ItemStackDataManager.register(TestData.class, tag -> new TestData(tag.getInt("value")));
    }

    @Test
    void testSaveAndGet() {
        BasicNbtContainer provider = new BasicNbtContainer();
        TestData data = new TestData(42);

        ItemStackDataManager.save(provider, data);
        TestData retrieved = ItemStackDataManager.get(provider, TestData.class);

        assertNotNull(retrieved);
        assertEquals(42, retrieved.value);
    }

    @Test
    void testGetEmptyReturnsNull() {
        BasicNbtContainer provider = new BasicNbtContainer();
        TestData retrieved = ItemStackDataManager.get(provider, TestData.class);
        assertNull(retrieved);
    }
}
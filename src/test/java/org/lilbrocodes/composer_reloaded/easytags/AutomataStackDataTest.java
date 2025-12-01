package org.lilbrocodes.composer_reloaded.easytags;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.easytags.annotation.Serialize;
import org.lilbrocodes.composer_reloaded.api.easytags.automata.AutomataStackData;
import org.lilbrocodes.composer_reloaded.api.easytags.impl.DefaultSerializers;
import org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializable;
import org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializers;
import org.lilbrocodes.composer_reloaded.api.nbt.ComposerCompound;

import static org.junit.jupiter.api.Assertions.*;

class AutomataStackDataTest {

    static class TestData extends AutomataStackData<TestData> {
        @Serialize
        public int value;

        @Serialize(key = "custom")
        public String text;
    }

    @BeforeAll
    static void initSerializers() {
        DefaultSerializers.initialize();
        AutomataSerializers.register(String.class, new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, String value) {
                tag.putString(key, value);
            }

            @Override
            public String read(ComposerCompound tag, String key) {
                return tag.getString(key);
            }
        });
    }

    @Test
    void testWriteAndReadNbt() {
        TestData data = new TestData();
        data.value = 100;
        data.text = "hello";

        ComposerCompound tag = new ComposerCompound();
        data.writeNbt(tag);

        TestData read = new TestData();
        read.readNbt(tag);

        assertEquals(100, read.value);
        assertEquals("hello", read.text);
    }

    @Test
    void testSkippedNulls() {
        TestData data = new TestData();
        data.text = null;
        data.value = 50;

        ComposerCompound tag = new ComposerCompound();
        data.writeNbt(tag);

        assertTrue(tag.contains("value"));
        assertFalse(tag.contains("custom"));
    }
}

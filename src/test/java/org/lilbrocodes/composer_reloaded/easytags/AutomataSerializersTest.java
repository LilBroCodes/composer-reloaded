package org.lilbrocodes.composer_reloaded.easytags;

import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.easytags.exception.AutomataSerializationException;
import org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializable;
import org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializers;
import org.lilbrocodes.composer_reloaded.api.nbt.ComposerCompound;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AutomataSerializersTest {

    static class TestClass {
        final String value;

        TestClass(String value) {
            this.value = value;
        }
    }

    @Test
    void testRegisterAndGet() {
        AutomataSerializable<TestClass> serializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, TestClass value) {
                tag.putString(key, value.value);
            }

            @Override
            public TestClass read(ComposerCompound tag, String key) {
                return new TestClass(tag.getString(key));
            }
        };

        AutomataSerializers.register(TestClass.class, serializer);
        AutomataSerializable<TestClass> retrieved = AutomataSerializers.get(TestClass.class);
        assertSame(serializer, retrieved);
    }

    @Test
    void testGetUnregisteredThrows() {
        assertThrows(AutomataSerializationException.class, () -> AutomataSerializers.get(String.class));
    }
}

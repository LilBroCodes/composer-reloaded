package org.lilbrocodes.composer_reloaded.easytags;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.easytags.impl.DefaultSerializers;
import org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializers;
import org.lilbrocodes.composer_reloaded.api.nbt.ComposerCompound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultSerializersTest {

    @BeforeAll
    static void setup() {
        DefaultSerializers.initialize();
    }

    @Test
    void testIntegerSerialization() {
        ComposerCompound tag = new ComposerCompound();
        var serializer = AutomataSerializers.get(Integer.class);
        serializer.write(tag, "value", 123);
        int result = serializer.read(tag, "value");
        assertEquals(123, result);
    }

    @Test
    void testBooleanSerialization() {
        ComposerCompound tag = new ComposerCompound();
        var serializer = AutomataSerializers.get(Boolean.class);
        serializer.write(tag, "flag", true);
        boolean result = serializer.read(tag, "flag");
        assertTrue(result);
    }

    @Test
    void testCharacterSerialization() {
        ComposerCompound tag = new ComposerCompound();
        var serializer = AutomataSerializers.get(Character.class);
        serializer.write(tag, "char", 'x');
        char result = serializer.read(tag, "char");
        assertEquals('x', result);
    }

}


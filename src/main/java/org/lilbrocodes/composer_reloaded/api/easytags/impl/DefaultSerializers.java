package org.lilbrocodes.composer_reloaded.api.easytags.impl;

import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.ApiStatus;
import org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializable;
import org.lilbrocodes.composer_reloaded.api.nbt.ComposerCompound;
import org.lilbrocodes.composer_reloaded.api.util.data.SerializableIdentifier;

import static org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializers.register;

@SuppressWarnings("FeatureEnvy")
public class DefaultSerializers {
    @ApiStatus.Internal
    public static void initialize() {
        AutomataSerializable<Integer> intSerializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Integer value) {
                tag.putInt(key, value);
            }

            @Override
            public Integer read(ComposerCompound tag, String key) {
                return tag.getInt(key);
            }
        };
        register(Integer.class, intSerializer);
        register(int.class, intSerializer);

        AutomataSerializable<Long> longSerializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Long value) {
                tag.putLong(key, value);
            }

            @Override
            public Long read(ComposerCompound tag, String key) {
                return tag.getLong(key);
            }
        };
        register(Long.class, longSerializer);
        register(long.class, longSerializer);

        AutomataSerializable<Float> floatSerializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Float value) {
                tag.putFloat(key, value);
            }

            @Override
            public Float read(ComposerCompound tag, String key) {
                return tag.getFloat(key);
            }
        };
        register(Float.class, floatSerializer);
        register(float.class, floatSerializer);

        AutomataSerializable<Double> doubleSerializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Double value) {
                tag.putDouble(key, value);
            }

            @Override
            public Double read(ComposerCompound tag, String key) {
                return tag.getDouble(key);
            }
        };
        register(Double.class, doubleSerializer);
        register(double.class, doubleSerializer);

        AutomataSerializable<Boolean> booleanSerializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Boolean value) {
                tag.putBoolean(key, value);
            }

            @Override
            public Boolean read(ComposerCompound tag, String key) {
                return tag.getBoolean(key);
            }
        };
        register(Boolean.class, booleanSerializer);
        register(boolean.class, booleanSerializer);

        AutomataSerializable<Short> shortSerializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Short value) {
                tag.putShort(key, value);
            }

            @Override
            public Short read(ComposerCompound tag, String key) {
                return tag.getShort(key);
            }
        };
        register(Short.class, shortSerializer);
        register(short.class, shortSerializer);

        AutomataSerializable<Byte> byteSerializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Byte value) {
                tag.putByte(key, value);
            }

            @Override
            public Byte read(ComposerCompound tag, String key) {
                return tag.getByte(key);
            }
        };
        register(Byte.class, byteSerializer);
        register(byte.class, byteSerializer);

        AutomataSerializable<Character> charSerializer = new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Character value) {
                tag.putString(key, value.toString());
            }

            @Override
            public Character read(ComposerCompound tag, String key) {
                String s = tag.getString(key);
                if (s.isEmpty()) return '\0';
                return s.charAt(0);
            }
        };
        register(Character.class, charSerializer);
        register(char.class, charSerializer);

        register(SerializableIdentifier.class, new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, SerializableIdentifier value) {
                tag.put(key, value.writeNbt());
            }

            @Override
            public SerializableIdentifier read(ComposerCompound tag, String key) {
                return new SerializableIdentifier(tag.getCompound(key));
            }
        });

        register(ComposerCompound.class, new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, ComposerCompound value) {
                tag.put(key, value);
            }

            @Override
            public ComposerCompound read(ComposerCompound tag, String key) {
                return ComposerCompound.copy(tag.getCompound(key));
            }
        });

        register(NbtCompound.class, new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, NbtCompound value) {
                tag.put(key, value);
            }

            @Override
            public NbtCompound read(ComposerCompound tag, String key) {
                return tag.getCompound(key);
            }
        });
    }
}

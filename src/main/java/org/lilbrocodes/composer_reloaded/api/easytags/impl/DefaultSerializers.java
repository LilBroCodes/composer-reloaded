package org.lilbrocodes.composer_reloaded.api.easytags.impl;

import org.jetbrains.annotations.ApiStatus;
import org.lilbrocodes.composer_reloaded.api.easytags.exception.AutomataSerializationException;
import org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializable;
import org.lilbrocodes.composer_reloaded.api.nbt.ComposerCompound;
import org.lilbrocodes.composer_reloaded.api.nbt.NbtSerializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializers.get;
import static org.lilbrocodes.composer_reloaded.api.easytags.registry.AutomataSerializers.register;

@SuppressWarnings("unchecked")
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

        register(Object.class, new AutomataSerializable<>() {
            @Override
            public void write(ComposerCompound tag, String key, Object value) {
                if (value == null) return;
                AutomataSerializable<Object> serializer = (AutomataSerializable<Object>) get(value.getClass());
                serializer.write(tag, key, value);
            }

            @Override
            public Object read(ComposerCompound tag, String key) {
                throw new AutomataSerializationException("Cannot deserialize Object without a type hint. Use a typed field or generic collection serializer.");
            }
        });

        register((Class) List.class, new AutomataSerializable<List<?>>() {
            @Override
            public void write(ComposerCompound tag, String key, List<?> value) {
                if (value == null) return;
                for (Object elem : value) {
                    if (!(elem instanceof NbtSerializable<?> ns)) {
                        throw new AutomataSerializationException("List element not NbtSerializable: " + elem);
                    }
                }
                tag.putList(key, (List<? extends NbtSerializable<?>>) value);
            }

            @Override
            public List<?> read(ComposerCompound tag, String key) {
                throw new AutomataSerializationException("Cannot deserialize raw List without type hint");
            }
        });

        register((Class) Set.class, new AutomataSerializable<Set<? extends NbtSerializable<?>>>() {
            @Override
            public void write(ComposerCompound tag, String key, Set<? extends NbtSerializable<?>> value) {
                if (value == null) return;
                tag.putList(key, new ArrayList<>(value));
            }

            @Override
            public Set<? extends NbtSerializable<?>> read(ComposerCompound tag, String key) {
                throw new AutomataSerializationException(
                        "Cannot deserialize raw Set without type hint. Use a typed field or TypedSetSerializer."
                );
            }
        });

        register((Class) Map.class, new AutomataSerializable<Map<String, ? extends NbtSerializable<?>>>() {
            @Override
            public void write(ComposerCompound tag, String key, Map<String, ? extends NbtSerializable<?>> value) {
                if (value == null) return;
                tag.putMap(key, value);
            }

            @Override
            public Map<String, ? extends NbtSerializable<?>> read(ComposerCompound tag, String key) {
                throw new AutomataSerializationException(
                        "Cannot deserialize raw Map without type hint. Use a typed field or TypedMapSerializer."
                );
            }
        });
    }
}

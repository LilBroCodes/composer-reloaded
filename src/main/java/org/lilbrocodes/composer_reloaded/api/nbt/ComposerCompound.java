package org.lilbrocodes.composer_reloaded.api.nbt;

import com.google.common.collect.Maps;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.*;
import java.util.function.Function;

public class ComposerCompound extends NbtCompound implements Cloneable {
    public ComposerCompound(Map<String, NbtElement> entries) {
        super(entries);
    }

    public ComposerCompound() {
        this(Maps.newHashMap());
    }

    public static ComposerCompound copy(NbtCompound tag) {
        ComposerCompound nbt = new ComposerCompound();
        tag.getKeys().forEach(key -> nbt.put(key, tag.get(key)));
        return nbt;
    }

    @Override
    public ComposerCompound clone() {
        try {
            ComposerCompound clone = (ComposerCompound) super.clone();
            getKeys().forEach(key -> clone.put(key, get(key)));
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public <T extends NbtSerializable<T>> void putList(String key, Collection<T> list) {
        NbtList tagList = new NbtList();
        list.forEach(e -> tagList.add(e.writeNbt()));
        put(key, tagList);
    }

    public <T extends NbtSerializable<T>> ComposerCompound putListFluent(String key, Collection<T> list) {
        putList(key, list);
        return this;
    }

    public <T extends NbtSerializable<T>> List<T> getList(String key, Function<NbtCompound, T> factory) {
        NbtList tagList = getList(key, NbtElement.COMPOUND_TYPE);
        List<T> list = new ArrayList<>();

        for (int i = 0; i < tagList.size(); i++) {
            NbtCompound compound = tagList.getCompound(i);
            T element = factory.apply(compound);
            list.add(element);
        }

        return list;
    }

    public <T extends NbtSerializable<T>> List<T> getListOrDefault(String key, Function<NbtCompound, T> factory) {
        if (!contains(key, NbtElement.LIST_TYPE)) return new ArrayList<>();
        return getList(key, factory);
    }

    public <T extends NbtSerializable<T>> void putSerializable(String key, T value) {
        put(key, value.writeNbt());
    }

    public <T extends NbtSerializable<T>> T getSerializable(String key, Function<NbtCompound, T> factory) {
        NbtCompound compound = getCompound(key);
        return factory.apply(compound);
    }

    public <T extends NbtSerializable<T>> Optional<T> getOptional(String key, Function<NbtCompound, T> factory) {
        return contains(key, NbtElement.COMPOUND_TYPE)
                ? Optional.of(getSerializable(key, factory))
                : Optional.empty();
    }

    public <T extends NbtSerializable<T>> void putMap(String key, Map<String, T> map) {
        NbtList list = new NbtList();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            ComposerCompound tag = new ComposerCompound();
            tag.putString("key", entry.getKey());
            tag.putSerializable("value", entry.getValue());
            list.add(tag);
        }
        put(key, list);
    }

    public <T extends NbtSerializable<T>> Map<String, T> getMap(String key, Function<NbtCompound, T> factory) {
        NbtList values = getList(key, NbtElement.COMPOUND_TYPE);
        Map<String, T> map = new HashMap<>();

        for (NbtElement value : values) {
            if (value instanceof NbtCompound tag) {
                map.put(tag.getString("key"), factory.apply(tag.getCompound("value")));
            }
        }

        return map;
    }
}

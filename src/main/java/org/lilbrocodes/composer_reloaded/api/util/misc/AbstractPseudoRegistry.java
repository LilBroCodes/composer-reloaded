package org.lilbrocodes.composer_reloaded.api.util.misc;


import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPseudoRegistry<V> {
    private static final Map<Identifier, AbstractPseudoRegistry<?>> identified = new HashMap<>();

    protected final Map<Identifier, V> values;
    protected final Map<Identifier, V> fileValues;
    private boolean loadingFiles = false;

    protected AbstractPseudoRegistry() {
        this.values = new HashMap<>();
        this.fileValues = new HashMap<>();
    }

    public void register(Identifier id, V value) {
        values.put(id, value);
        if (loadingFiles) fileValues.put(id, value);
    }

    public void setLoadingFiles() {
        loadingFiles = true;
    }

    public void finishedLoadingFiles() {
        loadingFiles = false;
    }

    public V get(Identifier id) {
        return values.get(id);
    }

    public Map<Identifier, V> getAll() {
        return values;
    }

    public Map<Identifier, V> getAllFileLoadedValues() {
        return fileValues;
    }

    public void clear() {
        values.clear();
        fileValues.clear();
    }

    public Identifier find(V recipe) {
        for (Map.Entry<Identifier, V> entry : values.entrySet()) {
            if (entry.getValue() == recipe) return entry.getKey();
        }
        return null;
    }

    public static AbstractPseudoRegistry<?> registry(Identifier id) {
       return identified.get(id);
    }

    public static void identify(Identifier string, AbstractPseudoRegistry<?> registry) {
        identified.put(string, registry);
    }
}

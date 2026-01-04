package com.codex.composer.api.v1.easytags.registry;

import com.codex.composer.api.v1.easytags.exception.AutomataSerializationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Central registry for all {@link AutomataSerializable} instances.
 * <p>
 * Provides methods to register and retrieve serializers for specific types.
 * Throws {@link AutomataSerializationException} if a serializer is requested
 * for a type that hasn't been registered.
 * </p>
 */
public class AutomataSerializers {

    /** Map storing the serializers keyed by their handled class type. */
    private static final Map<Class<?>, AutomataSerializable<?>> registry = new HashMap<>();

    /**
     * Registers a serializer for a specific class type.
     *
     * @param clazz      the class type the serializer handles
     * @param serializer the serializer instance
     * @param <T>        the type handled by the serializer
     */
    public static <T> void register(Class<T> clazz, AutomataSerializable<T> serializer) {
        registry.put(clazz, serializer);
    }

    /**
     * Retrieves the serializer registered for a given class type.
     *
     * @param clazz the class type
     * @param <T>   the type handled by the serializer
     * @return the registered serializer
     * @throws AutomataSerializationException if no serializer is registered for the class
     */
    @SuppressWarnings("unchecked")
    public static <T> AutomataSerializable<T> get(Class<T> clazz) {
        AutomataSerializable<T> serializer = (AutomataSerializable<T>) registry.get(clazz);
        if (serializer == null) {
            throw new AutomataSerializationException("No serializer registered for type: " + clazz);
        }
        return serializer;
    }
}

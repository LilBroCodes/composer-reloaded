package org.lilbrocodes.composer_reloaded.api.v1.easytags.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a specific field in a class as serializable for Automata/NBT serialization.
 * <p>
 * By default, the field's name will be used as the key in the serialized data,
 * but a custom key can be provided via {@link #key()}.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Serialize {
    String key() default "";
}

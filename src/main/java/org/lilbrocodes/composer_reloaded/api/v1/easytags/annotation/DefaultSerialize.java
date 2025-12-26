package org.lilbrocodes.composer_reloaded.api.v1.easytags.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class so that, by default, all of its fields are automatically serialized
 * to NBT unless explicitly marked {@link Transient}.
 * <p>
 * The {@link #value()} parameter controls whether the default serialization behavior
 * is enabled for the class.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultSerialize {
    boolean value();
}

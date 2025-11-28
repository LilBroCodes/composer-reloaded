package org.lilbrocodes.composer_reloaded.api.easytags.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field to be excluded from serialization.
 * <p>
 * This annotation overrides any class-level {@link DefaultSerialize} or field-level
 * {@link Serialize} annotation. Fields marked as transient will never be serialized.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Transient {
    boolean value() default true;
}

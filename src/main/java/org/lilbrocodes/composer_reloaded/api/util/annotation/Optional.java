package org.lilbrocodes.composer_reloaded.api.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for {@link org.lilbrocodes.composer_reloaded.api.util.builder.BuilderFields} to mark a field as nullable.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Optional {
    boolean value() default true;
}

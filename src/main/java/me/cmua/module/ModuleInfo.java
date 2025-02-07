package me.cmua.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String name();

    String description() default "";

    ModuleCategory category();

    int priority() default ModulePriority.MEDIUM;
}
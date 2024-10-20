package com.enterprisemanager.backend.infrastructure.utils.validations;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsername {
    String message() default "That name already exists, choose another one.!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
package com.oekrem.SpringMVCBackEnd.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Şifre min 8 karakter içermeli ve en az bir büyük harf, bir rakam ve bir özel karakter içermelidir";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
package com.example.football.core.stadium.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StadiumValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StadiumValidation {
    String message() default "{StadiumValid = error }";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

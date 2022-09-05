package com.example.football.core.match.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchValidation {
    String message() default "{MatchValid = error }";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

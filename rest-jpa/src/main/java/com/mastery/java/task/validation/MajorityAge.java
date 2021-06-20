package com.mastery.java.task.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MajorityAgeValidator.class)
@Documented
public @interface MajorityAge {

    String message() default "{Age invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

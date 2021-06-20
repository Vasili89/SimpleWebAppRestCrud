package com.mastery.java.task.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MajorityAgeValidator implements ConstraintValidator<MajorityAge, LocalDate> {

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate today = LocalDate.now();
        return (localDate.plusYears(18).isBefore(today) || localDate.plusYears(18).equals(today));
    }
}

package com.travix.medusa.busyflights.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validates the departure and return date according to format, before after relation, past date
 * from today
 */
@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidateDepartureAndReturnDateValidator.class)
public @interface ValidateDepartureAndReturnDate {

  Class<?>[] groups() default {};

  String message() default "{validation.date.ValidateDepartureAndReturnDate.message}";

  Class<? extends Payload>[] payload() default {};
}

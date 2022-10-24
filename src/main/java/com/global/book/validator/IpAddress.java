package com.global.book.validator;

import java.lang.annotation.Documented;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy={IpAddressImpl.class})
@Target(value={FIELD})
@Retention(value=RUNTIME)
@Documented
public @interface IpAddress {
	String message() default "{validation.constraints.ip-address.message}";
	//String value() default "{validation.constraints.ip-address.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	
}

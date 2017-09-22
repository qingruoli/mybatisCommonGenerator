package com.gznb.member.validator.annotation;

import com.gznb.member.validator.DataInValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;
import java.sql.Types;

@Documented
@Constraint(
        validatedBy = DataInValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface DataIn {
    String message() default "数据不匹配";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // 逗号分隔
    String in() default "";

    int dataType() default Types.INTEGER;

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        DataIn[] value();
    }
}

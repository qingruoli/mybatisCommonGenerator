package com.gznb.member.validator.annotation;


import com.gznb.member.validator.DateFldFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = DateFldFormatValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface DateFldFormat {
    String message() default "时间格式无效";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String datePattern() default "yyyy-MM-dd HH:mm:ss";

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        DateFldFormat[] value();
    }
}

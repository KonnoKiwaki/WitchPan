package com.witch.common.annotation;

import java.lang.annotation.*;

/**
 * @author Yuuki
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {

}


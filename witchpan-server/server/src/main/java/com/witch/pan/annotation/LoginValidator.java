package com.witch.pan.annotation;

import java.lang.annotation.*;

/**
 * @author Yuuki
 */ //这个注解用在哪些东西上面  方法和类上面
@Target({ElementType.METHOD, ElementType.TYPE})
//运行时生效  生命周期
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginValidator {
    //登陆效验，调用这个注解的时候，会调用切面方法，进行登陆效验 (这个方法设置了一个切面)
    boolean validated() default true;

    boolean checkAdmin() default false;
}

package com.example.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 介绍
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public  @interface Schema {
    String value() default "";
}

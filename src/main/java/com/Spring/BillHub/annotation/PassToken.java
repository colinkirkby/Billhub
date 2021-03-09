package com.Spring.BillHub.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//PassToken for skipping credential check
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)          //Annotation can be stored by JVM, can be used by JVM during runtime
public @interface PassToken {                //Declaring an interface using the "@interface" syntax allows you to put a default value.
    boolean required() default true;
}

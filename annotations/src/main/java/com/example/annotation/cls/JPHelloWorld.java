package com.example.annotation.cls;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Rukey7 on 2016/8/7.
 * JavaPoet HelloWorld 例子
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface JPHelloWorld {
}

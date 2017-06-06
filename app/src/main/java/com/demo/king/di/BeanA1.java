package com.demo.king.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 10:32
 *
 * 用于区分相同类的不同实例对象，功能和@Named相同，只是不需要再去写name
 * 这也是@Qualifier和@Named的区别
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanA1 {
}

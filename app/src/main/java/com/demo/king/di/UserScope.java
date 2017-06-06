package com.demo.king.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 09:04
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}

package com.demo.king.di.component;

import com.demo.king.MainActivity;
import com.demo.king.di.module.Demo1Module;
import com.demo.king.di.module.Demo2Module;

import dagger.Component;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/05 21:16
 */
@Component(modules = {Demo1Module.class, Demo2Module.class})
public interface Demo1Component {

    void inject(MainActivity activity);

}

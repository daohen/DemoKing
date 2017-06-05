package com.demo.king.base;

import android.app.Activity;

import com.demo.king.di.component.DaggerDemo1Component;
import com.demo.king.di.component.Demo1Component;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/05 20:39
 */
public class AppActivity extends Activity {

    public Demo1Component getDemo1Component(){
        /**
         * build component的时候，如果module的构造函数是无参的，
         * 在这里可以不去初始化它，生成的代码检测到为null，会帮我们new出来
         */
        return DaggerDemo1Component.create();
    }

}

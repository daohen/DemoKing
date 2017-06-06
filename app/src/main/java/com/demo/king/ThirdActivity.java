package com.demo.king;

import android.os.Bundle;

import com.demo.king.base.AppActivity;
import com.demo.king.bean.Bean1;
import com.demo.king.bean.BeanE;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 13:50
 */
public class ThirdActivity extends AppActivity {


    @Inject
    Bean1 bean1;
    @Inject
    BeanE beanE;

    /**
     * 通过父Component中暴露出的方法访问，如果没有暴露，就不能访问
     */
    @Inject
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
    }
}

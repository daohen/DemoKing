package com.demo.king;

import android.os.Bundle;

import com.demo.king.base.AppActivity;
import com.demo.king.bean.BeanC;
import com.demo.king.bean.BeanD;

import javax.inject.Inject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 00:47
 */
public class SecondActivity extends AppActivity {

    @Inject
    BeanC beanC;
    /**
     * 此处component是@Subcomponent，可以访问到父Component产生的对象，所以可以注入BeanD
     */
    @Inject
    BeanD beanD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDemo3Component().inject(this);
    }
}

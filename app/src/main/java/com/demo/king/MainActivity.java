package com.demo.king;

import android.os.Bundle;

import com.demo.king.base.AppActivity;
import com.demo.king.bean.BeanA;
import com.demo.king.bean.BeanB;

import javax.inject.Inject;

public class MainActivity extends AppActivity {

    @Inject
    BeanA beanA;
    @Inject
    BeanB beanB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDemo1Component().inject(this);
    }
}

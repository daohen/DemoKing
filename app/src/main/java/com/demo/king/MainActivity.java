package com.demo.king;

import android.os.Bundle;

import com.demo.king.base.AppActivity;
import com.demo.king.bean.Bean0;
import com.demo.king.bean.BeanA;
import com.demo.king.bean.BeanB;

import javax.inject.Inject;

public class MainActivity extends AppActivity {

    /**
     * 通过在构造方法上面添加@Inject注入
     */
    @Inject
    Bean0 bean0;
    /**
     * 通过module注入
     */
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

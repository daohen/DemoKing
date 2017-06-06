package com.demo.king;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.demo.king.base.AppActivity;
import com.demo.king.bean.Bean0;
import com.demo.king.bean.Bean1;
import com.demo.king.bean.BeanA;
import com.demo.king.bean.BeanB;

import javax.inject.Inject;

public class MainActivity extends AppActivity {

    /**
     * 构造器注入，有参数，参数也会被注入
     */
    @Inject
    Bean0 bean0;
    /**
     * 属性注入
     */
    @Inject
    BeanA beanA;
    @Inject
    BeanB beanB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 触发注入，当前类下面被@Inject标记的，都会被注入
         * 找寻对应的类的实例化方式是按照下面顺序来的：
         *      1 先在Demo1Component里面找，看是否有合适的实现方式
         *      2 如果有合适的方法，再看方法是否有参数
         *      2.1 如果有参数，按照步骤1依次初始化每个参数
         *      2.2 如果没有参数， 直接实例化，该类的注入完成
         *      3 如果没有合适的方法，查找@Inject注解的构造函数，看是否有参数
         *      3.1 如果有参数，按照步骤1依次初始化每个参数
         *      3.2 如果没有参数， 直接实例化，该类的注入完成
         */
        getDemo1Component().inject(this);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    /**
     * 方法注入，会先注入参数
     * 适用于需要安全的this对象，因为该方法执行在构造器之后
     */
    @Inject void methodInject(Bean1 bean1){
        Log.e("daohen", "methodInject");
    }
}

package com.demo.king;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.demo.king.base.AppActivity;
import com.demo.king.bean.BeanC;
import com.demo.king.bean.BeanD;
import com.demo.king.bean.User;

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

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("alun", 18);
                getApp().createUserComponent(user, new User("alun2", 40));

                Log.e("daohen", SecondActivity.class.getSimpleName() + " new User = "+user);

                startActivity(new Intent(SecondActivity.this, User1Activity.class));
            }
        });
    }
}

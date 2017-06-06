package com.demo.king;

import android.os.Bundle;
import android.util.Log;

import com.demo.king.base.AppActivity;
import com.demo.king.bean.User;

import javax.inject.Inject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 09:05
 */
public class User2Activity extends AppActivity {

    /**
     * 这里的user就是SecondActivity里面new出来的
     */
    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getApp().getUserComponent().inject(this);

        Log.e("daohen", User2Activity.class.getSimpleName() + " user="+user);
        Log.e("daohen", User2Activity.class.getSimpleName() + " name=" + user.getName() + " age=" + user.getAge());
    }
}

package com.demo.king;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.demo.king.base.App;
import com.demo.king.base.AppActivity;
import com.demo.king.bean.User;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 09:05
 */
public class User1Activity extends AppActivity {

    /**
     * 这里的user就是SecondActivity里面new出来的，同一个实例
     * 由于UserModule里面提供了两个User的实现方式，
     * 为了更准确找出我们需要的实现方式，需要标记@Named加以区别
     */
    @Inject @Named("userA")
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getApp().getUserComponent().inject(this);

        Log.e("daohen", User1Activity.class.getSimpleName() + " user="+user);
        Log.e("daohen", User1Activity.class.getSimpleName() + " name=" + user.getName() + " age="+user.getAge());
        user.setAge(20);
        Log.e("daohen", User1Activity.class.getSimpleName() + " modify age=" + user.getAge());

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User1Activity.this, User2Activity.class));
            }
        });
    }
}

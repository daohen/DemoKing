package com.demo.king.di.module;

import com.demo.king.bean.User;
import com.demo.king.di.UserScope;

import dagger.Module;
import dagger.Provides;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 09:08
 */
@Module
public class UserModule {

    private User user;

    public UserModule(User user){
        this.user = user;
    }

    @Provides @UserScope User provideUser(){
        return user;
    }
}

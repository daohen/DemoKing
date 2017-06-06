package com.demo.king.di.module;

import com.demo.king.bean.User;
import com.demo.king.di.UserScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 09:08
 */
@Module
public class UserModule {

    private User userA;
    private User userB;

    public UserModule(User userA, User userB){
        this.userA = userA;
        this.userB = userB;
    }

    /**
     * 用@Named来区分两个实例
     * @return
     */

    @Provides @UserScope @Named("userA") User provideUserA(){
        return userA;
    }

    @Provides @UserScope @Named("userB") User provideUserB(){
        return userB;
    }
}

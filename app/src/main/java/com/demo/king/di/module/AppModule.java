package com.demo.king.di.module;

import android.content.Context;

import com.demo.king.bean.BeanD;
import com.demo.king.bean.DataRepository;
import com.demo.king.bean.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 00:32
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    BeanD provideBeanD(){
        return new BeanD();
    }

    @Provides @Singleton
    Context provideContext(){
        return context;
    }

    @Provides @Singleton
    Repository provideRepository(DataRepository dataRepository){
        return new Repository(dataRepository);
    }
}

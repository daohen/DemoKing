package com.demo.king.db;


import android.arch.persistence.room.Room;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Contexts;

public class DatabaseCreator {

    private AppDataBase appDataBase;

    public static DatabaseCreator get(){
        return gDefault.get();
    }

    public void createDb(){
        appDataBase = Room.databaseBuilder(Contexts.getContext(), AppDataBase.class, AppDataBase.DABABASE_NAME).build();
    }

    public AppDataBase getAppDataBase(){
        return appDataBase;
    }

    private static final Singleton<DatabaseCreator> gDefault = new Singleton<DatabaseCreator>() {
        @Override
        protected DatabaseCreator create() {
            return new DatabaseCreator();
        }
    };
}

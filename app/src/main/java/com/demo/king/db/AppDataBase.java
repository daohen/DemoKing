package com.demo.king.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.demo.king.db.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DABABASE_NAME = "simple_db";

    public abstract User userDao();

}

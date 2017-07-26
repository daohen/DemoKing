package com.demo.king;


import android.app.Application;

import com.daohen.personal.toolbox.library.util.Contexts;
import com.demo.king.db.DatabaseCreator;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Contexts.setContext(this);

        DatabaseCreator.get().createDb();
    }
}

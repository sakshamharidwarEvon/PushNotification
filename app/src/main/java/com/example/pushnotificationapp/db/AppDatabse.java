package com.example.pushnotificationapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class AppDatabse extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabse INSTANCE;

    public static AppDatabse getDbInstance(Context context){
        if(INSTANCE == null)
        {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabse.class,"DB name")
                    .allowMainThreadQueries()
                    .build();
        }


        return INSTANCE;

    }
}

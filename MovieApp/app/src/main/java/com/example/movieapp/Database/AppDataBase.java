package com.example.movieapp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.movieapp.Model.MoviesEntity;

@Database(entities = {MoviesEntity.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "AppDataBase.db";
    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract MoviesDAO moviesDAO();

    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}

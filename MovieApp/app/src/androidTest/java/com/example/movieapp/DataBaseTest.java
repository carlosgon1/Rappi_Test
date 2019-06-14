package com.example.movieapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.movieapp.Database.AppDataBase;
import com.example.movieapp.Database.MoviesDAO;
import com.example.movieapp.Model.MoviesEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DataBaseTest {
    public static final String TAG ="UnitTesting";
    private AppDataBase mDB;
    private MoviesDAO moviesDAO;

    @Before
    public void CreateDB(){
        Context ctx = InstrumentationRegistry.getTargetContext();
        mDB = Room.inMemoryDatabaseBuilder(ctx,AppDataBase.class).build();
        moviesDAO = mDB.moviesDAO();
        Log.i(TAG,"create database");
    }

    @After
    public void closeD(){
        mDB.close();
        Log.i(TAG,"close database");
    }

    @Test
    public void createAndRetriveMovies(){
        List<MoviesEntity> movieList = new ArrayList();
        movieList.add(new MoviesEntity(1,10,true,"Hidra movie 1","/path/dummy/","good Movie"));
        movieList.add(new MoviesEntity(1,21,true,"Hidra movie 2","/path/dummy/","good Movie"));
        movieList.add(new MoviesEntity(2,99,true,"Rappi movie 3","/path/dummy/","good Movie"));
        moviesDAO.insertAll(movieList);
        int count = moviesDAO.getCount(1);
      //  List<MoviesEntity> moviesFound = moviesDAO.getAll(1,"Hidra");
        Log.i(TAG,"number of rows = "+count);
       // Log.i(TAG,"number of rows on live = "+moviesFound.size());
        assertEquals(2,count);

    }
}

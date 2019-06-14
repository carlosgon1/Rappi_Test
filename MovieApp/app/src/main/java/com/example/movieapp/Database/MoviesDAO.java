package com.example.movieapp.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.movieapp.Model.MoviesEntity;

import java.util.List;

@Dao
public interface MoviesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MoviesEntity> movies);

    @Query("SELECT * FROM movies")
    LiveData<List<MoviesEntity>> getAllMovies();

    @Query("SELECT COUNT(*) FROM movies WHERE category_id = :id")
    int getCount(int id);
}

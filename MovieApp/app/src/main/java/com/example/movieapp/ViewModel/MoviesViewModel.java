package com.example.movieapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.example.movieapp.Repository.AppRepository;
import com.example.movieapp.Model.MoviesEntity;

import java.util.ArrayList;
import java.util.List;

public class MoviesViewModel extends AndroidViewModel {
    public LiveData<List<MoviesEntity>> mMoviesList;
    private int mCategory;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        mMoviesList = AppRepository.getInstance(application.getApplicationContext()).mMovies;
    }

    public List<MoviesEntity> getMovies() {
        List<MoviesEntity> temp = new  ArrayList();
        for (MoviesEntity movie: mMoviesList.getValue()) {
            if(movie.getCategory_id() == mCategory){
                temp.add(movie);
            }
        }
        return temp;
    }

    public void setCategory(int mCategory) {
        this.mCategory = mCategory;
    }

}

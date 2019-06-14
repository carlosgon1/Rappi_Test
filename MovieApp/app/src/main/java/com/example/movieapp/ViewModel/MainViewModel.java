package com.example.movieapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.movieapp.Repository.AppRepository;


public class MainViewModel extends AndroidViewModel {;
    private AppRepository.DataListener mDataListener;
    private AppRepository mRepository;
    private Context mContext;


    public MainViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
    }

    public void downloadDataFromServer(){
        mRepository = AppRepository.getInstance(mContext);
        mRepository.setmDataListener(mDataListener);
        mRepository.fetchData(mContext);
    }

    public void setmDataListener(AppRepository.DataListener mDataListener) {
        this.mDataListener = mDataListener;
    }

}

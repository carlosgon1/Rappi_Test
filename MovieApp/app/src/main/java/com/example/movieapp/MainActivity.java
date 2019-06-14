package com.example.movieapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.movieapp.Adapters.MainAdapter;
import com.example.movieapp.Model.MoviesEntity;
import com.example.movieapp.Repository.AppRepository;
import com.example.movieapp.ViewModel.MainViewModel;


import java.util.List;

import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private  TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MainViewModel  mViewModel;
    private ProgressBar mProgressBar;
    private FloatingActionButton mFloatingActionButton;
    private TextView mFailDataTV;
    private Button mReloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager =  findViewById(R.id.pager);
        mTabLayout =  findViewById(R.id.tablayout);
        mProgressBar = findViewById(R.id.progressBar);
        mFloatingActionButton = findViewById(R.id.fabActionButton);
        mFailDataTV = findViewById(R.id.fail_download_text);
        mReloadButton = findViewById(R.id.reload_button);
        mReloadButton.setOnClickListener(reload);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.setmDataListener(dataListener);
        mViewModel.downloadDataFromServer();
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if(fragments.size()>3)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    View.OnClickListener fabClick = v -> {
        Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
        intent.putExtra(CATEGORY_MOVIE, mTabLayout.getSelectedTabPosition());
        startActivity(intent);
    };

    AppRepository.DataListener dataListener = new AppRepository.DataListener() {
        @Override
        public void dataReady(List<MoviesEntity> movies) {
           Log.i(TAG,"Data ready the data contains "+movies.size());
            loadFragments();
        }

        @Override
        public void dataDownloadFail() {
            Log.i(TAG,"Data fail");
            runOnUiThread (new Thread(() -> {
                mReloadButton.setVisibility(View.VISIBLE);
                mFailDataTV.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }));

        }
    };

    private void loadFragments(){
        runOnUiThread (new Thread(() -> {
            MainAdapter myPagerAdapter = new MainAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(myPagerAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
            mProgressBar.setVisibility(View.GONE);
            mFloatingActionButton.setOnClickListener(fabClick);
        }));
    }

    View.OnClickListener reload = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mReloadButton.setVisibility(View.GONE);
            mFailDataTV.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
            mViewModel.downloadDataFromServer();
        }
    };
}

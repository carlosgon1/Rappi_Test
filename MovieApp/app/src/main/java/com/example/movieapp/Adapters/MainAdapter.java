package com.example.movieapp.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.movieapp.CategoryFragment;

import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE;
import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_POPULAR;
import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_TOP_RATED;
import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_UPCOMING;

public class MainAdapter extends FragmentStatePagerAdapter {

    public MainAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args;
        CategoryFragment categoryFragment;

        switch (position){
           case 0:
               categoryFragment = new CategoryFragment();
               args = new Bundle();
               args.putInt(CATEGORY_MOVIE,CATEGORY_MOVIE_POPULAR);
               categoryFragment.setArguments(args);
               return categoryFragment;

            case 1:
                categoryFragment = new CategoryFragment();
                args = new Bundle();
                args.putInt(CATEGORY_MOVIE,CATEGORY_MOVIE_TOP_RATED);
                categoryFragment.setArguments(args);
                return categoryFragment;

            case 2:
                categoryFragment = new CategoryFragment();
                args = new Bundle();
                args.putInt(CATEGORY_MOVIE,CATEGORY_MOVIE_UPCOMING);
                categoryFragment.setArguments(args);
                return categoryFragment;



               default: return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Popular";
            case 1: return "Top Rated";
            case 2: return "Upcoming";
            default: return null;
        }
    }


}

package com.example.movieapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_POPULAR;
import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_TOP_RATED;
import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_UPCOMING;

public class MovieAppUtils {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static String getCategoryName(int category){
        switch (category){
            case CATEGORY_MOVIE_TOP_RATED:
                return "Search in Top Rated Category";
            case CATEGORY_MOVIE_POPULAR:
                return "Search in Popular Category";
            case CATEGORY_MOVIE_UPCOMING:
                return "Search in Upcoming Category";
                default:return "";
        }
    }
}

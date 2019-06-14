package com.example.movieapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.movieapp.Utils.AppConstants.DETAILS_MOVIE;
import static com.example.movieapp.Utils.AppConstants.TITLE_MOVIE;
import static com.example.movieapp.Utils.AppConstants.URL_MOVIE;

public class DetailFragment extends Fragment {
    private  View mMainView;
    private ImageView mMovieImage;
    private TextView mMovieTitle;
    private TextView mMovieDetails;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mMainView = inflater.inflate(R.layout.movie_details, container, false);
        Bundle args = getArguments();
        String movie_url = args.getString(URL_MOVIE, "");
        String movie_title = args.getString(TITLE_MOVIE, "");
        String movie_details = args.getString(DETAILS_MOVIE, "");
        mMovieTitle =  mMainView.findViewById(R.id.title_movie);
        mMovieImage = mMainView.findViewById(R.id.movie_img_detail);
        mMovieDetails = mMainView.findViewById(R.id.movie_texview_detail);

        Glide.with(getActivity().getApplicationContext()).load(movie_url).into(mMovieImage);
        mMovieDetails.setText(movie_details);
        mMovieTitle.setText(movie_title);

        return mMainView;
    }
}

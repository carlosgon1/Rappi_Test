package com.example.movieapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.Adapters.MovieAdapter;
import com.example.movieapp.Model.MoviesEntity;
import com.example.movieapp.ViewModel.MoviesViewModel;

import java.util.List;

import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE;
import static com.example.movieapp.Utils.AppConstants.DETAILS_MOVIE;
import static com.example.movieapp.Utils.AppConstants.TITLE_MOVIE;
import static com.example.movieapp.Utils.AppConstants.URL_MOVIE;

public class CategoryFragment extends Fragment {
    private MoviesViewModel mViewModel;
    private View mMainView;
    private RecyclerView mMoviesRecyclerview;
    private MovieAdapter mMovieAdapter;
    private int mCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle args = getArguments();
        mCategory = args.getInt(CATEGORY_MOVIE, 1);
        mMainView = inflater.inflate(R.layout.popular_fragment, container, false);
        mMoviesRecyclerview = mMainView.findViewById(R.id.recycler_movies_photos);
        initViewModel();
        return mMainView;
    }


    void initViewModel(){
        final Observer<List<MoviesEntity>> moviesObserver = moviesEntities -> {
            if(mMovieAdapter == null){
                mMovieAdapter = new MovieAdapter(getActivity().getApplicationContext(),mViewModel.getMovies(),movieOnClick,false);
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                mMoviesRecyclerview.setLayoutManager(layoutManager);
                mMoviesRecyclerview.setHasFixedSize(true);
                mMoviesRecyclerview.setAdapter(mMovieAdapter);
            }else{
                mMovieAdapter.notifyDataSetChanged();
                mMoviesRecyclerview.invalidate();
            }
        };

        mViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        mViewModel.setCategory(mCategory);
        mViewModel.mMoviesList.observe(this,moviesObserver);
    }


    private MovieAdapter.MovieOnClickListener movieOnClick = new MovieAdapter.MovieOnClickListener() {
        @Override
        public void onClickMovie(int index) {
        DetailFragment frag = new DetailFragment();
            Bundle args = new Bundle();
            args.putString(URL_MOVIE, mViewModel.getMovies().get(index).getFullPath());
            args.putString(DETAILS_MOVIE,  mViewModel.getMovies().get(index).getOverview());
            args.putString(TITLE_MOVIE,  mViewModel.getMovies().get(index).getTitle());
            frag.setArguments(args);

           getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, frag,DetailFragment.class.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();

        }
    };




}

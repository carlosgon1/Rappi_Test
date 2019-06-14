package com.example.movieapp;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.movieapp.Adapters.MovieAdapter;
import com.example.movieapp.Model.MoviesEntity;
import com.example.movieapp.Utils.MovieAppUtils;
import com.example.movieapp.ViewModel.SearchViewModel;

import java.util.List;

import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE;
import static com.example.movieapp.Utils.AppConstants.DETAILS_MOVIE;
import static com.example.movieapp.Utils.AppConstants.TITLE_MOVIE;
import static com.example.movieapp.Utils.AppConstants.URL_MOVIE;


public class SearchActivity extends AppCompatActivity {

    private RecyclerView mSearchRecyclerView;
    private TextView mCategoryNameTV;
    private Button searchButton;
    private MovieAdapter mMovieAdapter;
    private SearchViewModel mViewModel;
    private EditText editTextSearch;
    private List<MoviesEntity> movies;
    private int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_fagment);

        Bundle args = getIntent().getExtras();
        category = args.getInt(CATEGORY_MOVIE, 1);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        mViewModel.setCategory(category);

        searchButton = findViewById(R.id.search_button);
        mSearchRecyclerView =findViewById(R.id.search_recycler);
        mCategoryNameTV =findViewById(R.id.title_category);
        editTextSearch = findViewById(R.id.edit_text);

        mCategoryNameTV.setText(MovieAppUtils.getCategoryName(category));
        searchButton.setOnClickListener(searchOnClick);

        movies = mViewModel.getMovies();
        mMovieAdapter = new MovieAdapter(getApplicationContext(),movies , movieOnClick, true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mSearchRecyclerView.setLayoutManager(linearLayoutManager);
        mSearchRecyclerView.setHasFixedSize(true);
        mSearchRecyclerView.setAdapter(mMovieAdapter);
    }

    private View.OnClickListener searchOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = editTextSearch.getText().toString();
            movies = mViewModel.getMoviesBySearch(text);
            mMovieAdapter = new MovieAdapter(getApplicationContext(),movies , movieOnClick, true);
            mSearchRecyclerView.setAdapter(mMovieAdapter);
            mMovieAdapter.notifyDataSetChanged();
            mSearchRecyclerView.invalidate();
        }
    };

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if(fragments.size()!=0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
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

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.search_main_container, frag,DetailFragment.class.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();

        }
    };


}

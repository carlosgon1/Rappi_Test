package com.example.movieapp.Adapters;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Model.MoviesEntity;
import com.example.movieapp.R;

import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public interface MovieOnClickListener {
        void onClickMovie(int index);
    }

    private List<MoviesEntity> mMovies;
    private Context mContext;
    private MovieOnClickListener mListener;
    private boolean mIsSearchView = false;

    public MovieAdapter(Context context , List<MoviesEntity> movies, MovieOnClickListener listener,boolean searchView){
        mMovies = movies;
        mContext = context;
        mListener = listener;
        mIsSearchView = searchView;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;

        if(mIsSearchView)
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item_movie, viewGroup, false);
        else
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_picture_view_holder, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, int i) {
      //  if(mCategory == mMovies.get(i).getCategory_id()) {
            final TextView movie_name = viewHolder.movieName;
            final ImageView movie_image = viewHolder.movieImage;

            movie_name.setText(mMovies.get(i).getTitle());
            Glide.with(mContext).load(mMovies.get(i).getFullPath()).into(movie_image);
            movie_image.setOnClickListener(v -> mListener.onClickMovie(i));
      //  }
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView movieName;
        public ImageView movieImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_img);
            movieName = itemView.findViewById(R.id.movie_name);
        }
    }
}


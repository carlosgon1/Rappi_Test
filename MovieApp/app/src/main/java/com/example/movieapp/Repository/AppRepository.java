package com.example.movieapp.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.movieapp.Adapters.MovieAdapter;
import com.example.movieapp.Database.AppDataBase;
import com.example.movieapp.Model.MoviesEntity;
import com.example.movieapp.io.ApiResponse;
import com.example.movieapp.Utils.MovieAppUtils;
import com.example.movieapp.io.GetData;
import com.example.movieapp.io.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_POPULAR;
import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_TOP_RATED;
import static com.example.movieapp.Utils.AppConstants.CATEGORY_MOVIE_UPCOMING;

public class AppRepository {

    public interface DataListener {
        void dataReady(List<MoviesEntity> movies);
        void dataDownloadFail();
    }

    private static final String TAG = AppRepository.class.getSimpleName();
    private static AppRepository mInstance;
    private final static String security_key = "54e22058efd67e1bfcf00b85da57005e";
    public LiveData<List<MoviesEntity>> mMovies;
    private AppDataBase mDb;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    private List<MoviesEntity> mPopularMovies;
    private List<MoviesEntity> mTopRatedMovies;
    private List<MoviesEntity> mUpcomingMovies;
    private List<MoviesEntity> fullMovieList = new ArrayList();
    boolean request1,request2,request3 = false;
    DataListener mDataListener;

    public void setmDataListener(DataListener mDataListener) {
        this.mDataListener = mDataListener;
    }

    public static AppRepository getInstance(Context context){
        if(mInstance == null){
            mInstance = new AppRepository(context);
        }
        return mInstance;
    }

    private AppRepository (Context context){
        mDb = AppDataBase.getInstance(context);
        mMovies = getAllMovies();
    }

    public void fetchData(Context context){
        if(MovieAppUtils.isNetworkAvailable(context)){
            Log.i(TAG,"Network Available");
                    fetchTopRatedMoviesData();
                    fetchPopularMoviesData();
                    fetchUpcomingMoviesData();
        }else{
            Log.i(TAG,"Network Unavailable");

            final Observer<List<MoviesEntity>> moviesObserver = moviesEntities -> {
                if(moviesEntities.size()>0)
                    mDataListener.dataReady(moviesEntities);
                else
                    mDataListener.dataDownloadFail();
            };
            mMovies.observeForever(moviesObserver);

        }
    }

    private LiveData<List<MoviesEntity>> getAllMovies(){
        return mDb.moviesDAO().getAllMovies();
    }

    private void loadDataList(ApiResponse response,int category) {
        switch (category){
            case CATEGORY_MOVIE_TOP_RATED:
                if(!request1){
                mTopRatedMovies = response.getMovies();
                for(int x = 0;x<mTopRatedMovies.size();x++)
                    mTopRatedMovies.get(x).setCategory_id(CATEGORY_MOVIE_TOP_RATED);
                fullMovieList.addAll(mTopRatedMovies);
                request1 =true;
                }else
                    mDataListener.dataDownloadFail();
                break;

            case CATEGORY_MOVIE_POPULAR:
                if(!request2){
                mPopularMovies = response.getMovies();
                for(int x = 0;x<mPopularMovies.size();x++)
                    mPopularMovies.get(x).setCategory_id(CATEGORY_MOVIE_POPULAR);
                fullMovieList.addAll(mPopularMovies);
                request2 =true;
                }else
                    mDataListener.dataDownloadFail();
                break;

            case CATEGORY_MOVIE_UPCOMING:
                if (!request3) {
                    mUpcomingMovies = response.getMovies();
                    for (int x = 0; x < mUpcomingMovies.size(); x++)
                        mUpcomingMovies.get(x).setCategory_id(CATEGORY_MOVIE_UPCOMING);
                    fullMovieList.addAll(mUpcomingMovies);
                request3 = true;
                }else
                    mDataListener.dataDownloadFail();
                break;
        }

        mExecutor.execute(() ->{
            if(request1 && request2 && request3) {
                request1 = false;request2 = false;request3 = false;
                Log.i(TAG, "size of movies" + fullMovieList.size() + "called by " + category);
                mDb.moviesDAO().insertAll(fullMovieList);
                mDataListener.dataReady(fullMovieList);
            }
        });
    }

    void fetchPopularMoviesData(){
        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        Call<ApiResponse> call = service.getPopularMovies(security_key,1);
        Log.e( TAG,"Request : "+ call.request().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i(TAG,"response : " + response.body());
                loadDataList(response.body(),CATEGORY_MOVIE_POPULAR);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                Log.e(TAG, "Failure : " + throwable.toString());
            }
        });
    }

    void fetchUpcomingMoviesData(){
        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        Call<ApiResponse> call = service.getUpconmingMovies(security_key,1);
        Log.e( TAG,"Request : "+ call.request().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i(TAG,"response : " + response.body());
                loadDataList(response.body(),CATEGORY_MOVIE_UPCOMING);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                Log.e(TAG, "Failure : " + throwable.toString());
            }
        });
    }

    void fetchTopRatedMoviesData(){
        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        Call<ApiResponse> call = service.getTopRatedMovies(security_key,1);
        Log.e( TAG,"Request : "+ call.request().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i(TAG,"response : " + response.body());
                loadDataList(response.body(),CATEGORY_MOVIE_TOP_RATED);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                Log.e(TAG, "Failure : " + throwable.toString());
            }
        });
    }

}

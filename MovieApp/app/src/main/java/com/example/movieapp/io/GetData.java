package com.example.movieapp.io;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetData {
   @GET("movie/popular")
   Call<ApiResponse> getPopularMovies(@Query("api_key") String security_key,@Query("page") int page);

    @GET("movie/top_rated")
    Call<ApiResponse> getTopRatedMovies(@Query("api_key") String security_key,@Query("page") int page);

    @GET("movie/upcoming")
    Call<ApiResponse> getUpconmingMovies(@Query("api_key") String security_key,@Query("page") int page);
}
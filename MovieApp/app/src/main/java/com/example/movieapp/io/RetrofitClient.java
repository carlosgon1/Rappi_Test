package com.example.movieapp.io;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    //Define the base URL//
    private static final String BASE_URL = "https://api.themoviedb.org/3/";


    //https://api.themoviedb.org/3/movie/popular?api_key=54e22058efd67e1bfcf00b85da57005e

    //https://developers.themoviedb.org/4/getting-started/authorization

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
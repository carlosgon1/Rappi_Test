package com.example.movieapp.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
public class MoviesEntity {

    private static String basePath = "https://image.tmdb.org/t/p/w500";

    private int category_id;
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    private int movie_id;

    @SerializedName("video")
    private Boolean available_video;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String path_img;

    @SerializedName("overview")
    private String overview;

    public MoviesEntity(int category_id, int movie_id, Boolean available_video, String title, String path_img, String overview) {
        this.category_id = category_id;
        this.movie_id = movie_id;
        this.available_video = available_video;
        this.title = title;
        this.path_img = path_img;
        this.overview = overview;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getPath_img() {
        return path_img;
    }

    public String getFullPath(){
        return basePath + path_img;
    }

    public String getOverview() {
        return overview;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public Boolean getAvailable_video() {
        return available_video;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "MoviesEntity{" +
                "movie_id=" + movie_id +
                ", available_video=" + available_video +
                ", title='" + title + '\'' +
                ", path_img='" +basePath+ path_img + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}

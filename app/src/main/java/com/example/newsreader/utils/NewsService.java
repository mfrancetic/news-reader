package com.example.newsreader.utils;

import com.example.newsreader.NewsStory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsService {

    @GET("topstories.json?print=pretty")
    Call<List<Integer>> topNewsStoriesIds();

    @GET("item/{id}.json?print=pretty")
    Call<NewsStory> storyDetails(@Path("id") int storyId);
}
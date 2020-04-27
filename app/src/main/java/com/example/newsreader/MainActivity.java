package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.newsreader.utils.AppDatabase;
import com.example.newsreader.utils.Constants;
import com.example.newsreader.utils.NewsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NewsService newsService;
    private List<NewsStory> newsStories = new ArrayList<>();
    private AppDatabase database;
    private List<NewsStory> savedNewsStories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNewsStoriesFromDatabase();
        generateApiTopStoriesCall();
    }

    private void generateApiTopStoriesCall() {
        newsService = RetrofitClientInstance.getRetrofitInstance().create(NewsService.class);
        Call<List<Integer>> newsStoryCalls = newsService.topNewsStoriesIds();

        newsStoryCalls.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                getNewsStories(response.body());
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Toast.makeText(MainActivity.this, getString(R.string.something_went_wrong),
                        Toast.LENGTH_LONG).show();
                setupRecyclerView();
            }
        });
    }

    private void getNewsStories(List<Integer> newsStoryIds) {
        newsService = RetrofitClientInstance.getRetrofitInstance().create(NewsService.class);
        for (int i = 0; i < 20; i++) {
            if (newsStoryIds.size() >= 20) {
                Call<NewsStory> newsStoryCall = newsService.storyDetails(newsStoryIds.get(i));

                newsStoryCall.enqueue(new Callback<NewsStory>() {
                    @Override
                    public void onResponse(Call<NewsStory> call, Response<NewsStory> response) {
                        newsStories.add(response.body());
                    }

                    @Override
                    public void onFailure(Call<NewsStory> call, Throwable t) {
                        Toast.makeText(MainActivity.this, getString(R.string.something_went_wrong),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        setupRecyclerView();
    }

    private void addNewsStoriesToDatabase(List<NewsStory> newsStories) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                database.newsStoryDao().deleteAll();
                database.newsStoryDao().insertAll(newsStories);
            }
        });
    }

    private List<NewsStory> getNewsStoriesFromDatabase() {
        AsyncTask.execute(() -> {
            database = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, Constants.DATABASE_NAME).build();
            savedNewsStories = database.newsStoryDao().getAll();
        });
        return savedNewsStories;
    }

    private void setupRecyclerView() {
        if (newsStories.isEmpty()) {
            newsStories = savedNewsStories;
        } else {
            addNewsStoriesToDatabase(newsStories);
        }
        RecyclerView mainRecyclerView = findViewById(R.id.main_recycler_view);
        NewsRecyclerViewAdapter adapter = new NewsRecyclerViewAdapter(newsStories, new NewsRecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent openDetailActivityIntent = new Intent(MainActivity.this, NewsStoryDetailActivity.class);
                openDetailActivityIntent.putExtra(Constants.NEWS_STORY_URL_KEY, newsStories.get(position).getUrl());
                startActivity(openDetailActivityIntent);
            }
        });
        mainRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mainRecyclerView.addItemDecoration(decoration);
    }
}
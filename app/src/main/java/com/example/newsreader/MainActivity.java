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
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private ProgressBar loadingIndicator;
    private TextView mainEmptyTextView;
    private RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLoadingView();

        getNewsStoriesFromDatabase();
        generateApiTopStoriesCall();
    }

    private void setupLoadingView() {
        mainRecyclerView = findViewById(R.id.main_recycler_view);
        loadingIndicator = findViewById(R.id.main_loading_indicator);
        mainEmptyTextView = findViewById(R.id.main_empty_text_view);

        loadingIndicator.setVisibility(View.VISIBLE);
        mainEmptyTextView.setVisibility(View.GONE);
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
        AsyncTask.execute(() -> {
            database.newsStoryDao().deleteAll();
            database.newsStoryDao().insertAll(newsStories);
        });
    }

    private void getNewsStoriesFromDatabase() {
        AsyncTask.execute(() -> {
            database = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, Constants.DATABASE_NAME).build();
            savedNewsStories = database.newsStoryDao().getAll();
        });
    }

    private void setupRecyclerView() {
        if (newsStories.isEmpty()) {
            newsStories = savedNewsStories;
        } else {
            addNewsStoriesToDatabase(newsStories);
        }
        loadingIndicator.setVisibility(View.GONE);

        if (newsStories.isEmpty()) {
            setEmptyView();
        } else {
            mainRecyclerView.setVisibility(View.VISIBLE);
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

    private void setEmptyView() {
        mainEmptyTextView.setVisibility(View.VISIBLE);
        mainRecyclerView.setVisibility(View.GONE);
    }
}
package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<NewsStory> newsStoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewsStoryList();
        setupRecyclerView();
    }

    private void addNewsStoryList() {
        newsStoryList = new ArrayList<>();

        List<Integer> kids = new ArrayList<>();
        kids.add(3131);
        kids.add(131);
        kids.add(242);

        List<Integer> parts = new ArrayList<>();
        parts.add(311);
        parts.add(11);
        parts.add(42);

        NewsStory newsStory = new NewsStory(8863, false, "story", "Maja Francetic", 1175714200, "Story Text", false, 13553,
                160704, kids, "http://www.getdropbox.com/u/2/screencast.html", 5, "My YC app: Dropbox - Throw away your USB drive",
                parts, 10);
        NewsStory newsStory2 = new NewsStory(8864, false, "story", "Marko Viskanic", 1175714201, "Story Text", false, 13543,
                160703, kids, "http://www.getdropbox.com/u/2/screencast.html", 5, "My YC app: Dropbox - Throw away your USB drive",
                parts, 10);

        newsStoryList.add(newsStory);
        newsStoryList.add(newsStory2);
    }

    private void setupRecyclerView() {
        RecyclerView mainRecyclerView = findViewById(R.id.main_recycler_view);
        NewsRecyclerViewAdapter adapter = new NewsRecyclerViewAdapter(newsStoryList, new NewsRecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Position clicked: "+ position, Toast.LENGTH_LONG).show();
            }
        });
        mainRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mainRecyclerView.addItemDecoration(decoration);
    }
}
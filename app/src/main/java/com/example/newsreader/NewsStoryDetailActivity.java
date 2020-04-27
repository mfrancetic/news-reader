package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsStoryDetailActivity extends AppCompatActivity {

    private String newsStoryItemUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_story_detail);

        WebView webView = findViewById(R.id.news_story_web_view);

        Intent mainActivityIntent = getIntent();
        if (mainActivityIntent != null) {
            newsStoryItemUrl = mainActivityIntent.getStringExtra(Constants.NEWS_STORY_URL_KEY);
        }

        if (newsStoryItemUrl != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(newsStoryItemUrl);
        }
    }
}
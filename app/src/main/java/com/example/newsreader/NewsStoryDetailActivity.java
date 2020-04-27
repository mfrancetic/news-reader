package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newsreader.utils.Constants;

public class NewsStoryDetailActivity extends AppCompatActivity {

    private String newsStoryItemUrl;
    private String newsStoryItemText;
    private TextView newsStoryTextView;
    private ProgressBar loadingIndicator;
    private TextView detailEmptyTextView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_story_detail);

        setupLoadingView();
        getNewsStoryUrlText();
    }

    private void getNewsStoryUrlText() {
        Intent mainActivityIntent = getIntent();
        if (mainActivityIntent != null) {
            newsStoryItemUrl = mainActivityIntent.getStringExtra(Constants.NEWS_STORY_URL_KEY);
            newsStoryItemText = mainActivityIntent.getStringExtra(Constants.NEWS_STORY_TITLE_KEY);
        }

        if (newsStoryItemUrl != null) {
            showWebView();
            hideLoadingIndicator();
        } else if (newsStoryItemText != null) {
            showTextView();
            hideLoadingIndicator();
        } else {
            setEmptyView();
            hideLoadingIndicator();
        }
    }

    private void setupLoadingView() {
        webView = findViewById(R.id.news_story_web_view);
        newsStoryTextView = findViewById(R.id.news_story_text_text_view);
        loadingIndicator = findViewById(R.id.detail_loading_indicator);
        detailEmptyTextView = findViewById(R.id.detail_empty_text_view);

        webView.setVisibility(View.GONE);
        detailEmptyTextView.setVisibility(View.GONE);
        newsStoryTextView.setVisibility(View.GONE);
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    private void showTextView() {
        newsStoryTextView.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);
    }

    private void showWebView() {
        newsStoryTextView.setVisibility(View.GONE);
        detailEmptyTextView.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(newsStoryItemUrl);
    }

    private void hideLoadingIndicator() {
        loadingIndicator.setVisibility(View.GONE);
    }

    private void setEmptyView() {
        detailEmptyTextView.setVisibility(View.VISIBLE);
        newsStoryTextView.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
    }
}
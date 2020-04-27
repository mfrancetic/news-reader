package com.example.newsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private List<NewsStory> newsStoryList;
    private RecyclerViewClickListener onClickListener;

    public NewsRecyclerViewAdapter(List<NewsStory> newsStoryList, RecyclerViewClickListener onClickListener) {
        this.newsStoryList = newsStoryList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NewsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View notesView = layoutInflater.inflate(R.layout.main_news_list_item, parent, false);

        return new ViewHolder(notesView, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.ViewHolder holder, int position) {
        NewsStory newsStory = newsStoryList.get(position);

        TextView newsTitleTextView = holder.newsTitleTextView;
        TextView newsAuthorTextView = holder.newsAuthorTextView;
        TextView newsTimeTextView = holder.newsTimeTextView;
        TextView newsScoreTextView = holder.newsScoreTextView;

        newsTitleTextView.setText(newsStory.getTitle());
        newsAuthorTextView.setText(newsStory.getBy());
        newsTimeTextView.setText(String.valueOf(newsStory.getTime()));
        newsScoreTextView.setText(String.valueOf(newsStory.getScore()));
    }

    @Override
    public int getItemCount() {
        if (newsStoryList != null) {
            return newsStoryList.size();
        } else {
            return 0;
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView newsTitleTextView;
        public TextView newsAuthorTextView;
        public TextView newsTimeTextView;
        public TextView newsScoreTextView;

        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            onClickListener = listener;
            itemView.setOnClickListener(this);

            newsTitleTextView = itemView.findViewById(R.id.news_title_text_view);
            newsAuthorTextView = itemView.findViewById(R.id.news_author_text_view);
            newsTimeTextView = itemView.findViewById(R.id.news_time_text_view);
            newsScoreTextView = itemView.findViewById(R.id.news_score_text_view);

        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v, getAdapterPosition());
        }
    }
}
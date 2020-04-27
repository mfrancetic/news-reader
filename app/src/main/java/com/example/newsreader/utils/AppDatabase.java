package com.example.newsreader.utils;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.newsreader.NewsStory;

@Database(entities = {NewsStory.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract NewsStoryDao newsStoryDao();
}
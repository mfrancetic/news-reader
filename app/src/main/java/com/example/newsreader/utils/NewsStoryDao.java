package com.example.newsreader.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newsreader.NewsStory;

import java.util.List;

@Dao
public interface NewsStoryDao {

    @Query("SELECT * FROM newsstory")
    List<NewsStory> getAll();

    @Query("SELECT * FROM newsstory WHERE id LIKE :id LIMIT 1")
    NewsStory findById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<NewsStory> newsStories);

    @Query("DELETE FROM newsstory")
    void deleteAll();
}
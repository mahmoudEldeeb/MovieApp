package com.eldeeb.movieapp.model.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SearchDao {
    @Insert
    void insert(SearchModel searrchModel);

   @Query("DELETE FROM search_table where id NOT IN (SELECT id from search_table ORDER BY id DESC LIMIT 10)")
    void delete();
    @Query("SELECT * from search_table")
    LiveData<List<SearchModel>> getSearchItems();
}

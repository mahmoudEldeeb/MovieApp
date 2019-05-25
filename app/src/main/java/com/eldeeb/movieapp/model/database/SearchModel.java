package com.eldeeb.movieapp.model.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "search_table",indices = {@Index(value = "searchItem", unique = true)})

public class SearchModel {

    @NonNull
    @ColumnInfo(name = "searchItem")
    public String searchItem;
    @PrimaryKey(autoGenerate = true)
    public int id;
    public SearchModel(String searchItem) {
        this.searchItem = searchItem;
    }
    public String getSearchItem() {
        return searchItem;
    }

}

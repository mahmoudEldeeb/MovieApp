package com.eldeeb.movieapp.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {SearchModel.class},version = 1)

public abstract class DatabaseMoviess extends RoomDatabase {
    public abstract SearchDao searchDao();
    private static volatile DatabaseMoviess INSTANCE;

    public static DatabaseMoviess getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseMoviess.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseMoviess.class, "movies_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


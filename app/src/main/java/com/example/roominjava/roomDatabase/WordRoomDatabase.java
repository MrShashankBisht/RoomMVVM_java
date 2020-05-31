package com.example.roominjava.roomDatabase;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roominjava.model.Word;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    private static volatile WordRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WordRoomDatabase getInstance(final Application application){
        if(INSTANCE == null){
            WeakReference<Application> contextWeakReference = new WeakReference<>(application);
//            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(contextWeakReference.get(),WordRoomDatabase.class,"Word_Database").build();
                }
//            }
        }
        return INSTANCE;
    }

}

package com.example.roominjava;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roominjava.model.Word;
import com.example.roominjava.roomDatabase.WordDao;
import com.example.roominjava.roomDatabase.WordRoomDatabase;

import java.lang.ref.WeakReference;
import java.util.List;

public class WordRepository {

    private WordDao wordDao;

    private LiveData<List<Word>> listLiveData;

    public WordRepository(Application application){
        WeakReference<Application> contextWeakReference = new WeakReference<>(application);
        WordRoomDatabase database = WordRoomDatabase.getInstance(contextWeakReference.get());
        wordDao = database.wordDao();
        listLiveData = wordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getListLiveData() {
        return listLiveData;
    }

    public void insert(Word word){
        WordRoomDatabase.databaseWriterExecutor.execute(() -> {
            wordDao.insert(word);
        });
    }

}

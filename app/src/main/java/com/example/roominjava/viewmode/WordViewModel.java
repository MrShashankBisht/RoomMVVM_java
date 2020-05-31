package com.example.roominjava.viewmode;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roominjava.WordRepository;
import com.example.roominjava.model.Word;

import java.lang.ref.WeakReference;
import java.util.List;

public class WordViewModel extends AndroidViewModel {

    public WordRepository mRepository;

    public LiveData<List<Word>> liveData;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        liveData = mRepository.getListLiveData();
    }


    public LiveData<List<Word>> getLiveData() {
        return liveData;
    }

    public void insertWordData(Word word){
        if(mRepository != null){
            mRepository.insert(word);
        }
    }
}

package com.example.roominjava.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

//    declaring entity
    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

//    creating methods

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    public String getWord(){return this.word;}

    public int getId() {
        return id;
    }
}

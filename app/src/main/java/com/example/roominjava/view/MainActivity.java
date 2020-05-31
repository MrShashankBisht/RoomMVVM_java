package com.example.roominjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.roominjava.R;
import com.example.roominjava.adapter.WordListAdapter;
import com.example.roominjava.model.Word;
import com.example.roominjava.viewmode.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn;
    RecyclerView recyclerView;

    WordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.name);
        btn = findViewById(R.id.submit);
        recyclerView = findViewById(R.id.recyclerView);

        WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(WordViewModel.class);

        viewModel.getLiveData().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                here i will create entity and send it to view Model
                Word word = new Word();
                if(!TextUtils.isEmpty(editText.getText())) {
                    word.setWord(editText.getText().toString());
                    viewModel.insertWordData(word);
                }
            }
        });

    }
}

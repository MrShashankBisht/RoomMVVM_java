package com.example.roominjava.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roominjava.R;
import com.example.roominjava.model.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    static class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private final TextView userId;
        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            userId = itemView.findViewById(R.id.id);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
            holder.userId.setText(""+current.getId());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
            holder.userId.setText("no id");
        }
    }

    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}

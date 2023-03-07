package com.example.androidfinalassignment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private QuestionsListAdapter.OnItemClickListener listener;
    private TextView questionTextView;
    private TextView questionStatusTextView;
    private ImageButton bookmarkButton;
    public QuestionListViewHolder(@NonNull View itemView, QuestionsListAdapter.OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        this.questionTextView = itemView.findViewById(R.id.question);
        this.questionStatusTextView = itemView.findViewById(R.id.status);
        this.bookmarkButton = itemView.findViewById(R.id.bookmark_button);
        itemView.setOnClickListener(this);
    }

    public void bind(QuestionModel questionModel, int position){
        questionTextView.setText(position + ". " + questionModel.getQuestion());
        if(questionModel.getStatus()){
            questionStatusTextView.setText(R.string.answered);
            questionStatusTextView.setTextColor(Color.parseColor("#00ff00"));
        }
        else {
            questionStatusTextView.setText(R.string.unanswered);
            questionStatusTextView.setTextColor(Color.parseColor("#ff0000"));
        }
        if(questionModel.getIsBookmarked()){
            bookmarkButton.setImageLevel(1);
        }
        else{
            bookmarkButton.setImageLevel(0);
        }
    }

    @Override
    public void onClick(View view) {

        Log.d("dada", "sasssaa");
        listener.onItemClickListener(getAdapterPosition());
    }
}

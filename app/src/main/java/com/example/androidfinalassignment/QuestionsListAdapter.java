package com.example.androidfinalassignment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListAdapter extends RecyclerView.Adapter<QuestionListViewHolder> {
    private ArrayList<QuestionModel> questions = new ArrayList<>();
    private OnItemClickListener listener;
    public QuestionsListAdapter(OnItemClickListener listener, ArrayList<QuestionModel> questions){
        this.listener = listener;
        this.questions = questions;
    }
    @NonNull
    @Override
    public QuestionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_view, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListViewHolder holder, int position) {
        holder.bind(questions.get(position), position);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setList(List<QuestionModel> questions){
        this.questions.clear();
        this.questions.addAll(questions);
        notifyDataSetChanged();
    }
    public void updateItem(QuestionModel questionModel, int position){
        this.questions.set(position, questionModel);
        notifyItemChanged(position);
    }
    interface OnItemClickListener{
        void onItemClickListener(int position);
    }
}

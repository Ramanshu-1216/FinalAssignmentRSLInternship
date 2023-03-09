package com.example.androidfinalassignment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionDetailScreenFragment extends Fragment {
    private ArrayList<QuestionModel> questionModels;
    private int position;
    private QuestionDetailScreenViewModel viewModel;
    private TextView questionTextView;
    private TextView questionNumberTextView;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private ImageButton bookMarkButton;

    private Button nextButton;
    private Button previousButton;


    public QuestionDetailScreenFragment(){

    }

    public static QuestionDetailScreenFragment newInstance(ArrayList<QuestionModel> questionModels, int questionNumber) {
        QuestionDetailScreenFragment questionDetailScreenFragment =  new QuestionDetailScreenFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("questionModels", (ArrayList<? extends Parcelable>) questionModels);
        bundle.putInt("questionNumber", questionNumber);
        questionDetailScreenFragment.setArguments(bundle);
        return questionDetailScreenFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            questionModels = getArguments().getParcelableArrayList("questionModels");
            position = getArguments().getInt("questionNumber");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_detail_screen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setUpLiveData() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()));
        viewModel = viewModelProvider.get(QuestionDetailScreenViewModel.class);
        viewModel.getQuestionModelsMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<QuestionModel>>() {
            @Override
            public void onChanged(ArrayList<QuestionModel> mQuestionModels) {
                questionModels = mQuestionModels;
            }
        });
        viewModel.getPositionLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                position = integer;
                QuestionModel questionModel = questionModels.get(position);
                questionTextView.setText(questionModel.getQuestion());
                questionNumberTextView.setText(String.valueOf(integer + 1));
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionTextView = view.findViewById(R.id.questionTextView);
        questionNumberTextView = view.findViewById(R.id.questionNumberTextView);
        option1 = view.findViewById(R.id.option_1);
        option2 = view.findViewById(R.id.option_2);
        option3 = view.findViewById(R.id.option_3);
        option4 = view.findViewById(R.id.option_4);
        bookMarkButton = view.findViewById(R.id.bookmarkButton);
        previousButton = view.findViewById(R.id.previousButton);
        nextButton = view.findViewById(R.id.nextButton);
        setUpLiveData();
        viewModel.setPosition(position);
        viewModel.setQuestionModels(questionModels);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position - 1 >= 0) {
                    viewModel.setPosition(position - 1);
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position + 1 < questionModels.size()) {
                    viewModel.setPosition(position + 1);
                }
            }
        });
        bookMarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
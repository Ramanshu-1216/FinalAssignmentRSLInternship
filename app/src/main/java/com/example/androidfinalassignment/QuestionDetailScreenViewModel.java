package com.example.androidfinalassignment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class QuestionDetailScreenViewModel extends ViewModel {
    private MutableLiveData<ArrayList<QuestionModel>> questionModelsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> positionLiveData = new MutableLiveData<>();
    public QuestionDetailScreenViewModel(){

    }

    public LiveData<Integer> getPositionLiveData() {
        return positionLiveData;
    }

    public LiveData<ArrayList<QuestionModel>> getQuestionModelsMutableLiveData() {
        return questionModelsMutableLiveData;
    }

    public void setQuestionModels(ArrayList<QuestionModel> questionModels) {
        this.questionModelsMutableLiveData.postValue(questionModels);
    }

    public void setPosition(int position) {
        this.positionLiveData.postValue(position);
    }
}
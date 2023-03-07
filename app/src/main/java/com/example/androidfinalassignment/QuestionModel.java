package com.example.androidfinalassignment;

import java.util.ArrayList;

public class QuestionModel {
    private int id;
    private String question;
    private ArrayList<String> options;
    private int correctOption;
    private boolean status;
    private String selectedOption;
    private boolean isBookmarked;
    public QuestionModel(int id, String question, ArrayList<String> options, int correctOption, boolean status, String selectedOption, boolean isBookmarked){
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        this.status = status;
        this.selectedOption = selectedOption;
        this.isBookmarked = isBookmarked;
    }
    public String getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public String getSelectedOption() {
        return selectedOption;
    }
    public boolean getStatus(){
        return status;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getIsBookmarked(){
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}

package com.example.androidfinalassignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionModel implements Parcelable {
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

    protected QuestionModel(Parcel in) {
        id = in.readInt();
        question = in.readString();
        options = in.createStringArrayList();
        correctOption = in.readInt();
        status = in.readByte() != 0;
        selectedOption = in.readString();
        isBookmarked = in.readByte() != 0;
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(question);
        parcel.writeStringList(options);
        parcel.writeInt(correctOption);
        parcel.writeByte((byte) (status ? 1 : 0));
        parcel.writeString(selectedOption);
        parcel.writeByte((byte) (isBookmarked ? 1 : 0));
    }
}

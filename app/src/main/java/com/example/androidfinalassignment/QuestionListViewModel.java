package com.example.androidfinalassignment;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QuestionListViewModel extends AndroidViewModel {
    private String API_URL = "https://raw.githubusercontent.com/tVishal96/sample-english-mcqs/master/db.json";
    private MutableLiveData<ArrayList<QuestionModel>> questionsLiveData = new MutableLiveData<>();
    private MutableLiveData<RequestNetworkStatus> requestNetworkStatusLiveData = new MutableLiveData<>();
    public QuestionListViewModel(@NonNull Application application) {
        super(application);
        requestNetworkStatusLiveData.postValue(RequestNetworkStatus.IDEAL);
    }
    public LiveData<ArrayList<QuestionModel>> getQuestionsLiveData(){
        return questionsLiveData;
    }
    public LiveData<RequestNetworkStatus> getRequestNetworkStatusLiveData(){
        return requestNetworkStatusLiveData;
    }
    public void fetchQuestions(){
        new QuestionsFetch().execute(API_URL);
    }

    private class QuestionsFetch extends AsyncTask<String, String, ArrayList<QuestionModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            requestNetworkStatusLiveData.postValue(RequestNetworkStatus.IN_PROGRESS);
        }

        @Override
        protected ArrayList<QuestionModel> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            ArrayList<QuestionModel> questions = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() != 200){
                    requestNetworkStatusLiveData.postValue(RequestNetworkStatus.FAILED);

                    throw new Exception("Invalid Response");
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder questionJson = new StringBuilder();

                while((line = reader.readLine()) != null){
                    questionJson.append(line);
                }
                JSONObject jsonObject = new JSONObject(questionJson.toString());
                JSONArray itemsArray = jsonObject.getJSONArray("questions");
                int cnt = 0;
                String question;
                int id;
                int correctAnswer;
                ArrayList<String> options = new ArrayList<>();
                while(cnt < itemsArray.length()){
                    JSONObject jsonObject1 = itemsArray.getJSONObject(cnt);
                    question = jsonObject1.getString("question");
                    id = (int) jsonObject1.get("id");
                    correctAnswer = (int) jsonObject1.get("correct_option");
                    QuestionModel questionModel = new QuestionModel(id, question, options, correctAnswer, false, "",false);
                    questions.add(questionModel);
                    cnt++;
                }
            }
            catch (Exception e){
                requestNetworkStatusLiveData.postValue(RequestNetworkStatus.FAILED);
                e.printStackTrace();
            }
            finally {
                if(connection != null){
                    connection.disconnect();
                }
            }
            return questions;
        }

        @Override
        protected void onPostExecute(ArrayList<QuestionModel> questionModels) {
            super.onPostExecute(questionModels);
            requestNetworkStatusLiveData.postValue(RequestNetworkStatus.SUCCEED);
            questionsLiveData.postValue(questionModels);
        }
    }
    public enum RequestNetworkStatus{
        IN_PROGRESS, FAILED, SUCCEED, IDEAL
    }
}
package com.example.androidfinalassignment;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QuestionListFragment extends Fragment implements QuestionsListAdapter.OnItemClickListener {
    private ArrayList<QuestionModel> questions = new ArrayList<>();
    private QuestionListViewModel questionListViewModel;
    private QuestionsListAdapter adapter;

    public static QuestionListFragment newInstance() {
        return new QuestionListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()));
        questionListViewModel = viewModelProvider.get(QuestionListViewModel.class);
        setUpLiveData();
        questionListViewModel.fetchQuestions();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new QuestionsListAdapter(this, questions);
        adapter.setList(questions);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void setUpLiveData() {
        questionListViewModel.getQuestionsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<QuestionModel>>() {
            @Override
            public void onChanged(ArrayList<QuestionModel> questionModels) {
                adapter.setList(questionModels);
            }
        });
    }


    @Override
    public void onItemClickListener(int position) {
        QuestionDetailScreenFragment questionDetailScreenFragment = new QuestionDetailScreenFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.add(getParentFragment().getView().findViewById(R.id.container).getId(), questionDetailScreenFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
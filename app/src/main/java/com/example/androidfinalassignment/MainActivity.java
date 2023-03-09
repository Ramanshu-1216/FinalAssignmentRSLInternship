package com.example.androidfinalassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private SplashScreenFragment splashScreenFragment;
    private SetupScreenFragment setupScreenFragment;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null || savedInstanceState.isEmpty()) {
            splashScreenFragment = SplashScreenFragment.newInstance();
            setupScreenFragment = SetupScreenFragment.newInstance();

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, splashScreenFragment);
            fragmentTransaction.commit();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, setupScreenFragment);
                    fragmentTransaction.commit();
                }
            }, 1500);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = QuestionListFragment.getInstance().getParentFragmentManager();
        if(manager.getBackStackEntryCount() > 0){
            manager.popBackStackImmediate();
        }
        else{
            super.onBackPressed();
        }
    }
}
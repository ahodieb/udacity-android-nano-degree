package com.abdallahhodieb.nanodegree.nanodegreeportfolio;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickProject1(View v) {
        displayMessage("Project 1");
    }

    public void onClickProject2(View v) {
        displayMessage("Project 2");
    }

    public void onClickProject3(View v) {
        displayMessage("Project 3");
    }

    public void onClickProject4(View v) {
        displayMessage("Project 4");
    }

    public void onClickProject5(View v) {
        displayMessage("Project 5");
    }

    public void displayMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}


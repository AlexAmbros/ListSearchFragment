package com.example.alexander.listsearchfragment.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.alexander.listsearchfragment.R;
import com.example.alexander.listsearchfragment.asynctask.GoogleSearchTask;


public class MainActivity extends ActionBarActivity {
    private ProgressBar progressBar;
    private EditText editText;
    private View searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public View getSearchButton() {
        return searchButton;
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editText = (EditText) findViewById(R.id.editText);
        searchButton = findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSearchTask googleSearchTask = new GoogleSearchTask(MainActivity.this);
                googleSearchTask.execute(editText.getText().toString());
            }
        });
    }
}
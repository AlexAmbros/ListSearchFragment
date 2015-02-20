package com.example.alexander.listsearchfragment.ui;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.alexander.listsearchfragment.R;
import com.example.alexander.listsearchfragment.ui.model.GoogleResults;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private View searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final EditText editText = (EditText) findViewById(R.id.editText);
        searchButton = findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSearchTask googleSearchTask = new GoogleSearchTask(MainActivity.this);
                googleSearchTask.execute(editText.getText().toString());
            }
        });
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public View getSearchButton() {
        return searchButton;
    }

    public static GoogleResults performSearch(String search) throws Exception {
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept-Charset", charset);
        Reader reader = new InputStreamReader(urlConnection.getInputStream(), charset);

        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
        return results;
    }

//    private static void readStream(InputStream in) {
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(in));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    private static class GoogleSearchTask extends AsyncTask<String, Void, GoogleResults> {
        private MainActivity mainActivity;

        public GoogleSearchTask(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        protected void onPreExecute() {
            mainActivity.getProgressBar().setVisibility(View.VISIBLE);
            mainActivity.getSearchButton().setEnabled(false);
        }

        @Override
        protected GoogleResults doInBackground(String... params) {
            GoogleResults results = null;
            try {
                results = MainActivity.performSearch(params[0]);
            } catch (Exception exception) {
                Log.e(TAG, "Exception occurred : " + exception.toString());
            }
            return results;
        }

        @Override
        protected void onPostExecute(GoogleResults result) {
            mainActivity.getProgressBar().setVisibility(View.GONE);
            mainActivity.getSearchButton().setEnabled(true);
            if (result != null) {

            }
        }
    }
}

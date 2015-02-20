package com.example.alexander.listsearchfragment.api;

import com.example.alexander.listsearchfragment.model.GoogleResults;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by OAmbros on 20.02.2015.
 */
public class ApiMethods {
    private static final String GOOGLE_SEARCH_API_METHOD = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
    private static final String CHARSET = "UTF-8";

    public static GoogleResults performSearch(String search) throws Exception {
        URL url = new URL(GOOGLE_SEARCH_API_METHOD + URLEncoder.encode(search, CHARSET));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept-Charset", CHARSET);
        Reader reader = new InputStreamReader(urlConnection.getInputStream(), CHARSET);

        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
        return results;
    }
}

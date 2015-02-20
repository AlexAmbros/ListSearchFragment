package com.example.alexander.listsearchfragment.asynctask;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alexander.listsearchfragment.ui.ListSearchFragment;
import com.example.alexander.listsearchfragment.api.ApiMethods;
import com.example.alexander.listsearchfragment.model.GoogleResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OAmbros on 20.02.2015.
 */
public class GoogleSearchTask extends BaseAsyncTask<String, Void, GoogleResults> {
    private static final String TAG = GoogleSearchTask.class.getSimpleName();
    private ListSearchFragment listSearchFragment;

    public GoogleSearchTask(ListSearchFragment listSearchFragment) {
        this.listSearchFragment = listSearchFragment;
    }

    @Override
    protected void onPreExecute() {
        listSearchFragment.getProgressBar().setVisibility(View.VISIBLE);
        listSearchFragment.getSearchButton().setEnabled(false);
    }

    @Override
    protected GoogleResults performInBackground(String... params) throws Exception {
        return ApiMethods.performSearch(params[0]);
    }

    @Override
    protected void onResult(GoogleResults results) {
        listSearchFragment.getProgressBar().setVisibility(View.GONE);
        listSearchFragment.getSearchButton().setEnabled(true);
        if (results != null) {
            List<String> resultsTitlesList = convertGoogleResultToString(results);
            ListView resultsListView = listSearchFragment.getListView();
            updateListView(resultsListView, resultsTitlesList);
            resultsListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onException(Exception exception) {
        Log.e(TAG, "Exception occurred : " + exception.toString());
    }

    private void updateListView(ListView resultsListView, List<String> resultsList) {

        resultsListView.setVisibility(View.VISIBLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(listSearchFragment.getActivity(), android.R.layout.simple_list_item_1, resultsList);
        resultsListView.setAdapter(adapter);
    }

    private List<String> convertGoogleResultToString(GoogleResults results) {
        List<String> googleSearchResultsTitles = new ArrayList<>();
        List<GoogleResults.Result> resultsList = results.getResponseData().getResults();
        if (resultsList != null && resultsList.size() > 0) {
            for (GoogleResults.Result result : resultsList) {
                googleSearchResultsTitles.add(result.getTitle());
            }
        }

        return googleSearchResultsTitles;
    }
}
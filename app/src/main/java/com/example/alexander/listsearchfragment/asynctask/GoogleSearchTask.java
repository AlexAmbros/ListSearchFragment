package com.example.alexander.listsearchfragment.asynctask;

import android.util.Log;
import android.widget.Toast;

import com.example.alexander.listsearchfragment.R;
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
        listSearchFragment.updateContentViews(true);
    }

    @Override
    protected GoogleResults performInBackground(String... params) throws Exception {
        return ApiMethods.performSearch(params[0]);
    }

    @Override
    protected void onResult(GoogleResults results) {
        listSearchFragment.updateContentViews(false);
        if (results != null) {
            List<String> resultsTitlesList = convertGoogleResultToString(results);
            listSearchFragment.updateListViewContent(resultsTitlesList);
        }
    }

    @Override
    protected void onException(Exception exception) {
        Log.e(TAG, "Exception occurred : " + exception.toString());
        listSearchFragment.updateContentViews(false);
        Toast.makeText(listSearchFragment.getActivity(), R.string.error_occurred, Toast.LENGTH_SHORT).show();
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
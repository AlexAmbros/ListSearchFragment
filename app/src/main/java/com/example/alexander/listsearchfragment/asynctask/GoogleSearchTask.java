package com.example.alexander.listsearchfragment.asynctask;

import android.util.Log;
import android.view.View;

import com.example.alexander.listsearchfragment.ui.ListSearchFragment;
import com.example.alexander.listsearchfragment.api.ApiMethods;
import com.example.alexander.listsearchfragment.model.GoogleResults;

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
    protected void onResult(GoogleResults result) {
        listSearchFragment.getProgressBar().setVisibility(View.GONE);
        listSearchFragment.getSearchButton().setEnabled(true);
        if (result != null) {
            //result downloaded
        }
    }

    @Override
    protected void onException(Exception exception) {
        Log.e(TAG, "Exception occurred : " + exception.toString());
    }
}

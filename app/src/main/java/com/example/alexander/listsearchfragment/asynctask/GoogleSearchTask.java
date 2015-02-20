package com.example.alexander.listsearchfragment.asynctask;

import android.util.Log;
import android.view.View;

import com.example.alexander.listsearchfragment.ui.MainActivity;
import com.example.alexander.listsearchfragment.api.ApiMethods;
import com.example.alexander.listsearchfragment.model.GoogleResults;

/**
 * Created by OAmbros on 20.02.2015.
 */
public class GoogleSearchTask extends BaseAsyncTask<String, Void, GoogleResults> {
    private static final String TAG = GoogleSearchTask.class.getSimpleName();
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
    protected GoogleResults performInBackground(String... params) throws Exception {
        return ApiMethods.performSearch(params[0]);
    }

    @Override
    protected void onResult(GoogleResults result) {
        mainActivity.getProgressBar().setVisibility(View.GONE);
        mainActivity.getSearchButton().setEnabled(true);
        if (result != null) {
            //result downloaded
        }
    }

    @Override
    protected void onException(Exception exception) {
        Log.e(TAG, "Exception occurred : " + exception.toString());
    }
}

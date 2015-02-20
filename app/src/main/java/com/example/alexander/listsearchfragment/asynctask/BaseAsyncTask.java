package com.example.alexander.listsearchfragment.asynctask;

import android.os.AsyncTask;

/**
 * Created by OAmbros on 20.02.2015.
 */
public abstract class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    private Exception exception;

    @Override
    protected Result doInBackground(Params... params) {
        Result result = null;
        try {
            result = performInBackground(params);
        } catch (Exception exception) {
            this.exception = exception;
        }
        return result;
    }

    protected void onPostExecute(Result result) {
        if (exception == null) {
            onResult(result);
        } else {
            onException(exception);
        }
    }

    protected abstract Result performInBackground(Params... params) throws Exception;
    protected abstract void onResult(Result result);
    protected abstract void onException(Exception exception);
}

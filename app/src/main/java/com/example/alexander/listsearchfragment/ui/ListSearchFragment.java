package com.example.alexander.listsearchfragment.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.alexander.listsearchfragment.R;
import com.example.alexander.listsearchfragment.asynctask.GoogleSearchTask;

/**
 * Created by alexander on 19.02.15.
 */
public class ListSearchFragment extends Fragment {
    private ProgressBar progressBar;
    private EditText editText;
    private View searchButton;
    private View rootView;
    private ListView resultsListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.search_list_fragment_layout, null);
        initViews(rootView);

        return rootView;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public View getSearchButton() {
        return searchButton;
    }

    public ListView getListView() {
        return resultsListView;
    }

    private void initViews(View rootView) {
        resultsListView = (ListView) rootView.findViewById(R.id.results_list_view);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        editText = (EditText) rootView.findViewById(R.id.search_edit_text);
        searchButton = rootView.findViewById(R.id.start_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSearchTask googleSearchTask = new GoogleSearchTask(ListSearchFragment.this);
                googleSearchTask.execute(editText.getText().toString());
            }
        });
    }
}
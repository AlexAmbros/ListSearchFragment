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
import com.example.alexander.listsearchfragment.adapters.ResultSearchAdapter;
import com.example.alexander.listsearchfragment.asynctask.GoogleSearchTask;

import java.util.List;

/**
 * Created by alexander on 19.02.15.
 */
public class ListSearchFragment extends Fragment {
    private EditText editText;
    private ProgressBar progressBar;
    private ListView resultsListView;
    private View searchButton;
    private View rootView;

    private ResultSearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.search_list_fragment_layout, null);
        initViews(rootView);

        return rootView;
    }

    public void updateContentViews(boolean isLoadingStarted) {
        searchButton.setEnabled(!isLoadingStarted);
        progressBar.setVisibility(isLoadingStarted? View.VISIBLE : View.GONE);
        resultsListView.setVisibility(isLoadingStarted? View.GONE : View.VISIBLE);
    }

    public void updateListViewContent(List<String> resultsList) {
        adapter.setItems(resultsList);
    }

    private void initViews(View rootView) {
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        editText = (EditText) rootView.findViewById(R.id.search_edit_text);
        searchButton = rootView.findViewById(R.id.start_search_button);

        adapter = new ResultSearchAdapter(this.getActivity(), R.layout.search_list_item);
        resultsListView = (ListView) rootView.findViewById(R.id.results_list_view);
        resultsListView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSearchTask googleSearchTask = new GoogleSearchTask(ListSearchFragment.this);
                googleSearchTask.execute(editText.getText().toString());
            }
        });
    }
}
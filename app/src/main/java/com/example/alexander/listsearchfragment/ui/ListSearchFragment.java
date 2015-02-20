package com.example.alexander.listsearchfragment.ui;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.alexander.listsearchfragment.R;

/**
 * Created by alexander on 19.02.15.
 */
public class ListSearchFragment extends Fragment {
    private View rootView;
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.search_list_fragment_layout, null);
        editText = (EditText) rootView.findViewById(R.id.editText);
        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                Uri uri = Uri.parse("http://www.google.com/#q=fish");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
//                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//                String term = editText.getText().toString();
//                intent.putExtra(SearchManager.QUERY, term);
//                startActivity(intent);
            }
        });
//        setupSearchView();
    }

//    private void setupSearchView() {
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        final SearchView searchView = (SearchView) rootView.findViewById(R.id.searchView);
//        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getActivity().getComponentName());
//        searchView.setSearchableInfo(searchableInfo);
//    }
}

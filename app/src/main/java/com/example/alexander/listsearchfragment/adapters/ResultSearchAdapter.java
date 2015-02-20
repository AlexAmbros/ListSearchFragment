package com.example.alexander.listsearchfragment.adapters;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alexander.listsearchfragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OAmbros on 20.02.2015.
 */
public class ResultSearchAdapter extends BaseAdapter {
    private Context context;
    private int itemLayoutId;
    private List<String> items = new ArrayList<>();

    public ResultSearchAdapter(Context context, int itemLayoutId) {
        this.context = context;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = View.inflate(context, itemLayoutId, null);
        }

        String searchResult = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(Html.fromHtml(searchResult));

        return view;
    }

    public void setItems(List<String> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }
}

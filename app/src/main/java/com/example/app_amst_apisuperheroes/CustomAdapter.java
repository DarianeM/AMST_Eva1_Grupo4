package com.example.app_amst_apisuperheroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> dataList;

    public CustomAdapter(Context context, List<String> dataList) {
        super(context, 0, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        String item = dataList.get(position);

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item);

        return convertView;
    }
}
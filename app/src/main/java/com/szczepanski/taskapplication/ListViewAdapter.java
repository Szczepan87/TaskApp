package com.szczepanski.taskapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter {

    private Activity context;
    private String[] IDs;
    private String[] names;
    private String[] statuses;

    public ListViewAdapter(Activity context, String[] IDs, String[] names, String[] statuses) {
        super(context, R.layout.listview_row, IDs);

        this.context = context;
        this.IDs = IDs;
        this.names = names;
        this.statuses = statuses;
    }

    public View addView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row,null,true);

        TextView ID = rowView.findViewById(R.id.task_Id_textView);
        TextView name = rowView.findViewById(R.id.task_name_textView);
        TextView status = rowView.findViewById(R.id.task_status_textView);

        ID.setText(IDs[position]);
        name.setText(names[position]);
        status.setText(statuses[position]);

        return rowView;
    }

}

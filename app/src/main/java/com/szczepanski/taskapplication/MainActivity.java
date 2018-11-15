package com.szczepanski.taskapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView idTextView;
    private TextView nameTextView;
    private TextView statusTextView;
    Button statusButton;
    private List<Task> taskList;
    private List<String> buttonNames;
    private List<String> availableStatuses;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepData();

        ListView listView = findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);

        statusButton = findViewById(R.id.status_button);

        //nullPointer
        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStatusChangePossible()){
                    if (statusTextView.getText().toString().equals(availableStatuses.get(0)))
                        statusTextView.setText(availableStatuses.get(1));
                    else statusTextView.setText(availableStatuses.get(2));
                }
            }
        });
    }

    private boolean isStatusChangePossible() {
        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).getStatus().equals(availableStatuses.get(0))){
                return false;
            }
        }
        return true;
    }

    private void prepData(){
        availableStatuses = Arrays.asList("OPEN","TRAVELING","WORKING");
        buttonNames = Arrays.asList("START TRAVEL","START WORK","STOP");
        taskList = new ArrayList<>();
        int[] IDs = new int[]{1,2,3,4,5};
        String[] names = new String[]{"Name 1","Name 2","Name 3","Name 4","Name 5"};

        for (int i = 0; i < IDs.length; i++) {
            taskList.add(new Task(IDs[i],names[i],availableStatuses.get(0)));
        }
    }

    class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return taskList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.listview_row,null);

            idTextView = view.findViewById(R.id.id_textView);
            nameTextView = view.findViewById(R.id.name_textView);
            statusTextView = view.findViewById(R.id.status_textView);
            statusButton = view.findViewById(R.id.status_button);

            idTextView.setText(String.valueOf(taskList.get(i).getId()));
            nameTextView.setText(taskList.get(i).getName());
            statusTextView.setText(taskList.get(i).getStatus());

            switch (statusTextView.getText().toString()){
                case "OPEN":
                    statusButton.setText(buttonNames.get(0));
                    break;
                case "TRAVELING":
                    statusButton.setText(buttonNames.get(1));
                    break;
                case "WORKING":
                    statusButton.setText(buttonNames.get(2));
                    break;
            }

            return view;
        }
    }
}

package com.szczepanski.taskapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Task> taskList;
    private List<String> buttonNames;
    private List<String> availableStatuses;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepData();

        ListView listView = findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);
    }

    private void prepData() {
        availableStatuses = Arrays.asList("OPEN", "TRAVELING", "WORKING");
        buttonNames = Arrays.asList("START TRAVEL", "START WORK", "STOP");
        taskList = new ArrayList<>();

        databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.addData(new Task(1, "Save the Queen!", availableStatuses.get(0)));
        databaseHelper.addData(new Task(2, "Capture the terrorist!", availableStatuses.get(0)));
        databaseHelper.addData(new Task(3, "Search for information!", availableStatuses.get(0)));
        databaseHelper.addData(new Task(4, "Drive Aston Martin!", availableStatuses.get(0)));
        databaseHelper.addData(new Task(5, "Kiss Moneypenny!", availableStatuses.get(0)));

        Cursor cursor = databaseHelper.getData();
        while (cursor.moveToNext()) {
            taskList.add(new Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
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
        public View getView(final int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.listview_row, null);

            TextView idTextView = view.findViewById(R.id.id_textView);
            TextView nameTextView = view.findViewById(R.id.name_textView);
            final TextView statusTextView = view.findViewById(R.id.status_textView);
            final Button statusButton = view.findViewById(R.id.status_button);

            idTextView.setText(String.valueOf(taskList.get(i).getId()));
            nameTextView.setText(taskList.get(i).getName());
            statusTextView.setText(taskList.get(i).getStatus());

            statusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (isStatusChangePossible()) {
                        switch (statusTextView.getText().toString()) {
                            case "OPEN":
                                taskList.get(i).setStatus(availableStatuses.get(1));
                                statusTextView.setText(availableStatuses.get(1));
                                statusButton.setText(buttonNames.get(1));
                                break;
                            case "TRAVELING":
                                taskList.get(i).setStatus(availableStatuses.get(2));
                                statusButton.setText(buttonNames.get(2));
                                statusTextView.setText(availableStatuses.get(2));
                                break;
                            case "WORKING":
                                taskList.get(i).setStatus(availableStatuses.get(0));
                                statusButton.setText(buttonNames.get(0));
                                statusTextView.setText(availableStatuses.get(0));
                                break;
                        }
                    } else
                        Toast.makeText(MainActivity.this,
                                "One of the tasks doesn't have open status!", Toast.LENGTH_SHORT).show();
                }

                private boolean isStatusChangePossible() {
                    int counter = 0;
                    int index = -1;

                    for (int j = 0; j < taskList.size(); j++) {
                        if (!taskList.get(j).getStatus().equals(availableStatuses.get(0))){
                            counter++;
                            index = j;
                        }
                    }
                    if (counter<1) return true;
                    else if (counter == 1 && index == i) return true;
                    else return false;
                }
            });

            switch (statusTextView.getText().toString()) {
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

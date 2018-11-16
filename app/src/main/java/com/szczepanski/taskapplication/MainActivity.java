package com.szczepanski.taskapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView idTextView;
    private TextView nameTextView;
    private List<Task> taskList;
    private List<String> buttonNames;
    private List<String> availableStatuses;

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
        int[] IDs = new int[]{1, 2, 3, 4, 5};
        String[] names = new String[]{"Name 1", "Name 2", "Name 3", "Name 4", "Name 5"};

        for (int i = 0; i < IDs.length; i++) {
            taskList.add(new Task(IDs[i], names[i], availableStatuses.get(0)));
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

            idTextView = view.findViewById(R.id.id_textView);
            nameTextView = view.findViewById(R.id.name_textView);
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

                    for (int j = 0; j < taskList.size(); j++) {
                        if (!taskList.get(j).getStatus().equals(availableStatuses.get(0)))
                            return false;
                    }
                    return true;
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

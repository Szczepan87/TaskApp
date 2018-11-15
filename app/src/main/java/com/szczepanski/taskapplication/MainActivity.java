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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView idTextView;
    TextView nameTextView;
    TextView statusTextView;
    Button statusButton;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepViews();
        prepData();

        ListView listView = findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);
    }

    private void prepViews(){
        statusButton = findViewById(R.id.status_button);
    }

    private void prepData(){
        taskList = new ArrayList<>();
        int[] IDs = new int[]{1,2,3,4,5};
        String[] names = new String[]{"Name 1","Name 2","Name 3","Name 4","Name 5"};
        String[] stats = new String[]{"Status 1","Status 2","Status 3","Status 4","Status 5"};

        for (int i = 0; i < IDs.length; i++) {
            taskList.add(new Task(IDs[i],names[i],stats[i]));
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

            idTextView.setText(String.valueOf(taskList.get(i).getId()));
            nameTextView.setText(taskList.get(i).getName());
            statusTextView.setText(taskList.get(i).getStatus());

            return view;
        }
    }
}

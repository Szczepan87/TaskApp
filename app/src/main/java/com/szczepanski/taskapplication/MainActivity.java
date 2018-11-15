package com.szczepanski.taskapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView idTextView;
    private TextView nameTextView;
    private TextView statusTextView;
    private Button statusButton;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepViews();
        prepData();

    }

    private void prepViews(){
        idTextView = findViewById(R.id.id_textView);
        nameTextView = findViewById(R.id.name_textView);
        statusTextView = findViewById(R.id.status_textView);
        statusButton = findViewById(R.id.status_button);
    }

    private void prepData(){
        int[] IDs = new int[]{1,2,3,4,5};
        String[] names = new String[]{"Name 1","Name 2","Name 3","Name 4","Name 5"};
        String[] stats = new String[]{"Status 1","Status 2","Status 3","Status 4","Status 5"};

        for (int i = 0; i < IDs.length; i++) {
            taskList.add(new Task(IDs[i],names[i],stats[i]));
        }
    }
}

package com.szczepanski.taskapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView idTextView;
    private TextView nameTextView;
    private TextView statusTextView;
    private Button statusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepViews();

    }

    private void prepViews(){
        idTextView = findViewById(R.id.id_textView);
        nameTextView = findViewById(R.id.name_textView);
        statusTextView = findViewById(R.id.status_textView);
        statusButton = findViewById(R.id.status_button);
    }
}

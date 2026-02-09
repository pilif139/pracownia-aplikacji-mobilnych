package edu.zsk.zadanie09_02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reported_activity);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String className = intent.getStringExtra("class");

        TextView reportedName = findViewById(R.id.reported_name);
        TextView reportedSurname = findViewById(R.id.reported_surname);
        TextView reportedClass = findViewById(R.id.reported_class);

        reportedName.setText(name);
        reportedSurname.setText(surname);
        reportedClass.setText(className);

        Button btnAddNew = findViewById(R.id.button);
        btnAddNew.setOnClickListener(v -> finish());
    }
}

package com.filip_kasperski.zadanie30_09;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText newItemEdit;
    Button addButton;
    ListView itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        newItemEdit = findViewById(R.id.newElementEdit);
        addButton = findViewById(R.id.addButton);
        itemsList = findViewById(R.id.itemsList);

        ArrayList<String> items = new ArrayList<String>(Arrays.asList("Umyć ręce", "Umyć naczynia", "Wynieść śmieci"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.rowText, items);
        itemsList.setAdapter(arrayAdapter);
        addButton.setOnClickListener(v -> {
            String item = newItemEdit.getText().toString();
            arrayAdapter.add(item);
        });
    }
}
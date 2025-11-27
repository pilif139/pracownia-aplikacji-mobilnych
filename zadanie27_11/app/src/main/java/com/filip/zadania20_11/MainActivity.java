package com.filip.zadania20_11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edit1;
    private EditText edit2;
    private TextView text;
    private Button showToastButton;

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

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        text = findViewById(R.id.text);
        showToastButton = findViewById(R.id.showToastButton);
        showToastButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String result = edit2.getText().toString();
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sayHello(View v){
       String result = edit1.getText().toString();
       text.setText("Hello " + result);
    }
}
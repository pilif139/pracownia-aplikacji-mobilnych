package com.filip_kasperski.egzamin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button incrementBtn;
    Button decrementBtn;
    TextView likesCountTextView;
    Integer likes = 0;

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

        incrementBtn = findViewById(R.id.like);
        decrementBtn = findViewById(R.id.delete);
        likesCountTextView = findViewById(R.id.likes_count);

        incrementBtn.setOnClickListener(v -> {
            likes += 1;
            likesCountTextView.setText(String.format("%d polubień", likes));
        });

        decrementBtn.setOnClickListener(v -> {
            if(likes == 0) return;

            likes -= 1;
            likesCountTextView.setText(String.format("%d polubień", likes));
        });

    }
}
package com.filip.zadania20_11;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HelloActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hello);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) return;

        TextView loggedEmail = findViewById(R.id.loggedEmail);
        boolean isValid = bundle.getBoolean("isValid");
        String email = bundle.getString("email");
        if(isValid){
            loggedEmail.setText("Zalogowano jako "+ email);
        } else {
            loggedEmail.setText("Witaj "+ email);
            loggedEmail.setTextColor(getColor(R.color.red));
        }
    }

    public void onLogout(View view){
        finish();
    }
}
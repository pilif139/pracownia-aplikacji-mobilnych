package com.filip.zadanie13_04;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CLICK_COUNT = "click_count";
    private EditText name;
    private EditText email;
    private Button increaseBtn;
    private TextView txt1;
    private TextView txt2;
    private int clickCount = 0;

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

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        increaseBtn = findViewById(R.id.increaseBtn);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt2.setText(getString(R.string.increaseBtnLabel, clickCount));

        if (savedInstanceState != null) {
            clickCount = savedInstanceState.getInt(KEY_CLICK_COUNT, 0);
            txt2.setText(getString(R.string.increaseBtnLabel, clickCount));
        }

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String name2 = name.getText() != null ? name.getText().toString().trim() : "";
                String email2 = email.getText() != null ? email.getText().toString().trim() : "";

                if(!name2.isEmpty() && !email2.isEmpty()){
                    txt1.setText(getString(R.string.welcome, name2, email2));
                }else{
                    txt1.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        };

        email.addTextChangedListener(textWatcher);
        name.addTextChangedListener(textWatcher);

        increaseBtn.setOnClickListener(v -> {
            String name2 = name.getText() != null ? name.getText().toString().trim() : "";
            String email2 = email.getText() != null ? email.getText().toString().trim() : "";

            if (name2.isEmpty() || email2.isEmpty()) {
                Toast.makeText(this, R.string.noData, Toast.LENGTH_SHORT).show();
            } else {
                clickCount++;
                txt2.setText(getString(R.string.increaseBtnLabel, clickCount));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CLICK_COUNT, clickCount);
    }
}
package com.filip_kasperski.mobilna;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView animalsListView;
    EditText nameAndSurnameEditText;
    SeekBar ageSeekBar;
    TextView ageValueLabel;
    EditText appointmentReasonEditText;
    EditText timeEditText;
    TextView result;
    Button okBtn;
    String selectedAnimal;

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

        animalsListView = findViewById(R.id.animalsListView);
        nameAndSurnameEditText = findViewById(R.id.nameAndSurnameEditText);
        ageSeekBar = findViewById(R.id.ageSeekBar);
        ageValueLabel = findViewById(R.id.ageValueLabel);
        appointmentReasonEditText = findViewById(R.id.appointmentReasonEditText);
        timeEditText = findViewById(R.id.timeEditText);
        result = findViewById(R.id.result);
        okBtn = findViewById(R.id.okBtn);

        ArrayList<String> animals = new ArrayList<>(List.of("Pies", "Kot", "Świnka morska"));
        ArrayAdapter<String> animalsAdapter = new ArrayAdapter<>(this, R.layout.row, animals);
        animalsListView.setAdapter(animalsAdapter);

        animalsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    selectedAnimal = "Pies";
                    ageSeekBar.setMax(18);
                } else if (i == 1){
                    ageSeekBar.setMax(20);
                    selectedAnimal = "Kot";
                } else{
                    ageSeekBar.setMax(9);
                    selectedAnimal = "Świnka morska";
                }
            }
        });

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ageValueLabel.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        okBtn.setOnClickListener(v -> {
            String resultText = String.format("%s, %s, %s, %s, %s",
                    nameAndSurnameEditText.getText().toString(),
                    selectedAnimal,
                    ageValueLabel.getText().toString(),
                    appointmentReasonEditText.getText().toString(),
                    timeEditText.getText().toString()
                    );
            result.setText(resultText);
        });

    }
}
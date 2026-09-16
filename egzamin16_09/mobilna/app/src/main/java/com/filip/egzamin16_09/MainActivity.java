package com.filip.egzamin16_09;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button confirmButton;
    EditText washingMachineNumberEditText;
    TextView washingMachineNumberTextView;
    Button turnOnButton;
    TextView vacuumPowerStatusTextView;
    boolean isVacuumOn = false;

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

        confirmButton = findViewById(R.id.washingMachineBtn);
        washingMachineNumberEditText = findViewById(R.id.washingNumber);
        washingMachineNumberTextView = findViewById(R.id.washingNumberText);
        turnOnButton = findViewById(R.id.turnOnBtn);
        vacuumPowerStatusTextView = findViewById(R.id.vacuumPowerStatus);

        confirmButton.setOnClickListener(v -> onConfirmButtonClick());

        turnOnButton.setOnClickListener(v -> onTurnOnButtonClick());
    }

    protected void onConfirmButtonClick(){
        String washingMachineNumberString = washingMachineNumberEditText.getText().toString();

        if(washingMachineNumberString.isEmpty()) return;

        int washingMachineNumber = Integer.parseInt(washingMachineNumberEditText.getText().toString());
        if(washingMachineNumber >= 1 && washingMachineNumber <= 12){
            washingMachineNumberTextView.setText("Numer prania: " + washingMachineNumber);
        }
    }

    protected void onTurnOnButtonClick(){
        if(!isVacuumOn) {
            turnOnButton.setText("Wyłącz");
            vacuumPowerStatusTextView.setText("Odkurzacz włączony");
            isVacuumOn = true;
        } else{
            turnOnButton.setText("Włącz");
            vacuumPowerStatusTextView.setText("Odkurzacz wyłączony");
            isVacuumOn = false;
        }
    }
}
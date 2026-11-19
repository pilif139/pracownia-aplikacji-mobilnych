package com.filip_kasperski.zadanie19_11;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button generatePasswordBtn;
    Button submitBtn;
    EditText nameEdit;
    EditText surnameEdit;
    Spinner roleSpinner;
    EditText passwordLengthEdit;
    CheckBox upperAndLowerCaseLettersCheckbox;
    CheckBox numbersCheckbox;
    CheckBox specialCharsCheckbox;
    String generatedPassword;
    String letters = "qwertyuiopasdfghjklzxcvbnm";
    String specialChars = "!@#$%^&*()_+-=";
    ArrayList<String> roles = new ArrayList<>(
            Arrays.asList("Kierownik", "Starszy programista", "Młodszy programista", "Tester")
    );

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
        generatePasswordBtn = findViewById(R.id.generatePasswordBtn);
        submitBtn = findViewById(R.id.submitBtn);
        nameEdit = findViewById(R.id.nameEdit);
        surnameEdit = findViewById(R.id.surnameEdit);
        roleSpinner = findViewById(R.id.roleSpinner);
        passwordLengthEdit = findViewById(R.id.passwordLengthEdit);
        upperAndLowerCaseLettersCheckbox = findViewById(R.id.upperAndLowerCaseLettersCheckbox);
        numbersCheckbox = findViewById(R.id.numbersCheckbox);
        specialCharsCheckbox = findViewById(R.id.specialCharsCheckbox);

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.item, roles);
        roleSpinner.setAdapter(roleAdapter);

        generatePasswordBtn.setOnClickListener(v -> {
            generatedPassword = generatePassword(
                    Integer.parseInt(passwordLengthEdit.getText().toString()),
                    upperAndLowerCaseLettersCheckbox.isChecked(),
                    numbersCheckbox.isChecked(),
                    specialCharsCheckbox.isChecked()
            );
            Toast.makeText(this, generatedPassword, Toast.LENGTH_LONG).show();
        });

        submitBtn.setOnClickListener(v -> {
            StringBuilder notification = new StringBuilder().append("Dane pracownika: ");
            notification.append(String.format(
                    "%s %s ",
                    nameEdit.getText().toString(),
                    surnameEdit.getText().toString()
                    )
            );
            notification.append(roleSpinner.getSelectedItem().toString());
            if (generatedPassword == null) {
                generatedPassword = generatePassword(
                        Integer.parseInt(passwordLengthEdit.getText().toString()),
                        upperAndLowerCaseLettersCheckbox.isChecked(),
                        numbersCheckbox.isChecked(),
                        specialCharsCheckbox.isChecked()
                );
            }
            notification.append(String.format(" Hasło: %s", generatedPassword));
            Toast.makeText(this, notification.toString(), Toast.LENGTH_LONG).show();
        });
    }

    private String generatePassword(int length, boolean useUppercase, boolean useNumbers, boolean useSpecialChars){
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        int usedCharsCount = 0;
        if(useNumbers){
            int number = random.nextInt(11);
            result.append(number);
            usedCharsCount--;
        }
        if(useSpecialChars){
            int idx = random.nextInt(specialChars.length());
            result.append(specialChars.charAt(idx));
            usedCharsCount--;
        }

        for (int i = 0; i<length-usedCharsCount; i++){
            int idx = random.nextInt(letters.length());
            char selectedChar = letters.charAt(idx);
            if(useUppercase && random.nextBoolean()) {
                result.append(String.valueOf(selectedChar).toUpperCase());
            }else{
                result.append(selectedChar);
            }
        }

        return result.toString();
    }
}
package com.filip_kasperski.zadanie;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {
    String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    TextView encodedText;
    EditText textToEncode;
    EditText keyValue;
    Button encodeBtn;
    Button saveToFileBtn;

    private final ActivityResultLauncher<String> saveFile =
            registerForActivityResult(new ActivityResultContracts.CreateDocument("text/plain"), uri -> {
                if (uri == null) return;
                try (OutputStream out = getContentResolver().openOutputStream(uri)) {
                    out.write(encodedText.getText().toString().getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    Toast.makeText(this, "Błąd zapisu", Toast.LENGTH_SHORT).show();
                }
            });

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

        encodedText = findViewById(R.id.encodedText);
        textToEncode = findViewById(R.id.textToEncode);
        keyValue = findViewById(R.id.keyValue);
        encodeBtn = findViewById(R.id.encodeBtn);
        saveToFileBtn = findViewById(R.id.saveToFileBtn);

        encodeBtn.setOnClickListener(v -> {
            int key;
            String keyValueText = keyValue.getText().toString();
            if(keyValueText.isEmpty()){
                key = 0;
            } else {
                try {
                    key = Integer.parseInt(keyValueText);
                }catch (NumberFormatException e){
                    key = 0;
                }
            }

            String encodedTxt = encodeText(key , textToEncode.getText().toString());
            encodedText.setText(encodedTxt);
        });

        saveToFileBtn.setOnClickListener(v -> saveFile.launch("szyfr.txt"));
    }

    protected String encodeText(int key, String textToEncode){
        StringBuilder result = new StringBuilder();

        for(int i=0; i<textToEncode.length(); i++){
            if(textToEncode.charAt(i) == ' '){
                result.append(" ");
                continue;
            }

            int position = ALPHABET.indexOf(textToEncode.charAt(i));
            if(position == -1) continue;
            int encodedPosition = Math.floorMod(position + key, 26);
            result.append(ALPHABET.charAt(encodedPosition));
        }
        return result.toString();
    }
}
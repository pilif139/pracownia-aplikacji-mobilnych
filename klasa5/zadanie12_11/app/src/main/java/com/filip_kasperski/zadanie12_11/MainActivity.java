package com.filip_kasperski.zadanie12_11;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    View colorView;
    TextView result;
    SeekBar redSeekBar;
    TextView redValue;
    SeekBar greenSeekBar;
    TextView greenValue;
    SeekBar blueSeekBar;
    TextView blueValue;
    Button updateBtn;
    int red = 255;
    int green = 255;
    int blue = 255;

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

        colorView = findViewById(R.id.colorView);
        result = findViewById(R.id.result);
        redSeekBar = findViewById(R.id.redSeekBar);
        redValue = findViewById(R.id.redValue);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        greenValue = findViewById(R.id.greenValue);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        blueValue = findViewById(R.id.blueValue);
        updateBtn = findViewById(R.id.updateBtn);
        
        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                redValue.setText(String.valueOf(i));
                colorView.setBackgroundColor(Color.rgb(i, green, blue));
                red = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                blueValue.setText(String.valueOf(i));
                colorView.setBackgroundColor(Color.rgb(red, green, i));
                blue = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                greenValue.setText(String.valueOf(i));
                colorView.setBackgroundColor(Color.rgb(red, i, blue));
                green = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        updateBtn.setOnClickListener(v -> {
            result.setBackgroundColor(Color.rgb(red, green, blue));
        });
    }
}
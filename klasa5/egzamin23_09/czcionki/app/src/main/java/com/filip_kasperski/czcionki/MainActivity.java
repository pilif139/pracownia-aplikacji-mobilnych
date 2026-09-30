package com.filip_kasperski.czcionki;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SeekBar sizeSeekBar;
    TextView size;
    TextView quote;
    Button nextQuoteButton;
    static String[] quotes = new String[]{
            "Dzień dobry", "Good morning", "Buenos dias"
    };
    int currentQuote = 0;


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

        sizeSeekBar = findViewById(R.id.sizeSeekBar);
        size = findViewById(R.id.size);
        quote = findViewById(R.id.quote);
        nextQuoteButton = findViewById(R.id.nextQuoteButton);

        nextQuoteButton.setOnClickListener(v -> clickOnNextQuoteButton());

        sizeSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                size.setText("Rozmiar: " + progress);
                size.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void clickOnNextQuoteButton(){
        if(currentQuote == 2){
            currentQuote = 0;
        } else{
            currentQuote += 1;
        }

        quote.setText(quotes[currentQuote]);
    }
}
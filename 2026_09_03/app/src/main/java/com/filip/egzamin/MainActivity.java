package com.filip.egzamin;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView result = null;
    TextView score = null;
    Button shootButton = null;
    Button resetButton = null;
    ImageView[] images = new ImageView[5];

    int scoreCount = 0;

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

        result = findViewById(R.id.result);
        score = findViewById(R.id.score);
        shootButton = findViewById(R.id.shoot_button);
        resetButton = findViewById(R.id.reset_button);

        images[0] = findViewById(R.id.image1);
        images[1] = findViewById(R.id.image2);
        images[2] = findViewById(R.id.image3);
        images[3] = findViewById(R.id.image4);
        images[4] = findViewById(R.id.image5);

        result.setText(getString(R.string.result, 0));
        score.setText(getString(R.string.score, scoreCount));

        shootButton.setOnClickListener(v -> gamble());
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void gamble(){
        Drawable[] drawables = new Drawable[]{
                AppCompatResources.getDrawable(this, R.drawable.k1),
                AppCompatResources.getDrawable(this, R.drawable.k2),
                AppCompatResources.getDrawable(this, R.drawable.k3),
                AppCompatResources.getDrawable(this, R.drawable.k4),
                AppCompatResources.getDrawable(this, R.drawable.k5),
                AppCompatResources.getDrawable(this, R.drawable.k6)
        };

        int sum = 0;

        int[] drawnNumbers = new int[]{0, 0, 0, 0, 0};
        for (int i = 0; i < images.length; i++) {
            int random = (int) (Math.random() * 5) + 1;
            drawnNumbers[i] += 1;
            images[i].setImageDrawable(drawables[random - 1]);
        }

        for (int i = 0; i < drawnNumbers.length; i++) {
            if(drawnNumbers[i] > 1){
                sum += i * drawnNumbers[i];
            }
        }
        result.setText(getString(R.string.result, sum));
        scoreCount += sum;
        score.setText(getString(R.string.score, scoreCount));
    }

    private void resetGame() {
        scoreCount = 0;
        score.setText(getString(R.string.score, scoreCount));
        result.setText(getString(R.string.result, 0));
        for (ImageView image : images) {
            image.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.question));
        }
    }
}
package com.filip.zadanie20_04;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int[] images = {
            R.drawable.cat1,
            R.drawable.cat2,
            R.drawable.cat3,
            R.drawable.cat4,
            R.drawable.cat5,
            R.drawable.cat6,
            R.drawable.cat7,
            R.drawable.cat8,
            R.drawable.cat9,
            R.drawable.cat10,
            R.drawable.cat11,
            R.drawable.cat12,
            R.drawable.cat1,
            R.drawable.cat2,
            R.drawable.cat3,
            R.drawable.cat4
    };

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

        LinearLayout container = findViewById(R.id.imageContainer);

        for (int image : images) {

            ImageView imageView = getImageView(image);

            container.addView(imageView);
        }
    }

    @NonNull
    private ImageView getImageView(int image) {
        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(400, 400);

        params.setMargins(20, 0, 20, 0);

        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(image);

        imageView.setOnClickListener(v -> {
            ImageDialogFragment dialog =
                    ImageDialogFragment.newInstance(image);

            dialog.show(getSupportFragmentManager(), "image_dialog");
        });
        return imageView;
    }
}
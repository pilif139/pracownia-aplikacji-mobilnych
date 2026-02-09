package com.filip.zadanie01_02;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FormFragment.OnFormSubmitListener {

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
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, FormFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onFormSubmit(String email, String firstName, String lastName) {
        DisplayFragment displayFragment = DisplayFragment.newInstance(email, firstName, lastName);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, displayFragment)
                .addToBackStack(null)
                .commit();
    }
}

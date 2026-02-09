package edu.zsk.zadanie09_02;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Button saveBtn = findViewById(R.id.save_button);
        saveBtn.setOnClickListener(v -> {
            EditText nameInput = findViewById(R.id.name);
            EditText surnameInput = findViewById(R.id.surname);
            EditText classNameInput = findViewById(R.id.classname);

            String name = nameInput.getText().toString().trim();
            String surname = surnameInput.getText().toString().trim();
            String classname = classNameInput.getText().toString().trim();

            if (name.isEmpty() || surname.isEmpty() || classname.isEmpty()) {
                Toast.makeText(MainActivity.this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, ReportedActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("surname", surname);
            intent.putExtra("classname", classname);

            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Dodaję uwagę…");
            progressDialog.setMessage("Proszę czekać");
            progressDialog.setCancelable(false);
            progressDialog.show();

            nameInput.setText("");
            surnameInput.setText("");
            classNameInput.setText("");

            new Handler().postDelayed(() -> {
                progressDialog.dismiss();
                startActivity(intent);
            }, 2000);
        });
    }
}
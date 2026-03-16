package edu.zsk.egzamin16_03;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
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
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    final String CHANNEL_ID = "my_channel";
    final String CHANNEL_NAME = "My Channel";

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

        TextView wiek = findViewById(R.id.wiek);

        ListView list = findViewById(R.id.gatunki);
        ArrayList<String> gatunki = new ArrayList<String>(Arrays.asList("Pies", "Kot", "Świnka morska"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.Row, gatunki);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapter, View view, int itemInt, long itemLong){
                var elementWybrany = (String) list.getItemAtPosition(itemInt);
                switch (elementWybrany){
                    case "Pies":
                        wiek.setText("18");
                        break;
                    case "Kot":
                        wiek.setText("20");
                        break;
                    case "Świnka morska":
                        wiek.setText("9");
                        break;
                }
            }
        });

        SeekBar seekBar = findViewById(R.id.ile_lat);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                var value = String.valueOf(progress);
                wiek.setText(value);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v->{
            String wlasciciel = ((EditText)findViewById(R.id.wlasciciel)).getText().toString();
            String gatunek = ((ListView)findViewById(R.id.gatunki)).getSelectedItem().toString();
            String celWizyty = ((EditText)findViewById(R.id.cel_wizyty)).getText().toString();
            String czas = ((EditText)findViewById(R.id.czas)).getText().toString();


            Uri ringtone =
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationChannel channel = new NotificationChannel(this.CHANNEL_ID, this.CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Notification notification =
                    new NotificationCompat.Builder(this, this.CHANNEL_ID)
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setContentTitle("Powiadomienie")
                            .setContentText(String.format("%s, %s, %s, %s", wlasciciel, gatunek, celWizyty, czas))
                            .setSound(ringtone)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .build();
            int id = 1;
            notificationManager.notify(id, notification);
        });
    }
}
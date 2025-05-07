package com.example.ainten;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText hour, minute, judul, share, call;
    private Button evan, elan, bimo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        call = findViewById(R.id.call);
        share = findViewById(R.id.share);
        evan = findViewById(R.id.evan);
        elan = findViewById(R.id.elan);
        bimo = findViewById(R.id.bimo);
        Button btn_share = findViewById(R.id.btn_share);
        Button btn_call = findViewById(R.id.btn_call);

        evan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+6285877158827" + "&text=" + "Tolong saya"));
                startActivity(intent);
            }
        });

        elan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+6281997727796" + "&text=" + "Hehe"));
                startActivity(intent);
            }
        });

        bimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+6288801981440" + "&text=" + "Oy"));
                startActivity(intent);
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = share.getText().toString();

                if (textToShare.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter text to share", Toast.LENGTH_SHORT).show();
                } else {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.setType("text/plan");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);

                    startActivity(Intent.createChooser(shareIntent, "Share via"));


                }
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String panggil = call.getText().toString().trim();

                if (panggil.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Masukkan nomor untuk dipanggil", Toast.LENGTH_SHORT).show();
                } else if (!panggil.matches("\\d{1,13}")) {
                    Toast.makeText(MainActivity.this, "Masukkan nomor yang valid (hanya angka, maksimal 13 digit)", Toast.LENGTH_SHORT).show();
                } else {

                    Intent Panggil = new Intent(Intent.ACTION_DIAL);
                    Panggil.setData(Uri.parse("tel:" + panggil));
                    startActivity(Panggil);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = share.getText().toString();

                if (textToShare.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter text to share", Toast.LENGTH_SHORT).show();
                } else {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.setType("text/plan");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);

                    startActivity(Intent.createChooser(shareIntent, "Share via"));


                }
            }
        });
    }
}















//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String title = judul.getText().toString();
//                String jam = hour.getText().toString();
//                String menit = minute.getText().toString();
//
//                if (jam.isEmpty() || menit.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Masukkan jam dan menit", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                int Jam = Integer.parseInt(jam);
//                int Menit = Integer.parseInt(menit);
//
//                if (Jam < 0 || Jam > 23 || Menit < 0 || Menit > 59) {
//                    Toast.makeText(MainActivity.this, "Masukkan waktu dengan benar", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                setAlarm(Jam, Menit, title);
//            }
//        });
//    }

//    private void setAlarm(int Jam, int Menit, String title) {
//        if (Jam < 0 || Jam > 23 || Menit < 0 || Menit > 59) {
//            Toast.makeText(this, "Jam atau Menit tidak valid", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Intent intent = new Intent();
//        intent.setAction(AlarmClock.ACTION_SET_ALARM);
//        intent.putExtra(AlarmClock.EXTRA_HOUR, Jam);
//        intent.putExtra(AlarmClock.EXTRA_MINUTES, Menit);
//        intent.putExtra(AlarmClock.EXTRA_MESSAGE, title);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        } else {
//            Toast.makeText(MainActivity.this, "Tidak tersedia aplikasi alarm", Toast.LENGTH_SHORT).show();
//        }
//
//
//        try {
//            if (intent.resolveActivity(getPackageManager()) != null) {
//                startActivity(intent);
//            } else {
//                Toast.makeText(MainActivity.this, "Tidak tersedia aplikasi alarm", Toast.LENGTH_SHORT).show();
//            }
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(MainActivity.this, "Tidak tersedia aplikasi alarm", Toast.LENGTH_SHORT).show();
//        }
//    }
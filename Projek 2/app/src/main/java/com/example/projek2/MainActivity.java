package com.example.projek2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAB=MainActivity.class.getSimpleName();

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
        Log.d(LOG_TAB, "....");
        Log.d(LOG_TAB, "onCreate");
    }

    private void lifeCycle(String event) {
        Log.d(LOG_TAB, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifeCycle("onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifeCycle("onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        lifeCycle("onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifeCycle("onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifeCycle("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifeCycle("onDestroy");
    }

    public void open(View view) {
        Intent intent = new Intent(MainActivity.this, Seconds.class);
        startActivity(intent);
    }


}
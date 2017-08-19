package com.example.brunofrani.photoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.brunofrani.photoapp.R;

public class LoginActivity extends AppCompatActivity {

    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = (Button) findViewById(R.id.gotophoto);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), PhotoActivity.class);
                startActivity(in);
            }
        });

    }
}

package com.example.brunofrani.photoapp.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.brunofrani.photoapp.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Bruno Frani on 17/08/2017.
 */

public class PhotoActivity extends AppCompatActivity {




    Button gotoprofile;
    FirebaseAuth auth;
    Toolbar customtoolbar;
    FirebaseAuth.AuthStateListener authListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);


        gotoprofile =(Button)findViewById(R.id.button_gotoProfile);
        customtoolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(customtoolbar);

        auth=FirebaseAuth.getInstance();




        gotoprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this,UserProfile.class);
                startActivity(intent);
            }
        });












    }}








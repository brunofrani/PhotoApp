package com.example.brunofrani.photoapp.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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


    Button takephoto;
    Button signout;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_photo);
        takephoto=(Button) findViewById(R.id.intentButon);
        signout =(Button) findViewById(R.id.button_signout);

        auth=FirebaseAuth.getInstance();



        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                auth.signOut();
Log.d("tag","signout called ");



              /*  authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            // User is signed in
                            Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                        } else {
                            // User is signed out
                            Log.d("TAG", "onAuthStateChanged:signed_out");


                            Intent in = new Intent(PhotoActivity.this, LoginActivity.class);
                            startActivity(in);
                            finish();
                        }
                        // ...
                    }
                };*/


// this listener will be called when there is change in firebase user session
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Log.d("tag","get current udìser called called ");
                        if (user == null) {
                            // user auth state is changed - user is null
                            // launch login activity

                            Intent in = new Intent(PhotoActivity.this, LoginActivity.class);
                            startActivity(in);
                            finish();
                        }

                        else{
                            Toast.makeText(getApplicationContext(),"User loged in ",Toast.LENGTH_LONG).show();


                        }
                    }
                };

                auth.addAuthStateListener(authListener);
            }
        });


        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);


                String picName = getPicNAme();
                File imgFile = new File (picDir,picName);
                Uri pauli = Uri.fromFile(imgFile);
                in.putExtra(MediaStore.EXTRA_OUTPUT,pauli);

                startActivityForResult(in,100);




            }
        });}



    private String getPicNAme() {
        String photoName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return photoName +".jpg";

    }



    }








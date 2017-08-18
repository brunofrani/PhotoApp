package com.example.brunofrani.photoapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Bruno Frani on 17/08/2017.
 */

public class PhotoActivity extends AppCompatActivity {


    Button takephoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_photo);
        takephoto=(Button) findViewById(R.id.intentButon);

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





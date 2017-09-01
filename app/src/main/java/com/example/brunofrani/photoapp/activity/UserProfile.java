package com.example.brunofrani.photoapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;

import com.example.brunofrani.photoapp.R;
import com.example.brunofrani.photoapp.classes.UserSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.description;

public class UserProfile extends AppCompatActivity {


    EditText name;
    EditText age;
    Spinner gender;
    Button changePhoto ;
    Button openGallery;
    ImageView profileImg;
    Button save;
    TextView currentUser;
    Button friends;


    Button signout;

    FirebaseAuth auth;

    FirebaseAuth.AuthStateListener authListener;


    private DatabaseReference dbref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        name = (EditText) findViewById(R.id.name_profile);
        age=(EditText)findViewById(R.id.email_profile);
        gender = (Spinner) findViewById(R.id.gender_spinner);
        changePhoto =(Button)findViewById(R.id.button_changePhoto);
        openGallery =(Button)findViewById(R.id.button_openGallery);
        profileImg = (ImageView)findViewById(R.id.profilephoto);
        save =(Button)findViewById(R.id.button_saveprofile);
        signout = (Button)findViewById(R.id.button_signout);
        currentUser = (TextView)findViewById(R.id.text_currentEmail);
        friends =(Button) findViewById(R.id.button_friends);

        dbref=FirebaseDatabase.getInstance().getReference("users");

        auth= FirebaseAuth.getInstance();

        Log.d("tag", String.valueOf(dbref));

        currentUser.setText(getuserEmail());


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        gender.setAdapter(adapter);


        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String usergender = gender.getSelectedItem().toString();


                final String username = name.getText().toString();
               final int userage = Integer.parseInt(age.getText().toString());
              // final String userdescription = gender.



                String userEmail = getuserEmail().replace('.','_');

                String uid = dbref.push().getKey();



                        UserSettings userset = new UserSettings(username,userage,usergender);

                        dbref.child(userEmail).setValue(userset);



            }
        });




        changePhoto.setOnClickListener(new View.OnClickListener() {
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
        });



        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.signOut();
                Log.d("tag","signout called ");

// this listener will be called when there is change in firebase user session
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Log.d("tag","get current udìser  called ");
                        if (user == null) {

                            Intent in = new Intent(UserProfile.this, LoginActivity.class);
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


        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FriendsActivity.class);
                startActivity(intent);
            }
        });

}






    private String getPicNAme() {
        String photoName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return photoName +".jpg";

    }



   /*private void setEditingEnabled(boolean enabled) {
        name.setEnabled(enabled);
        email.setEnabled(enabled);
        description.setEnabled(enabled);
        if (enabled) {
            save.setVisibility(View.VISIBLE);
        } else {
            save.setVisibility(View.GONE);
        }
    }*/

    public String getuserEmail(){

        return FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }


}


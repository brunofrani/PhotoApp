package com.example.brunofrani.photoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brunofrani.photoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.attr.password;
import static android.widget.Toast.makeText;

public class LoginActivity extends AppCompatActivity {

    Button go;
    EditText email;
    EditText passord;
    TextView register;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_loyout);

        go =(Button) findViewById(R.id.login_button);
        email = (EditText) findViewById(R.id.user_text);
        passord = (EditText)  findViewById(R.id.password_text);
        register= (TextView) findViewById(R.id.register_text);


        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, PhotoActivity.class));
            finish();}




        go = (Button) findViewById(R.id.login_button);

        go.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString();
                 String password1 = passord.getText().toString();

                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (passord.length() < 6) {

                                    passord.setError("Duhet paswword me i gjate");
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Singin failed", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, PhotoActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });




            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(in);
            }
        });

    }


    }


package com.example.brunofrani.photoapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brunofrani.photoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterActivity extends AppCompatActivity {

    Button signup;
    EditText email;
    EditText password;
    EditText username;
    FirebaseAuth sauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        email = (EditText) findViewById(R.id.email_signup);
        password= (EditText) findViewById(R.id.password_singup);

        signup = (Button) findViewById(R.id.button_signup);



        sauth = FirebaseAuth.getInstance();







        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString().trim();

                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }



                    sauth.createUserWithEmailAndPassword(email1, password1)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        finish();

                                        String uid = String.valueOf(getUid());
                                        Toast.makeText(RegisterActivity.this, uid +
                                                "" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                        Log.d("tag",uid);
                                        FirebaseStorage storage=FirebaseStorage.getInstance();
                                        StorageReference ref = storage.getReference(uid);

                                        Toast.makeText(RegisterActivity.this, "folder created" +
                                                "" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }


        });



    }


    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}

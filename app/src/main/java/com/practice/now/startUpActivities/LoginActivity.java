package com.practice.now.startUpActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.practice.now.newsActivities.NavActivity;
import com.practice.now.R;

public class LoginActivity extends AppCompatActivity {

    private EditText SignInEmail;
    private EditText SignInPass;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignInEmail = (EditText)findViewById(R.id.signinemail);
        SignInPass = (EditText)findViewById(R.id.signinpassword);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }




    public void loginButton(View view){
        String semail = SignInEmail.getText().toString().trim();
        String spass = SignInPass.getText().toString().trim();

        if(TextUtils.isEmpty(semail)){
            Toast.makeText(this,"Please enter the email id",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(spass)){
            Toast.makeText(this,"Please enter the password id",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Signing In");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(semail,spass).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Sign In Successful",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext() ,NavActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Invalid Id/Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void textClick(View view){

        finish();
        startActivity(new Intent(this,MainActivity.class));

    }
}

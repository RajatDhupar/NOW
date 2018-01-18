package com.practice.now.startUpActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.practice.now.newsActivities.NavActivity;
import com.practice.now.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button SignUpbutton;
    private TextView TextViewsignUp;
    private ProgressDialog prod;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prod = new ProgressDialog(this);
        editTextEmail = (EditText) findViewById(R.id.editTextemail);
        editTextPassword = (EditText) findViewById(R.id.editTextpassword);
        SignUpbutton = (Button) findViewById(R.id.signupbutton);
        TextViewsignUp = (TextView) findViewById(R.id.textViewsignup);


    }

    public void onClick(View view){

        String email = editTextEmail.getText().toString().trim();
        final String pass = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter the Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter the Password",Toast.LENGTH_SHORT).show();
            return;
        }

        prod.setMessage("Signing Up");
        prod.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        prod.dismiss();
                        if(task.isSuccessful())
                        {
                            finish();
                            startActivity(new Intent(getApplicationContext(),NavActivity.class));

                        }
                        else
                        {

                            if(pass.length()<=5) {
                                Toast.makeText(MainActivity.this, "Password too short..!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Email ID already exists..! Try Again",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
    }


    public void onClickk(View view){
        finish();
        startActivity(new Intent(this,LoginActivity.class));

    }

    public void withoutLogin(View view)
    {
        finish();
        startActivity(new Intent(this,DirectLoginActivity.class));
    }

}

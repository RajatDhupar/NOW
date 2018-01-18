package com.practice.now.depricatedActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.practice.now.newsActivities.ProfileActivity;
import com.practice.now.R;
import com.practice.now.startUpActivities.LoginActivity;

public class Main4Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


    }

    public void tech(View view){
            finish();
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);

            i.putExtra("url","https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
            startActivity(i);
    }

    public void sports(View view)
    {
        finish();
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);

        i.putExtra("url","https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
        startActivity(i);


    }

    public void enter(View view)
    {
        finish();
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);

        i.putExtra("url","https://newsapi.org/v1/articles?source=entertainment-weekly&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
        startActivity(i);
    }
    public void finan(View view)
    {
        finish();
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);

        i.putExtra("url","https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
        startActivity(i);
    }
    public void game(View view)
    {
        finish();
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);

        i.putExtra("url","https://newsapi.org/v1/articles?source=ign&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
        startActivity(i);
    }





    public void logout(View view){

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,LoginActivity.class));

    }
}

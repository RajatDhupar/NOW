package com.practice.now.newsActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.practice.now.R;
import com.practice.now.adaptor.NewsAdapter;
import com.practice.now.interfaces.ApiInterface;
import com.practice.now.modalClasses.Article;
import com.practice.now.modalClasses.GsonParse;
import com.practice.now.services.AutoRefreshService;
import com.practice.now.startUpActivities.LoginActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent mIntentService;
    FirebaseAuth firebaseAuth;
//  ProgressDialog progressDialog;

    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog pDialog;
    private final String TAG = "NavActivity";
    public TimerTask timerTaskService;
    public static Timer serviceTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        pDialog = new ProgressDialog(NavActivity.this);
        pDialog.setMessage("Please Wait..");


        Intent receiveProfileDataForTT = new Intent();
        String receiveProfileDataForTTString = receiveProfileDataForTT.getStringExtra("BOOLTASK");

        if(receiveProfileDataForTTString!=null)
            serviceTimer.cancel();


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(getApplicationContext(),"Press Logout to exit",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            firebaseAuth.signOut();
            startActivity(intent);
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
        Call<GsonParse> call = null;
        Log.d("Tag","Navigation Drawer Opened");


        if (id == R.id.nav_enter) {

            call = apiService.getEntertaiment();
            //int time = (int) System.currentTimeMillis();
            //IntentService called.
            //articleList is sent to the IntentService for auto-refresh functionality
            mIntentService = new Intent(NavActivity.this, AutoRefreshService.class);
            mIntentService.putExtra(AutoRefreshService.NAV_ACTIVITY_SELECTED,"enter");

            //intent.putExtra("NAVVALUE",articleList);
            //intent.putParcelableArrayListExtra("abc", articleList);
            //intent.putExtra("abc",new Article("a","b","c","d"));

        } else if (id == R.id.nav_finan) {

            //Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            //i.putExtra("url","https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
            call = apiService.getBusiness();
            mIntentService = new Intent(NavActivity.this, AutoRefreshService.class);
            mIntentService.putExtra(AutoRefreshService.NAV_ACTIVITY_SELECTED,"finan");

        } else if (id == R.id.nav_game) {

            //Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            //Call<GsonParse> call = apiService.getGaming();
            //i.putExtra("url", (Serializable) call);
           /* Intent intent = new Intent(getApplicationContext() , ProfileActivity.class);
            intent.putExtra("NAVVALUE",3);
            startActivity(intent);*/

            call = apiService.getGaming();
            mIntentService = new Intent(NavActivity.this, AutoRefreshService.class);
            mIntentService.putExtra(AutoRefreshService.NAV_ACTIVITY_SELECTED,"game");


            // i.putExtra("url","https://newsapi.org/v1/articles?source=ign&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
            //startActivity(i);

        } else if (id == R.id.nav_sport) {

            call = apiService.getSports();
            mIntentService = new Intent(NavActivity.this, AutoRefreshService.class);
            mIntentService.putExtra(AutoRefreshService.NAV_ACTIVITY_SELECTED,"sport");


            //i.putExtra("url","https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
            //startActivity(i);

        } else if (id == R.id.nav_tech) {

            //Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            call = apiService.getNews();
            mIntentService = new Intent(NavActivity.this, AutoRefreshService.class);
            mIntentService.putExtra(AutoRefreshService.NAV_ACTIVITY_SELECTED,"tech");


            //i.putExtra("url","https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4");
            //startActivity(i);

        }

        if(serviceTimer!=null)
        {
            serviceTimer.cancel();
        }

        serviceTimer = new Timer();
        timerTaskService = new TimerTask() {
            @Override
            public void run() {
                startService(mIntentService);
            }
        };
        serviceTimer.schedule(timerTaskService,0,7000);

        pDialog.show();
        call.enqueue(new Callback<GsonParse>() {
            @Override
            public void onResponse(Call<GsonParse> call, Response<GsonParse> response) {
                ArrayList<Article> articleList;
                articleList =  response.body().getArticles();
                Intent intent = new Intent(getApplicationContext() , ProfileActivity.class);
                intent.putParcelableArrayListExtra("article", articleList);

                startActivity(intent);
            }

            @Override
            public void onFailure(Call<GsonParse> call, Throwable t) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
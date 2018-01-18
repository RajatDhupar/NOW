package com.practice.now.newsActivities;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.practice.now.R;
import com.practice.now.adaptor.NewsAdapter;
import com.practice.now.modalClasses.Article;
import com.practice.now.services.AutoRefreshService;
import com.practice.now.startUpActivities.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {

    Article article;
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String TAG = MainActivity.class.getSimpleName();
    private static String url;
    private ProgressDialog progressDialog;
    ArrayList<Article> serviceArticleList;
    private ServiceStateReceiver receiver;
    //ArrayList<HashMap<String , String>> contactList;
    //List<card_data> articleList;
    List<Article> articleList;


    public class ServiceStateReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "com.practice.now.action.DATA_PROCESSED";
        ArrayList<Article> receivedArrayList;


        @Override
        public void onReceive(Context context, Intent intent) {
            receivedArrayList = intent.getParcelableArrayListExtra(AutoRefreshService.INTENT_OUT_DATA);
            mRecyclerView = (RecyclerView) findViewById(R.id.rview);
            mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(new NewsAdapter(getApplicationContext(), receivedArrayList));

        }

    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,intentFilter);
    }
*/
/*    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
*/
    //FirebaseAuth firebaseAuth;
    //String fresult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//------------------

        IntentFilter filter = new IntentFilter(ServiceStateReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ServiceStateReceiver();
        registerReceiver(receiver,filter);

//------------------

        articleList = new ArrayList<>();
        Intent intent = getIntent();
//        int navSelectedValue = intent.getIntExtra("NAVVALUE",0);
        // Log.d("TAG", String.valueOf(navSelectedValue));
        progressDialog = new ProgressDialog(this);

/*      IntentFilter statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        ServiceStateReceiver serviceStateReceiver = new ServiceStateReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(serviceStateReceiver,new IntentFilter(Constants.BROADCAST_ACTION));
*/

        ArrayList<Article> articleList = intent.getParcelableArrayListExtra("article");

/*
        registerReceiver(receiver,intentFilter);

        if(serviceArticleList!=null) {
            mRecyclerView.setAdapter(new NewsAdapter(getApplicationContext(), serviceArticleList));
        }
//---------
*/
        mRecyclerView = (RecyclerView) findViewById(R.id.rview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(progressDialog.isShowing())
            progressDialog.dismiss();

        Log.d("Tag","Calling Adaptor..");

        mRecyclerView.setAdapter(new NewsAdapter(getApplicationContext(), articleList));


//        registerReceiver(receiver,intentFilter);



/*
        call.enqueue(new Callback<GsonParse>() {
            @Override
            public void onResponse(Call<GsonParse> call, Response<GsonParse> response) {
                int statuscode = response.code();
                articleList = response.body().getArticles();

                /*Gson gson = new Gson();
                GsonParse gsonParse = gson.fromJson(response.body().toString(), GsonParse.class);

         .       for(Article data:gsonParse.getArticles()) {
                    String title = data.getTitle();
                    String des = data.getDescription();
                    String image = data.getUrlToImage();
                    String url = data.getUrl();
                    article = new Article(title, des, url, image);
                    articleList.add(article);
                }*/
/*
            }

            @Override
            public void onFailure(Call<GsonParse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
*/

/*        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GsonParse> call = null;
            if(navSelectedValue==1) {


                 call = apiService.getEntertaiment();
            }
            else if(navSelectedValue==2)
            {
//                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                call = apiService.getBusiness();
/*                call.enqueue(new Callback<GsonParse>() {
                    @Override
                    public void onResponse(Call<GsonParse> call, Response<GsonParse> response) {
                        articleList = response.body().getArticles();
                        if (pDialog.isShowing())
                            pDialog.dismiss();
                        mRecyclerView.setAdapter(new NewsAdapter(getApplicationContext(), articleList));
                        Log.d("TAG", articleList.toString());
                    }
                    @Override
                    public void onFailure(Call<GsonParse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
*/
/*            }
            else if(navSelectedValue==3)
            {
                call = apiService.getGaming();
            }
            else if(navSelectedValue==4)
            {
                call = apiService.getSports();
            }
            else if(navSelectedValue==5)
            {
                call = apiService.getNews();
            }

                call.enqueue(new Callback<GsonParse>() {
                @Override
                public void onResponse(Call<GsonParse> call, Response<GsonParse> response) {
                    articleList = response.body().getArticles();
                    if (pDialog.isShowing())
                        pDialog.dismiss();
                    mRecyclerView.setAdapter(new NewsAdapter(getApplicationContext(), articleList));
                    Log.d("TAG", articleList.toString());
                }
                @Override
                public void onFailure(Call<GsonParse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
/*
        int position = 0;
        String s = articleList.get(position).getTitle().toString();
        Log.d("TAG", s);
*/

//        mAdapter = new NewsAdapter(getApplicationContext(), articleList);
//        mRecyclerView.setAdapter(mAdapter);


    }

    // new asynctask().execute();


   /* public void logout(View view){

        firebaseAuth.signOut();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }*/


    @Override
    public void onBackPressed() {
        finish();
        unregisterReceiver(receiver);
        Intent navIntent = new Intent(getApplicationContext(),NavActivity.class);
        startActivity(navIntent);
    }

}

   /* public void onCheckboxClicked(View view)
    {
        boolean checked= ((CheckBox)view).isChecked();

        if(checked)

    }*/
/*
    public class asynctask extends AsyncTask<Void,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ProfileActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             **/
/*            mAdapter = new NewsAdapter(getApplicationContext(), articleList);
            mRecyclerView.setAdapter(mAdapter);
        }

        @Override
        protected String doInBackground(Void... arg0) {

           // HttpHandler sh = new HttpHandler();

            //Making a request to url and getting response
            //String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);

            call.enqueue(new Callback<GsonParse>() {
                             @Override
                             public void onResponse(Call<GsonParse> call, Response<GsonParse> response) {
                                 int statuscode = response.code();
                                 //articles = response.body().getArticles();

                                 Gson gson = new Gson();
                                 GsonParse gsonParse = gson.fromJson(response.body().toString(), GsonParse.class);

                                 for(Article data:gsonParse.getArticles()) {
                                     String title = data.getTitle();
                                     String des = data.getDescription();
                                     String image = data.getUrlToImage();
                                     String url = data.getUrl();
                                     article = new Article(title, des, url, image);
                                     articleList.add(article);
                                 }

                             }

                             @Override
                             public void onFailure(Call<GsonParse> call, Throwable t) {
                                 Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
                             }
                         });





          /*  if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("articles");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String title = c.getString("title");
                        String des = c.getString("description");
                        String image = c.getString("urlToImage");
                        String url = c.getString("url");


                        // tmp hash map for single contact
                       /* HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("title",title);
                        contact.put("description",des);
                        contact.put("urlToImage",image);
                        contact.put("url",url);*/
          /* {
                article = new card_data(title, des, image, url);
            }
                        articleList.add(article);
                        // adding contact to contact list
                        //contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;

        */
/*
            return null;
        }





    }

    */

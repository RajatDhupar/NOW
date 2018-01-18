package com.practice.now.depricatedActivities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 30-04-2017.
 */

public class userdbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ usercontract.NewUserInfo.TABLE_NAME+"("+ usercontract.NewUserInfo.TITLE+" TEXT,"+
                    usercontract.NewUserInfo.DESCRIP+" TEXT,"+ usercontract.NewUserInfo.URLIMAGE+" TEXT,"+
                    usercontract.NewUserInfo.URL+" TEXT);";

    public userdbhelper(Context context)
    {
        super(context , DATABASE_NAME,null,DATABASE_VERSION );
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    public void addinformation(String title , String des , String urlimage ,String url , SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(usercontract.NewUserInfo.TITLE , title);
        contentValues.put(usercontract.NewUserInfo.DESCRIP , des);
        contentValues.put(usercontract.NewUserInfo.URLIMAGE , urlimage);
        contentValues.put(usercontract.NewUserInfo.URL , url);
        db.insert(usercontract.NewUserInfo.TABLE_NAME, null,contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.brunofrani.photoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

import static android.R.attr.id;
import static android.os.Build.ID;
import static android.webkit.WebSettings.PluginState.ON;

/**
 * Created by Bruno Frani on 18/08/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static  final int DATABASE_VERSION = 1;

    private  static final String DATABASE_NAME = "Users Database";

    private static final String TABLE_NAME = "contact info";

    private static final String  _ID = "id";
    private static final String  USERNAME = "username";
    private static final String  EMAIL = "email";
    private static final String  PASSWORD="password";
    private static final String  IMAGE = "image" ;


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


            String CREATE_USERS_TABLE = "CREATE TABLE" + DATABASE_NAME +"(" +_ID + " INTEGER PRIMARY KEY," + USERNAME  +"TEXT"+EMAIL+"TEXT"+PASSWORD+"TEXT"+IMAGE+"TEXT" +")";
            db.execSQL(CREATE_USERS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);

        onCreate(db);

    }

    public static String getEMAIL() {
        return EMAIL;
    }
}

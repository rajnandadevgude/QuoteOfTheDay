package com.example.quoteoftheday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String qtdb = "myquotes.db";

    public DBHelper(Context context) {
        super(context, qtdb, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE myquotes (id INTEGER PRIMARY KEY AUTOINCREMENT, quote TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This method is called when the database needs to be upgraded.
        // You can drop the existing tables and recreate them.
        db.execSQL("DROP TABLE IF EXISTS myquotes");
        onCreate(db);
    }
}

package com.example.keybox.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class KeyBoxDatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_KEY_BOX = "create table key_box ("
            + "id integer primary key autoincrement,"
            + "key_name text,"
            + "username text,"
            + "key_content text"
            + ")";

    private Context mContext;

    public KeyBoxDatabaseHelper(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_KEY_BOX);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

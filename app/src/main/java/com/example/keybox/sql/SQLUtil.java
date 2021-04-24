package com.example.keybox.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.keybox.entity.Key;

import java.util.ArrayList;
import java.util.List;

public class SQLUtil {

    private static KeyBoxDatabaseHelper helper;

    public static void createKeyBoxDataBase(Context context) {
        helper = new KeyBoxDatabaseHelper(context,"KeyBox.db",null,1);
        helper.getWritableDatabase();
    }

    public static boolean insertKey(Context context,String keyName, String username, String keyContent) {
        helper = new KeyBoxDatabaseHelper(context,"KeyBox.db",null,1);
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("key_name", keyName);
        values.put("username", username);
        values.put("key_content", keyContent);
        long insertResult = database.insert("key_box",null,values);
        if (insertResult == -1) {
            return false;
        }
        return true;
    }

    public static boolean deleteKey(Context context, int keyId){
        helper = new KeyBoxDatabaseHelper(context,"KeyBox.db",null,1);
        SQLiteDatabase database = helper.getReadableDatabase();
        long deleteResult = database.delete("key_box","id = ?", new String[]{""+keyId+""});
        if(deleteResult == -1){
            return false;
        }
        return true;
    }

    public static List<Key> queryAllKeys(Context context) {
        helper = new KeyBoxDatabaseHelper(context,"KeyBox.db",null,1);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query("key_box",null,null,null,null,null,null);
        List<Key> keys = new ArrayList<>();
        while (cursor.moveToNext()) {
            int keyId = cursor.getInt(cursor.getColumnIndex("id"));
            String keyName = cursor.getString(cursor.getColumnIndex("key_name"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String keyContent = cursor.getString(cursor.getColumnIndex("key_content"));
            Key key = new Key(keyId, keyName, username, keyContent);
            keys.add(key);
        }
        return keys;
    }
}

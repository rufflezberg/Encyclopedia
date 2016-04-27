package com.example.russell.frogencyclopedia;

/**
 * Created by Russell on 4/5/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DatabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getInfo(String name) {
        List<String> list = new ArrayList<>();
        //write query
        Cursor cr = database.rawQuery("", null);
        cr.moveToFirst();
        list.add(cr.getString(0));
        list.add(cr.getString(1));
        list.add(cr.getString(2));
        list.add(cr.getString(3));
        list.add(cr.getString(4));
        list.add(cr.getString(5));
        list.add(cr.getString(6));
        list.add(cr.getString(7));
        list.add(cr.getString(8));
        list.add(cr.getString(9));
        list.add(cr.getString(10));
        cr.close();
        return list;
    }

    public byte[] getInfoImage(String name){
        byte[] image;
        //write query
        Cursor cr = database.rawQuery("", null);
        cr.moveToFirst();
        image=cr.getBlob(0);
        return image;
    }

    public List<byte[]> getSeeImages(){
        List<byte[]> list = new ArrayList<byte[]>();
        //write query
        Cursor cr = database.rawQuery("", null);
        cr.moveToFirst();
        while(!cr.isAfterLast()) {
            list.add(cr.getBlob(0));
            cr.moveToNext();
        }
        return list;
    }

    public List<String> getTriviaInfo(int count){
        List<String> list = new ArrayList<String>();
        //write query
        Cursor cr = database.rawQuery("", null);
        cr.moveToFirst();
        for(int i = 0; i < count; i++)
            cr.moveToNext();
        list.add(cr.getString(0));
        list.add(cr.getString(1));
        list.add(cr.getString(2));
        list.add(cr.getString(3));
        list.add(cr.getString(4));
        list.add(cr.getString(5));
        return list;
    }

    public byte[] getTriviaImage(int count){
        byte[] image;
        //write query
        Cursor cr = database.rawQuery("", null);
        cr.moveToFirst();
        for(int i = 0; i < count; i++)
            cr.moveToNext();
        image = cr.getBlob(0);
        return image;
    }

    public int getNumOfQuestions(){
        int count;
        //write query
        Cursor cr = database.rawQuery("", null);
        count = cr.getInt(0);
        return count;
    }
}

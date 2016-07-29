package com.russell.encyclopedia;

/**
 * Created by Russell on 4/5/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess extends SQLiteOpenHelper{

    private static String DB_PATH = "/data/data/com.russell.encyclopedia/databases/";

    private static String DB_NAME = "Frog Encyclopedia.db";

    private SQLiteDatabase database;

    private final Context ctx;

    //Initialize string necessary for connection to database for context
    public DatabaseAccess(Context context) {
        super(context, DB_NAME, null, 1);
        this.ctx = context;
    }

    //First time database access. Establishes file for database to copy into.
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(!dbExist){
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    //Checks to see if database exists or not.
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

        }

        if(checkDB != null){

            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    //Copies the database into device internal memory location made in createDataBase().
    private void copyDataBase() throws IOException{

        InputStream myInput = ctx.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    //Opens the database from the device internal memory.
    public void openDataBase() throws SQLException {

        try {
            copyDataBase();

        } catch (IOException e) {

            throw new Error("Error copying database");

        }

        String myPath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    //Closes the database stream.
    @Override
    public synchronized void close() {

        if(database != null)
            database.close();

        super.close();

    }

    //No code needed here as the database already exists.
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    //Database is not allowed to be upgraded except by Russell, and that will be done externally.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Database query to get the info of the animal queried for.
    public List<String> getInfo(String name) {

        List<String> list = new ArrayList<>();
        String[] arg = {name};
        Cursor cr = database.rawQuery("SELECT * FROM Frog_Data WHERE _id = ?", arg);

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
        list.add(cr.getString(11));
        list.add(cr.getString(12));
        cr.close();

        return list;
    }

    //Database query for all animal names in the database.
    public List<String> getNames(){

        List<String> list = new ArrayList<>();
        Cursor cr = database.rawQuery("SELECT _id FROM Frog_Data", null);

        cr.moveToFirst();
        list.add(cr.getString(0));

        while(cr.moveToNext()){
            list.add(cr.getString(0));
        }

        return list;
    }

    //Database query to get the image for the information activity of the animal queried for.
    public byte[] getInfoImage(String name){

        byte[] image;
        String[] arg = {name};
        Cursor cr = database.rawQuery("SELECT Info_Image FROM Frog_Images WHERE _id = ?", arg);

        cr.moveToFirst();
        image=cr.getBlob(0);

        return image;
    }

    //Database query to get the images for the see animals activity of all animals in the database.
    public List<byte[]> getSeeImages(){

        List<byte[]> list = new ArrayList<byte[]>();
        Cursor cr = database.rawQuery("SELECT See_Image FROM Frog_Images", null);

        cr.moveToFirst();

        while(!cr.isAfterLast()) {
            list.add(cr.getBlob(0));
            cr.moveToNext();
        }

        return list;
    }

    //Database query to get the information for the trivia question queried for.
    public List<String> getTriviaInfo(int count){

        List<String> list = new ArrayList<String>();
        Cursor cr = database.rawQuery("SELECT Question, Answer_1, Answer_2, Answer_3, Answer_4, Correct_Let FROM Trivia", null);

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

    //Database query to get the image associated with the trivia question queried for.
    public byte[] getTriviaImage(int count){

        byte[] image;
        Cursor cr = database.rawQuery("SELECT Trivia_Image FROM Trivia", null);

        cr.moveToFirst();

        for(int i = 0; i < count; i++)
            cr.moveToNext();

        image = cr.getBlob(0);

        return image;
    }

    //Database query to get the number of questions currently in the database.
    public int getNumOfQuestions(){

        int count;
        Cursor cr = database.rawQuery("SELECT COUNT(Question_Num) FROM Trivia", null);

        count = cr.getInt(0);

        return count;
    }
}

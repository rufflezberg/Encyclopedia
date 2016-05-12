package com.example.russell.frogencyclopedia;

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

    private static String DB_PATH = "/data/data/assets/databases/";

    private static String DB_NAME = "Frog Encyclopedia.db";

    private SQLiteDatabase database;

    private final Context ctx;


    public DatabaseAccess(Context context) {
        super(context, DB_NAME, null, 1);
        this.ctx = context;
    }

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

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = ctx.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(database != null)
            database.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

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

    public List<String> getNames(){
        List<String> list = new ArrayList<>();
        Cursor cr = database.rawQuery("", null);
        cr.moveToFirst();
        list.add(cr.getString(0));
        while(cr.moveToNext()){
            list.add(cr.getString(0));
        }
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

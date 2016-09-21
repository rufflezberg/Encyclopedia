package com.russell.encyclopedia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);

        //Set up access to the database.
        DatabaseAccess db = new DatabaseAccess(this);

        try{
            db.createDataBase();
        }catch(IOException ioe){
            throw new Error("Unable to create database");
        }

        //Navigation to the Lincoln Park Zoo website.
        ImageButton lincolnParkAd=(ImageButton)findViewById(R.id.lincolnPark);
        lincolnParkAd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent lincolnParkAd=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lpzoo.org/"));
                startActivity(lincolnParkAd);
            }
        });

        //Navigation to the see animals activity.
        ImageButton seeAnimalsNav = (ImageButton) findViewById(R.id.seeAnimalsNav);
        seeAnimalsNav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent seeAnimals = new Intent(view.getContext(), SeeAnimals.class);
                startActivityForResult(seeAnimals, 0);
            }
        });

        //Navigation to the trivia main activity
        ImageButton triviaGameNav = (ImageButton) findViewById(R.id.triviaGameNav);
        triviaGameNav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent triviaGame = new Intent (view.getContext(), TriviaMain.class);
                startActivityForResult(triviaGame, 0);
            }
        });

        //Navigation to the photo recognition activity
        Button photoRecognitionNav = (Button)findViewById(R.id.PhotoRecognitionActivity);
        photoRecognitionNav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent photoRecognition = new Intent(view.getContext(), PhotoRecognition.class);
                startActivityForResult(photoRecognition, 0);
            }
        });
    }
}
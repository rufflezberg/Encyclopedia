package com.russell.encyclopedia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);

        DatabaseAccess db = new DatabaseAccess(this);

        try{
            db.createDataBase();
        }catch(IOException ioe){
            throw new Error("Unable to create database");
        }

        ImageButton lincolnParkAd=(ImageButton)findViewById(R.id.lincolnPark);
        lincolnParkAd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent lincolnParkAd=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lpzoo.org/"));
                startActivity(lincolnParkAd);
            }
        });
        ImageButton seeAnimalsNav = (ImageButton) findViewById(R.id.seeAnimalsNav);
        seeAnimalsNav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent seeAnimals = new Intent(view.getContext(), SeeAnimals.class);
                startActivityForResult(seeAnimals, 0);
            }
        });

        ImageButton triviaGameNav = (ImageButton) findViewById(R.id.triviaGameNav);
        triviaGameNav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent triviaGame = new Intent (view.getContext(), TriviaGame.class);
                startActivityForResult(triviaGame, 0);
            }
        });
    }
}
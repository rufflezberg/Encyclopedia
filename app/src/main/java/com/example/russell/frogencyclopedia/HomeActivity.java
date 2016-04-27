package com.example.russell.frogencyclopedia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton ad=(ImageButton)findViewById(R.id.imageButton);
        ad.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view2){
                Intent ad1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lpzoo.org/"));
                startActivity(ad1);
            }
        });
        ImageButton seeFrogsNav = (ImageButton) findViewById(R.id.seeFrogsNav);
        seeFrogsNav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent seeFrogs = new Intent(view.getContext(), SeeFrogs.class);
                startActivityForResult(seeFrogs, 0);
            }
        });

        ImageButton triviaGameNav = (ImageButton) findViewById(R.id.triviaGameNav);
        triviaGameNav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view1){
                Intent triviaGame = new Intent (view1.getContext(), TriviaGame.class);
                startActivityForResult(triviaGame, 0);
            }
        });
    }
}
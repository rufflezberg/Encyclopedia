package com.russell.encyclopedia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
/**
 * Created by Russell on 4/13/2015.
 */
public class TriviaGame extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviamain);

        Button play=(Button)findViewById(R.id.play);
        ImageButton brookfieldAd=(ImageButton)findViewById(R.id.brookfield);
        Button help=(Button)findViewById(R.id.help);
        Button home=(Button)findViewById(R.id.home);

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent playNav=new Intent(view.getContext(), TriviaPlay.class);
                startActivityForResult(playNav, 0);
            }
        });

        brookfieldAd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent brookfieldAd = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.czs.org/Brookfield-ZOO/Home"));
                startActivity(brookfieldAd);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent helpNav=new Intent(view.getContext(), TriviaHelp.class);
                startActivityForResult(helpNav, 0);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent homeNav=new Intent(view.getContext(), HomeActivity.class);
                startActivityForResult(homeNav, 0);
            }
        });
    }
}
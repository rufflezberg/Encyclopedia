package com.russell.encyclopedia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Russell on 4/13/2015.
 */
public class TriviaGameOver extends TriviaPlay {

    TextView RESULTS, SCOREMESSAGE;
    String results, message1, message2, message3, message4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviagameover);

        //Checks if the answer given to the final question was correct.
        checkCorrect(correctAnswer);

        RESULTS=(TextView)findViewById(R.id.resultsText);
        SCOREMESSAGE=(TextView)findViewById(R.id.scoreText);

        results="Results: " + correctAnswers + "/10\n";
        RESULTS.setText(results);

        message1="\nYour ribbit could use some work! Use the encyclopedia and let the frogs teach their ways, so you can get better!\n";
        message2="\nYou've got the potential to have a great voice! You just need some coaching from your friendly neighborhood frogs!\n";
        message3="\nNot too shabby! With just a little more knowledge, you'll be croaking your heart out!\n";
        message4="\nNow that's what I'm talking about! You're good enough to get out there and sing with the best of them!\n";

        //Sets the text of SCOREMESSAGE depending on the results of the user's trivia performance.
        if(correctAnswers>=0 && correctAnswers<=2)
            SCOREMESSAGE.setText(message1);
        else if(correctAnswers>=3 && correctAnswers<=5)
            SCOREMESSAGE.setText(message2);
        else if(correctAnswers>=6 && correctAnswers<=8)
            SCOREMESSAGE.setText(message3);
        else if(correctAnswers>=9 && correctAnswers<=10)
            SCOREMESSAGE.setText(message4);

        Button playAgain=(Button)findViewById(R.id.playAgain);
        Button home=(Button)findViewById(R.id.home);
        ImageButton peoriaAd=(ImageButton)findViewById(R.id.peoria);

        //Navigation to the trivia play activity.
        playAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent playAgain=new Intent(view.getContext(), TriviaPlay.class);
                correctAnswers=0;
                startActivityForResult(playAgain, 0);
            }
        });

        //Navigation to the home activity.
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent homeNav=new Intent(view.getContext(), TriviaMain.class);
                correctAnswers=0;
                startActivityForResult(homeNav, 0);
            }
        });

        //Navigation to the Peoria Zoo website.
        peoriaAd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent peoriaAd=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.peoriazoo.org/"));
                startActivity(peoriaAd);
            }
        });
    }
}

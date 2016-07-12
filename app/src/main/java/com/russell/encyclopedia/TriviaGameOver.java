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
    TextView RESULTS, MESSAGE;
    String results, message1, message2, message3, message4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviagameover);
        checkCorrect(correct_l);
        RESULTS=(TextView)findViewById(R.id.textView21);
        MESSAGE=(TextView)findViewById(R.id.textView22);
        results="Results: " + correct + "/10\n";
        RESULTS.setText(results);
        message1="\nYour ribbit could use some work! Use the encyclopedia and let the frogs teach their ways, so you can get better!\n";
        message2="\nYou've got the potential to have a great voice! You just need some coaching from your friendly neighborhood frogs!\n";
        message3="\nNot too shabby! With just a little more knowledge, you'll be croaking your heart out!\n";
        message4="\nNow that's what I'm talking about! You're good enough to get out there and sing with the best of them!\n";
        if(correct>=0 && correct<=2)
            MESSAGE.setText(message1);
        else if(correct>=3 && correct<=5)
            MESSAGE.setText(message2);
        else if(correct>=6 && correct<=8)
            MESSAGE.setText(message3);
        else if(correct>=9 && correct<=10)
            MESSAGE.setText(message4);
        Button b7=(Button)findViewById(R.id.button14);
        Button b8=(Button)findViewById(R.id.button15);
        ImageButton i=(ImageButton)findViewById(R.id.imageButton4);
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent back=new Intent(view.getContext(), TriviaPlay.class);
                correct=0;
                startActivityForResult(back, 0);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view1){
                Intent back=new Intent(view1.getContext(), TriviaGame.class);
                correct=0;
                startActivityForResult(back, 0);
            }
        });
        i.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view2){
                Intent ad2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.peoriazoo.org/"));
                startActivity(ad2);
            }
        });
    }
}

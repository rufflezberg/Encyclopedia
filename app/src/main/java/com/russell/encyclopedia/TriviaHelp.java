package com.russell.encyclopedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * Created by Russell on 4/13/2015.
 */
public class TriviaHelp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviahelp);

        //Navigation to the trivia main activity.
        Button trivia=(Button)findViewById(R.id.backTrivia);
        trivia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent trivia=new Intent(view.getContext(), TriviaMain.class);
                startActivityForResult(trivia, 0);
            }
        });
    }
}

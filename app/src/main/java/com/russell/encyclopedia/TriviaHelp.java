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
        Button b6=(Button)findViewById(R.id.button9);
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent back=new Intent(view.getContext(), TriviaGame.class);
                startActivityForResult(back, 0);
            }
        });
    }
}

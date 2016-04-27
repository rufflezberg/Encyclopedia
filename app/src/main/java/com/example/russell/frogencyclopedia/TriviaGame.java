package com.example.russell.frogencyclopedia;

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
        Button b1=(Button)findViewById(R.id.button4);
        ImageButton ad2=(ImageButton)findViewById(R.id.imageButton2);
        Button b3=(Button)findViewById(R.id.button6);
        Button b4=(Button)findViewById(R.id.button7);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view0){
                Intent back=new Intent(view0.getContext(), TriviaPlay.class);
                startActivityForResult(back, 0);
            }
        });
        ad2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view1) {
                Intent ad1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.czs.org/Brookfield-ZOO/Home"));
                startActivity(ad1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view2){
                Intent back=new Intent(view2.getContext(), TriviaHelp.class);
                startActivityForResult(back, 0);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view3){
                Intent back=new Intent(view3.getContext(), HomeActivity.class);
                startActivityForResult(back, 0);
            }
        });
    }
}
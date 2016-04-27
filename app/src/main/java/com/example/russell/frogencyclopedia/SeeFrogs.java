package com.example.russell.frogencyclopedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Russell on 4/14/2015.
 */
public class SeeFrogs extends Activity {
    public static String name;
    List<String> nameList = new ArrayList<String>();
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seefrogs);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_seefrogs);

        DataBaseHelper db = new DataBaseHelper(this);

        try {

            db.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            db.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }

        List<byte[]> images = db.getSeeImages();
        List<ImageButton> imageButtonList = new ArrayList<ImageButton>();

        nameList = db.getNames();
        for(int i = 0; i < images.size(); i++){
            imageButtonList.add(new ImageButton(this));
            Bitmap bitmap = BitmapFactory.decodeByteArray(images.get(i), 0, images.get(i).length);
            BitmapDrawable drawableBitmap = new BitmapDrawable(ctx.getResources(), bitmap);
            imageButtonList.get(i).setBackground(drawableBitmap);
            imageButtonList.get(i).setClickable(true);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 175);
            params.width = 150;
            params.height = 175;
            params.topMargin = 20;
            if(i%2 == 0)
            {
                params.leftMargin = 40;
                if(i == 0)
                {
                    params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
                }
                else
                {
                    params.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);
                    for (int n = 0; n < i/2; n++)
                        params.topMargin += 200;
                }
                layout.addView(imageButtonList.get(i), params);
            }
            else
            {
                params.rightMargin = 40;
                params.leftMargin = 20;
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                if(i != 1){
                    for (int n = 0; n < i/2; n++)
                        params.topMargin += 200;
                }
                layout.addView(imageButtonList.get(i), params);
            }
        }
        setOnClick(imageButtonList);

        Button button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View toHome){
                Intent back=new Intent(toHome.getContext(), HomeActivity.class);
                startActivityForResult(back, 0);
            }
        });
    }

    public void setOnClick(List<ImageButton> imageButtonList){
        for(int i = 0; i < imageButtonList.size(); i++){
            final int n = i;
            //if needed lists have an indexOf(Object o) function
            imageButtonList.get(i).setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    Intent toInfo=new Intent(view.getContext(), FrogInfo.class);
                    name = nameList.get(n);
                    startActivityForResult(toInfo, 0);
                }
            });
        }
    }
}

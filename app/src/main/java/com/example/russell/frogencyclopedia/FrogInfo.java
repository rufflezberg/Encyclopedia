package com.example.russell.frogencyclopedia;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Russell on 4/13/2015.
 */
public class FrogInfo extends SeeFrogs {
    TextView FROGNAME,SCINAME,FAM,DESC,SIZE,VOICE,BREED,HABIT,RANGE,BEHAV,DIET;
    String frogname,sciname,fam,desc,size,voice,breed,habit,range,behav,diet;
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.froginfo);

        FROGNAME=(TextView)findViewById(R.id.fName);
        SCINAME=(TextView)findViewById(R.id.sName);
        FAM=(TextView)findViewById(R.id.family);
        DESC=(TextView)findViewById(R.id.descript);
        SIZE=(TextView)findViewById(R.id.siz);
        VOICE=(TextView)findViewById(R.id.voic);
        BREED=(TextView)findViewById(R.id.breeding);
        HABIT=(TextView)findViewById(R.id.habit);
        RANGE=(TextView)findViewById(R.id.rang);
        BEHAV=(TextView)findViewById(R.id.behav);
        DIET=(TextView)findViewById(R.id.diet);

        ImageView FROGIMAGE;
        FROGIMAGE=(ImageView)findViewById(R.id.fImag);

        DataBaseHelper db = new DataBaseHelper(this);

        try {

            db.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }

        byte[] image = db.getInfoImage(name);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        BitmapDrawable drawableBitmap = new BitmapDrawable(ctx.getResources(), bitmap);
        FROGIMAGE.setBackground(drawableBitmap);

        List<String> infoList = db.getInfo(name);
        frogname=infoList.get(0);
        sciname=infoList.get(1);
        fam=infoList.get(2);
        desc=infoList.get(3);
        size=infoList.get(4);
        voice=infoList.get(5);
        breed=infoList.get(6);
        habit=infoList.get(7);
        range=infoList.get(8);
        behav=infoList.get(9);
        diet=infoList.get(10);

        FROGNAME.setText(frogname + "\n");
        SCINAME.setText("\nScientific Name: " + sciname + "\n");
        FAM.setText("Family: " + fam + "\n");
        DESC.setText("Description: " + desc + "\n");
        SIZE.setText("Size: " + size + "\n");
        VOICE.setText("Voice: " + voice + "\n");
        BREED.setText("Breeding habits: " + breed + "\n");
        HABIT.setText("Habitats: " + habit + "\n");
        RANGE.setText("Range: " + range + "\n");
        BEHAV.setText("Behavior: " + behav + "\n");
        DIET.setText("Diet: " + diet + "\n");

        Button back = (Button) findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent seeFrogs = new Intent(view.getContext(), SeeFrogs.class);
                startActivityForResult(seeFrogs, 0);
            }
        });
    }
}

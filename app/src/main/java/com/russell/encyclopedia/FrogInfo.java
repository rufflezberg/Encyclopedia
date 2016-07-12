package com.russell.encyclopedia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Russell on 4/13/2015.
 */
public class FrogInfo extends SeeFrogs {
    TextView FROGNAME,SCINAME,FAM,DESC,SIZE,VOICE,BREED,HABIT,RANGE,BEHAV,DIET,THREAT;
    String frogname,sciname,fam,desc,size,voice,breed,habit,range,behav,diet,threat,endangered;
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.froginfo);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.froginfo);

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

        DatabaseAccess db;
        db = new DatabaseAccess(this);

        try {

            db.openDataBase();

        }catch(SQLException sqle){

            Toast.makeText(this, "Database failed to open", Toast.LENGTH_SHORT);
            finish();

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
        threat=infoList.get(11);
        endangered=infoList.get(12);

        if(threat.equals("True") || endangered.equals("True")){
            THREAT=new TextView(ctx);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            THREAT.setId(0);
            params.addRule(RelativeLayout.BELOW, R.id.fImag);
            THREAT.setTextColor(Color.parseColor("#ffffff"));
            layout.addView(THREAT, params);
            if(endangered == "True")
                THREAT.setText("*Endangered");
            else
                THREAT.setText("*Threatened");
            RelativeLayout.LayoutParams change = (RelativeLayout.LayoutParams)SCINAME.getLayoutParams();
            change.setMargins(0, 20, 0, 0);
            SCINAME.setLayoutParams(change);
        }

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

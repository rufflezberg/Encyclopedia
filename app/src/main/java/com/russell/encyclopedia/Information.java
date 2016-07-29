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
public class Information extends SeeAnimals {
    TextView ANIMALNAME,SCIENTIFICNAME,FAMILY,DESCRIPTION,SIZE,VOICE,BREEDING,HABITAT,RANGE,BEHAVIOR,DIET,THREATENED;
    String animalName,scientificName,animalFamily,animalDescription,animalSize,animalVoice,animalBreeding,animalHabitat,animalRange,animalBehavior,animalDiet,threatened,endangered;
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.animalInformation);

        ANIMALNAME=(TextView)findViewById(R.id.animalName);
        SCIENTIFICNAME=(TextView)findViewById(R.id.scientificName);
        FAMILY=(TextView)findViewById(R.id.animalFamily);
        DESCRIPTION=(TextView)findViewById(R.id.animalDescription);
        SIZE=(TextView)findViewById(R.id.animalSize);
        VOICE=(TextView)findViewById(R.id.animalVoice);
        BREEDING=(TextView)findViewById(R.id.animalBreeding);
        HABITAT=(TextView)findViewById(R.id.animalHabitat);
        RANGE=(TextView)findViewById(R.id.animalRange);
        BEHAVIOR=(TextView)findViewById(R.id.animalBehavior);
        DIET=(TextView)findViewById(R.id.animalDiet);

        ImageView ANIMALIMAGE;
        ANIMALIMAGE=(ImageView)findViewById(R.id.animalImage);

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
        ANIMALIMAGE.setBackground(drawableBitmap);

        List<String> infoList = db.getInfo(name);
        animalName=infoList.get(0);
        scientificName=infoList.get(1);
        animalFamily=infoList.get(2);
        animalDescription=infoList.get(3);
        animalSize=infoList.get(4);
        animalVoice=infoList.get(5);
        animalBreeding=infoList.get(6);
        animalHabitat=infoList.get(7);
        animalRange=infoList.get(8);
        animalBehavior=infoList.get(9);
        animalDiet=infoList.get(10);
        threatened=infoList.get(11);
        endangered=infoList.get(12);

        if(threatened.equals("True") || endangered.equals("True")){
            THREATENED=new TextView(ctx);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            THREATENED.setId(0);
            params.addRule(RelativeLayout.BELOW, R.id.animalImage);
            THREATENED.setTextColor(Color.parseColor("#ffffff"));
            layout.addView(THREATENED, params);
            if(endangered == "True")
                THREATENED.setText("*Endangered");
            else
                THREATENED.setText("*Threatened");
            RelativeLayout.LayoutParams change = (RelativeLayout.LayoutParams)SCIENTIFICNAME.getLayoutParams();
            change.setMargins(0, 20, 0, 0);
            SCIENTIFICNAME.setLayoutParams(change);
        }

        ANIMALNAME.setText(animalName + "\n");
        SCIENTIFICNAME.setText("\nScientific Name: " + scientificName + "\n");
        FAMILY.setText("Family: " + animalFamily + "\n");
        DESCRIPTION.setText("Description: " + animalDescription + "\n");
        SIZE.setText("Size: " + animalSize + "\n");
        VOICE.setText("Voice: " + animalVoice + "\n");
        BREEDING.setText("Breeding habits: " + animalBreeding + "\n");
        HABITAT.setText("Habitats: " + animalHabitat + "\n");
        RANGE.setText("Range: " + animalRange + "\n");
        BEHAVIOR.setText("Behavior: " + animalBehavior + "\n");
        DIET.setText("Diet: " + animalDiet + "\n");

        Button back = (Button) findViewById(R.id.backSeeAnimals);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent seeAnimals = new Intent(view.getContext(), SeeAnimals.class);
                startActivityForResult(seeAnimals, 0);
            }
        });
    }
}

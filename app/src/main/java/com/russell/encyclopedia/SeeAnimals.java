package com.russell.encyclopedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Russell on 4/14/2015.
 */
public class SeeAnimals extends Activity {

    public static String name;
    List<String> nameList = new ArrayList<String>();
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seeanimals);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.seeAnimals);

        //Set up database connection.
        DatabaseAccess db;
        db = new DatabaseAccess(this);

        try {

            db.openDataBase();

        }catch(SQLException sqle){

            Toast.makeText(this, "Database failed to open", Toast.LENGTH_SHORT);
            finish();

        }

        //Query to get the see images for all of the animals in the database.
        List<byte[]> images = db.getSeeImages();

        //Creates a list of ImageButtons to store the ImageButtons associated with each animal in the database.
        List<ImageButton> imageButtonList = new ArrayList<ImageButton>();

        //Query to get the names of all animals in the database.
        nameList = db.getNames();

        //Adds an ImageButton for each animal in the database and sets the layout parameters for those ImageButtons
        for(int i = 0; i < images.size(); i++){
            imageButtonList.add(new ImageButton(this));
            //Convert images from the list of see images to a drawable format and sets the appropriate ImageButton background image.
            Bitmap bitmap = BitmapFactory.decodeByteArray(images.get(i), 0, images.get(i).length);
            BitmapDrawable drawableBitmap = new BitmapDrawable(ctx.getResources(), bitmap);
            imageButtonList.get(i).setBackground(drawableBitmap);
            imageButtonList.get(i).setClickable(true);

            //Sets layout parameters for the ImageButtons
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 175);
            params.width = 300;
            params.height = 250;
            params.topMargin = 20;

            //Sets extra parameters for the ImageButtons on the left side of the screen.
            if(i%2 == 0)
            {
                params.leftMargin = 40;

                //Sets the parameters for the first ImageButton on the left side of the screen.
                if(i == 0)
                {
                    params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
                }

                //Sets the parameters for all subsequent ImageButtons on the left side of the screen.
                else
                {
                    params.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);
                    for (int n = 0; n < i/2; n++)
                        params.topMargin += 275;
                }

                layout.addView(imageButtonList.get(i), params);
            }

            //Sets extra parameters for the ImageButtons on the right side of the screen.
            else
            {
                //Sets the parameters for the first ImageButton on the right side of the screen.
                params.rightMargin = 40;
                params.leftMargin = 20;
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                ///Sets the parameters for all subsequent ImageButtons on the right side of the screen.
                if(i != 1){
                    for (int n = 0; n < i/2; n++)
                        params.topMargin += 275;
                }

                layout.addView(imageButtonList.get(i), params);
            }
        }

        //Sets the onClickEvent for the ImageButtons.
        setOnClick(imageButtonList);

        //Sets the extra layout parameters for the back button at the bottom of the screen.
        Button homeActivity = (Button)findViewById(R.id.backHomeActivity);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(250, 100);
        param.topMargin = 275 * (imageButtonList.size()/2) + 25;
        param.addRule(RelativeLayout.CENTER_HORIZONTAL);
        homeActivity.setLayoutParams(param);

        //navigation to the home activity.
        homeActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent homeActivity=new Intent(view.getContext(), HomeActivity.class);
                startActivityForResult(homeActivity, 0);
            }
        });
    }

    //Sets the onClickEvent for the ImageButtons.
    public void setOnClick(List<ImageButton> imageButtonList){
        for(int i = 0; i < imageButtonList.size(); i++){
            final int n = i;
            imageButtonList.get(i).setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    Intent toInfo=new Intent(view.getContext(), Information.class);
                    name = nameList.get(n);
                    startActivityForResult(toInfo, 0);
                }
            });
        }
    }
}

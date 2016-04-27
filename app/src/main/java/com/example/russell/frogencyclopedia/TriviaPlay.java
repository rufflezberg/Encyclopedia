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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Russell on 4/13/2015.
 */
public class TriviaPlay extends Activity {
    TextView QUESTION,QUEST_NUM;
    Button ANSWER1,ANSWER2,ANSWER3,ANSWER4;
    ImageView IMAGE;
    String question,quest_num,answer1,answer2,answer3,answer4,correct_let;
    static String correct_letter,correct_l;
    Context ctx=this;
    static int correct=0, count1=1, numOfQuests=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviaplaygame);
        QUEST_NUM=(TextView)findViewById(R.id.textView18);
        QUESTION=(TextView)findViewById(R.id.textView19);
        IMAGE=(ImageView)findViewById(R.id.imageView);
        ANSWER1=(Button)findViewById(R.id.button10);
        ANSWER2=(Button)findViewById(R.id.button11);
        ANSWER3=(Button)findViewById(R.id.button12);
        ANSWER4=(Button)findViewById(R.id.button13);

        DataBaseHelper db = new DataBaseHelper(this);

        try {

            db.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }

        if(count1==0)
            numOfQuests = db.getNumOfQuestions();
        List<String> list = db.getTriviaInfo(count1);
        byte[] image = db.getTriviaImage(count1);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        BitmapDrawable drawableBitmap = new BitmapDrawable(ctx.getResources(), bitmap);
        IMAGE.setBackground(drawableBitmap);
            if(count1==1)
            {
                question=list.get(0);
                answer1=list.get(1);
                answer2=list.get(2);
                answer3=list.get(3);
                answer4=list.get(4);
                correct_let=list.get(5);
                sText(question,answer1,answer2,answer3,answer4,correct_let);
            }
            else
            {
                checkCorrect(correct_l);
                question=list.get(0);
                answer1=list.get(1);
                answer2=list.get(2);
                answer3=list.get(3);
                answer4=list.get(4);
                correct_let=list.get(5);
                sText(question,answer1,answer2,answer3,answer4,correct_let);
            }
        quest_num="Question: " + count1 + "/10\n";
        QUEST_NUM.setText(quest_num);
        count1++;
        if(count1==numOfQuests+1)
        {
            count1=0;
            numOfQuests=0;
            gameOver();
        }
        else
        {
            nextQuestion();
        }

    }
    void sText(String a, String b, String c, String d, String e, String f)
    {
        QUESTION.setText(a + "\n");
        ANSWER1.setText(b);
        ANSWER2.setText(c);
        ANSWER3.setText(d);
        ANSWER4.setText(e);
        correct_letter=f;
    }
    static void checkCorrect(String let)
    {
        if(let.equals(correct_letter))
            correct++;
    }
    void gameOver()
    {
        ANSWER1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view4) {
                correct_l="A";
                Intent done = new Intent(view4.getContext(), TriviaGameOver.class);
                startActivityForResult(done, 0);
            }
        });
        ANSWER2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view5) {
                correct_l="B";
                Intent done = new Intent(view5.getContext(), TriviaGameOver.class);
                startActivityForResult(done, 0);
            }
        });
        ANSWER3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view6) {
                correct_l="C";
                Intent done = new Intent(view6.getContext(), TriviaGameOver.class);
                startActivityForResult(done, 0);
            }
        });
        ANSWER4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view7) {
                correct_l="D";
                Intent done = new Intent(view7.getContext(), TriviaGameOver.class);
                startActivityForResult(done, 0);
            }
        });
    }
    void nextQuestion()
    {
        ANSWER1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                correct_l="A";
                Intent back=new Intent(view.getContext(), TriviaPlay.class);
                startActivityForResult(back, 0);
            }
        });
        ANSWER2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view1){
                correct_l="B";
                Intent back=new Intent(view1.getContext(), TriviaPlay.class);
                startActivityForResult(back, 0);
            }
        });
        ANSWER3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view2){
                correct_l="C";
                Intent back=new Intent(view2.getContext(), TriviaPlay.class);
                startActivityForResult(back, 0);
            }
        });
        ANSWER4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view3){
                correct_l="D";
                Intent back=new Intent(view3.getContext(), TriviaPlay.class);
                startActivityForResult(back, 0);
            }
        });
    }
}

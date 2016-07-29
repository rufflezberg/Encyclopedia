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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Russell on 4/13/2015.
 */
public class TriviaPlay extends Activity {
    TextView QUESTION,QUESTIONNUMBER;
    Button ANSWERA,ANSWERB,ANSWERC,ANSWERD;
    ImageView IMAGE;
    String question,questionNumber,answerA,answerB,answerC,answerD;
    static String answer,correctAnswer;
    Context ctx=this;
    static int correctAnswers=0, questionCount=1, numberOfQuestions=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviaplaygame);
        QUESTIONNUMBER=(TextView)findViewById(R.id.questionNumber);
        QUESTION=(TextView)findViewById(R.id.question);
        IMAGE=(ImageView)findViewById(R.id.questionImage);
        ANSWERA=(Button)findViewById(R.id.answerA);
        ANSWERB=(Button)findViewById(R.id.answerB);
        ANSWERC=(Button)findViewById(R.id.answerC);
        ANSWERD=(Button)findViewById(R.id.answerD);

        DatabaseAccess db;
        db = new DatabaseAccess(this);

        try {

            db.openDataBase();

        }catch(java.sql.SQLException sqle){

            Toast.makeText(this, "Database failed to open", Toast.LENGTH_SHORT);
            finish();

        }

        if(questionCount==0)
            numberOfQuestions = db.getNumOfQuestions();

        List<String> list = db.getTriviaInfo(questionCount);
        byte[] image = db.getTriviaImage(questionCount);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        BitmapDrawable drawableBitmap = new BitmapDrawable(ctx.getResources(), bitmap);

        IMAGE.setBackground(drawableBitmap);
            if(questionCount==1)
            {
                question=list.get(0);
                answerA=list.get(1);
                answerB=list.get(2);
                answerC=list.get(3);
                answerD=list.get(4);
                correctAnswer=list.get(5);
                setText(question,answerA,answerB,answerC,answerD,correctAnswer);
            }
            else
            {
                checkCorrect(answer);
                question=list.get(0);
                answerA=list.get(1);
                answerB=list.get(2);
                answerC=list.get(3);
                answerD=list.get(4);
                correctAnswer=list.get(5);
                setText(question,answerA,answerB,answerC,answerD,correctAnswer);
            }

        questionNumber="Question: " + questionCount + "/10\n";
        QUESTIONNUMBER.setText(questionNumber);
        questionCount++;

        if(questionCount==numberOfQuestions+1)
        {
            questionCount=0;
            numberOfQuestions=0;
            nextQuestion(true);
        }
        else
        {
            nextQuestion(false);
        }
    }

    void setText(String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer)
    {
        QUESTION.setText(question + "\n");
        ANSWERA.setText(answerA);
        ANSWERB.setText(answerB);
        ANSWERC.setText(answerC);
        ANSWERD.setText(answerD);
        this.correctAnswer=correctAnswer;
    }

    static void checkCorrect(String answer)
    {
        if(answer.equals(correctAnswer))
            correctAnswers++;
    }

    void nextQuestion(boolean isOver)
    {
        if(isOver){
            ANSWERA.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    correctAnswer="A";
                    Intent gameOver = new Intent(view.getContext(), TriviaPlay.class);
                    startActivityForResult(gameOver, 0);
                }
            });
            ANSWERB.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    correctAnswer="B";
                    Intent gameOver = new Intent(view.getContext(), TriviaPlay.class);
                    startActivityForResult(gameOver, 0);
                }
            });
            ANSWERC.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    correctAnswer="C";
                    Intent gameOver = new Intent(view.getContext(), TriviaPlay.class);
                    startActivityForResult(gameOver, 0);
                }
            });
            ANSWERD.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    correctAnswer="D";
                    Intent gameOver = new Intent(view.getContext(), TriviaPlay.class);
                    startActivityForResult(gameOver, 0);
                }
            });
        }
        else{
            ANSWERA.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    correctAnswer="A";
                    Intent gameOver = new Intent(view.getContext(), TriviaGameOver.class);
                    startActivityForResult(gameOver, 0);
                }
            });
            ANSWERB.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    correctAnswer="B";
                    Intent gameOver = new Intent(view.getContext(), TriviaGameOver.class);
                    startActivityForResult(gameOver, 0);
                }
            });
            ANSWERC.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    correctAnswer="C";
                    Intent gameOver = new Intent(view.getContext(), TriviaGameOver.class);
                    startActivityForResult(gameOver, 0);
                }
            });
            ANSWERD.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    correctAnswer="D";
                    Intent gameOver = new Intent(view.getContext(), TriviaGameOver.class);
                    startActivityForResult(gameOver, 0);
                }
            });
        }
    }
}

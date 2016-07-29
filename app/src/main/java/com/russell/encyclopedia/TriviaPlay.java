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

        //Sets up the database connection.
        DatabaseAccess db;
        db = new DatabaseAccess(this);

        try {

            db.openDataBase();

        }catch(java.sql.SQLException sqle){

            Toast.makeText(this, "Database failed to open", Toast.LENGTH_SHORT);
            finish();

        }

        //Query to get the number of questions in the database if no questions have been asked yet.
        if(questionCount==0)
            numberOfQuestions = db.getNumOfQuestions();

        //Query to get the question, answers, and correct answer for the number of the question the user is on.
        List<String> list = db.getTriviaInfo(questionCount);

        //Query to get the image associated with the current question.
        byte[] image = db.getTriviaImage(questionCount);

        //Converts the image associated with the question to a drawable format.
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        BitmapDrawable drawableBitmap = new BitmapDrawable(ctx.getResources(), bitmap);

        //Sets the background image of the ImageView on the screen.
        IMAGE.setBackground(drawableBitmap);

        //Sets up question 1 and does not check the answer to the last question as there wasn't one.
        if(questionCount==1)
        {
            question=list.get(0);
            answerA=list.get(1);
            answerB=list.get(2);
            answerC=list.get(3);
            answerD=list.get(4);
            correctAnswer=list.get(5);

            //Sets the TextView for the question, the text on the answer buttons, and the correct answer variable.
            setText(question,answerA,answerB,answerC,answerD,correctAnswer);
        }

        //Sets up the rest of the questions and checks the answer to the last question.
        else
        {
            //Checks the answer to the last question.
            checkCorrect(answer);

            question=list.get(0);
            answerA=list.get(1);
            answerB=list.get(2);
            answerC=list.get(3);
            answerD=list.get(4);
            correctAnswer=list.get(5);

            //Sets the TextView for the question, the text on the answer buttons, and the correct answer variable.
            setText(question,answerA,answerB,answerC,answerD,correctAnswer);
        }

        //Sets up the question counter at the top of the screen.
        questionNumber="Question: " + questionCount + "/10\n";
        QUESTIONNUMBER.setText(questionNumber);
        questionCount++;

        //Tracks the question counter to watch for the last question.
        if(questionCount==numberOfQuestions+1)
        {
            questionCount=0;
            numberOfQuestions=0;

            //Sets up the next screen for the last question.
            nextQuestion(true);
        }

        //Continues to the next question, which is not the last one.
        else
        {
            //Sets up the next screen for the next question.
            nextQuestion(false);
        }
    }

    //Sets the text for the question, the answer buttons, and the correct answer variable.
    void setText(String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer)
    {
        QUESTION.setText(question + "\n");
        ANSWERA.setText(answerA);
        ANSWERB.setText(answerB);
        ANSWERC.setText(answerC);
        ANSWERD.setText(answerD);
        this.correctAnswer=correctAnswer;
    }

    //Checks the answer to the last question and adds to the score upon a match.
    static void checkCorrect(String answer)
    {
        if(answer.equals(correctAnswer))
            correctAnswers++;
    }

    //Sets up the next screen for the next question or final question if isOver is true.
    void nextQuestion(boolean isOver)
    {
        //Sets up the next screen for the last question.
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

        //Sets up the next screen for the next question.
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

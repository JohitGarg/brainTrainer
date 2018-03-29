package com.example.johitgarg.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView scoreTextView,quesTextView,resultTextView,timerTextView;
    int score=0;
    int question=0;
    Button button0,button1,button2,button3,playAgain;
    ConstraintLayout playArea;
    GridLayout answersGrid;
    public void playAgain(View view){
        score=0;
        question=0;
        newQuestion();
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(question));
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time up!!");
                playAgain.setVisibility(View.VISIBLE);
                answersGrid.setVisibility(View.INVISIBLE);
                //Also we want to disable all answers button
            }
        }.start();
        playAgain.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        playArea.setVisibility(View.VISIBLE);
        answersGrid.setVisibility(View.VISIBLE);
        resultTextView.setText("Play");
    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));//It doesn't matter which view you pass to the arguments
    }
    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        quesTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for (int i=0;i<4;i++){
            if (locationOfCorrectAnswer == i) {
                answers.add(a + b);
            }
            else{
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer==a+b){
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void answer(View view){
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
            question++;
            newQuestion();
        }
        else {
            resultTextView.setText("Wrong!");
            question++;
        }
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(question));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        quesTextView = findViewById(R.id.quesTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgain = findViewById(R.id.playAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        answersGrid = findViewById(R.id.answersGrid);
        playArea = findViewById(R.id.playArea);
        playAgain.setVisibility(View.INVISIBLE);
    }
}

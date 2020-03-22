package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView questionno, score, questions;
    EditText answer;
    Button button, playagain;
    int res, c;
    int totalscore = 0, totalquestion = 0;
    int change=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        questionno = findViewById(R.id.questionno);
        score = findViewById(R.id.score);
        questions = findViewById(R.id.questions);
        button = findViewById(R.id.button);
        answer = (EditText) findViewById(R.id.answer);
        playagain = findViewById(R.id.playagain);
        score.setText("Score: 0/0");
        questionno.setText("Question no: 1");
        progressBar.setProgress(1);
        progressBar.setMax(6);
        generatequestion();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Integer.parseInt(answer.getText().toString());
                checkans();
                generatequestion();
                if(totalquestion==0) {
                    score.setText("Score: 0/0");
                }
                else
                {
                    score.setText("Score: " + totalscore + "/" + (totalquestion - 1));
                }

                questionno.setText("Question no: " + totalquestion);
                progressBar.setProgress(totalquestion);

            }
        });
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totalquestion = 0;
                totalscore = 0;
                playagain.setText("Submit");
               playagain.setVisibility(View.INVISIBLE);
               button.setVisibility(View.VISIBLE);
            }
        });


    }

    public void generatequestion() {
        totalquestion++;
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        questions.setText(a + "+" + b);
        res = a + b;
         if (totalquestion == 6) {
             change=1;
            answer.setClickable(false);
            totalquestion = 0;
          int c=totalscore+1;

             button.setVisibility(View.INVISIBLE);
             playagain.setVisibility(View.VISIBLE);
             playagain.setText("Play Again");
             new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                     .setTitleText("Game Over!")
                     .setContentText("Your Score is " + c + "/5")
                     .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                         @Override
                         public void onClick(SweetAlertDialog sweetAlertDialog) {

                             sweetAlertDialog.dismiss();
                         }
                     })
                     .show();
             totalscore = 0;
        }


    }

    public void checkans() {
        if (totalquestion >= 0 && totalquestion <= 4) {
            if (c == res) {
                totalscore++;
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("Right Answer!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                                sweetAlertDialog.dismiss();
                            }
                        })
                        .show();
            }

             else {
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Wrong Answer!")
                        .setContentText("Right Answer is " + res)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        })
                        .show();
            }
            }
        }
    }





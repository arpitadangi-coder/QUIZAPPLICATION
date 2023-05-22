package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questionTextView;
    Button ansA,ansB,ansC,ansD,SubmitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        SubmitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        //SubmitBtn.equals(this);

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])) {
                    score++;
                }
                currentQuestionIndex++;
                Log.d("abcd", "onClick: " + currentQuestionIndex);
                Toast.makeText(MainActivity.this, String.valueOf(score), Toast.LENGTH_SHORT).show();
                loadNewQuestion();
            }
        });
        //totalQuestionsTextView.setText("Total question : "+totalQuestion);

        loadNewQuestion();
    }
    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);


        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn) {

            if(selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            //Log.d("abcd", "onClick: " + currentQuestionIndex);
            //Toast.makeText(this, score, Toast.LENGTH_SHORT).show();
            loadNewQuestion();
        }else{
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }
    void loadNewQuestion(){

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    public void finishQuiz() {
        String passStatus = "";
        if(score > totalQuestion*0.60) {
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is "+score+"out of "+totalQuestion)
                .setPositiveButton("Restart",(dialogInterface,i)-> restartQuiz() )
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
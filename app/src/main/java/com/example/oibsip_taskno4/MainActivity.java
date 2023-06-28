package com.example.oibsip_taskno4;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtTotalQuestion;
    TextView txtQuestion;
    Button btnA,btnB,btnC,btnD;
    Button btnSave;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int  currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTotalQuestion = findViewById(R.id.txtTotalQuestion);
        txtQuestion = findViewById(R.id.txtQuestion);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnSave = findViewById(R.id.btnSave);


        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        txtTotalQuestion.setText("Total Questions:" +totalQuestion);
        loadNewQuestion();


    }

    @Override
    public void onClick(View view) {

        // btnA.setBackgroundColor(Color.DKGRAY);
        // btnB.setBackgroundColor(Color.DKGRAY);
        // btnC.setBackgroundColor(Color.DKGRAY);
        //btnD.setBackgroundColor(Color.DKGRAY);


        Button clickedButton = (Button) view;
        if( clickedButton.getId()==R.id.btnSave){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        }else {
            //choice button clicked
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);


        }
    }
    void loadNewQuestion(){

        if(currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }


        txtQuestion.setText(QuestionAnswer.question[currentQuestionIndex]);
        btnA.setText(QuestionAnswer.answers[currentQuestionIndex][0]);
        btnB.setText(QuestionAnswer.answers[currentQuestionIndex][1]);
        btnC.setText(QuestionAnswer.answers[currentQuestionIndex][2]);
        btnD.setText(QuestionAnswer.answers[currentQuestionIndex][3]);



    }

    void finishQuiz(){
        String passStatus ="";
        if(score>totalQuestion*0.60){
            passStatus="Passed";
        }else{
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" out of " + totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();


    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }
}
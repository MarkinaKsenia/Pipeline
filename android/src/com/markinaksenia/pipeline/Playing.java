package com.markinaksenia.pipeline;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Playing extends AppCompatActivity implements View.OnClickListener{

    private static final long  COUNTDOWN_TIME = 16000;
    private ColorStateList txtColor;
    private CountDownTimer countDownTimer;
    private long time;

    int index =0, score =0, score1=0, thisQuestion = 0, totalQuestion, correctAnswer;

    ImageView question_image;
    Button btnA, btnB, btnC, btnD;
    TextView  txtTimer, txtQuestionNum, question_text,questionTxtImage;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        dialog = new Dialog(Playing.this);
        dialog.setTitle("Подсказка");
        dialog.setContentView(R.layout.dialog_view);
        TextView textDialog = (TextView) dialog.findViewById(R.id.textView);

        if( Common.questionList.get(index).getCategoryId().equals("Легкий уровень") ) {
            textDialog.setText(Common.questionList.get(index).getComment().toString() );
        }


        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtQuestionNum = (TextView) findViewById(R.id.txtTotalQuestion);
        question_text = (TextView) findViewById(R.id.question_text);
        questionTxtImage = (TextView) findViewById(R.id.questionTxtImage);
        question_image = (ImageView) findViewById(R.id.question_image);

        txtColor = txtTimer.getTextColors();


        btnA  = (Button) findViewById(R.id.btnAnswerA);
        btnB  = (Button) findViewById(R.id.btnAnswerB);
        btnC  = (Button) findViewById(R.id.btnAnswerC);
        btnD  = (Button) findViewById(R.id.btnAnswerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);

        countDownTimer.cancel();
        if(index<totalQuestion){
            Button clickedButton = (Button) v;
            if(clickedButton.getText().equals(Common.questionList.get(index).getCorrectAnswer())){
               if( Common.questionList.get(index).getCategoryId().equals("Легкий уровень") ) {
                   score1 = 10;
               }
               else {
                   if( Common.questionList.get(index).getCategoryId().equals("Средний уровень") ) {
                       score1 = 50;
                   }
                   else {
                       if( Common.questionList.get(index).getCategoryId().equals("Тяжелый уровень") ) {
                       score1=100;
                   }
                 }
               }
                score+=score1;
                correctAnswer++;
                clickedButton.setBackgroundColor(Color.rgb(76,187,23));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnA.setEnabled(true);
                        btnB.setEnabled(true);
                        btnC.setEnabled(true);
                        btnD.setEnabled(true);
                        showQuestion (++index);
                    }
                }, 1000);
            }
            else {
                if( Common.questionList.get(index).getCategoryId().equals("Легкий уровень") ) {
                   // dialog.show();
                }
                    score += 0;
                    clickedButton.setBackgroundColor(Color.RED);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnA.setEnabled(true);
                            btnB.setEnabled(true);
                            btnC.setEnabled(true);
                            btnD.setEnabled(true);
                            showQuestion(++index);
                        }
                    }, 1000);

            }
        }
    }



    private void updateTxtTimer (){
        int minutes = (int) (time / 1000) / 60;
        int sec = (int) (time / 1000) % 60;

        String timeTxt = String.format(Locale.getDefault(),"%02d:%02d", minutes, sec);
        txtTimer.setText(timeTxt);

        if( time < 6000){
            txtTimer.setTextColor(Color.RED);
        }
        else {
            txtTimer.setTextColor(Color.WHITE);
        }


    }

    private void showQuestion(int index) {

        btnA.setBackgroundColor(Color.rgb(139,0,139));
        btnB.setBackgroundColor(Color.rgb(139,0,139));
        btnC.setBackgroundColor(Color.rgb(139,0,139));
        btnD.setBackgroundColor(Color.rgb(139,0,139));

        if(index<totalQuestion){
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d", thisQuestion, totalQuestion));

            updateTxtTimer();

            if(Common.questionList.get(index).getCategoryId().equals("Легкий уровень")){

                btnC.setVisibility(View.INVISIBLE);
                btnD.setVisibility(View.INVISIBLE);
                question_text.setText(Common.questionList.get(index).getQuestion());
                question_image.setVisibility(View.INVISIBLE);
                questionTxtImage.setVisibility(View.INVISIBLE);
                question_text.setVisibility(View.VISIBLE);
            }
            else{
                question_text.setText(Common.questionList.get(index).getQuestion());

                question_image.setVisibility(View.INVISIBLE);
                questionTxtImage.setVisibility(View.INVISIBLE);
                question_text.setVisibility(View.VISIBLE);
            }

            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());

            countDownTimer.start();

        }
        else {
            Intent intent = new Intent(this, Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt ("SCORE", score);
            dataSend.putInt("TOTAL", totalQuestion);
            dataSend.putInt("CORRECT", correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalQuestion = Common.questionList.size();

        time = COUNTDOWN_TIME;
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateTxtTimer();
            }

            @Override
            public void onFinish() {
                time = 0;
                updateTxtTimer();
                countDownTimer.cancel();
                showQuestion(++index);

            }
        };
        showQuestion(index);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }
}

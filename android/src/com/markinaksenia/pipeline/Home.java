package com.markinaksenia.pipeline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Home extends AppCompatActivity {

    private final static String FILE_NAME = "info.txt";

    Button btnGame, btnTest, btnRait;
    TextView textName, textRait;
    String user;
    String textRaitnew;
    String textNamefile;
    String normYsloviya;
    String textRaitNorm;
    String normYsloviyaScore;
    String Score;

    FileOutputStream fos;

    int wordIterator=0;

    FirebaseDatabase database;
    DatabaseReference raiting;
    Rait login;
    QuestionScore normYsloviyaInBD;
    long score;
    DatabaseReference questionScore, raitingTbl;
    DatabaseReference question_score;

    int sum = 0;
    int raitInBD;
 //   int existsNorm, noExistsNorm, resumeExistsNorm=0, resumeNoExistsNorm=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance();
        raiting = database.getReference("Raiting");

        question_score = database.getReference("Score");
        questionScore = database.getReference("Score");
        raitingTbl = database.getReference("Raiting");

        user = Common.currentUser.getUserName();
        score=0;

        btnGame = (Button) findViewById(R.id.button);
        btnTest = (Button) findViewById(R.id.button2);
        btnRait = (Button) findViewById(R.id.button3);
        textName = (TextView) findViewById(R.id.textView2);
        textRait = (TextView) findViewById(R.id.textView3);

       textName.setText(Common.currentUser.getUserName());

        btnRait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rait= new Intent(Home.this, Raiting.class);
                startActivity(rait);
                finish();

            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent playAct= new Intent(Home.this, Categories.class);
                startActivity(playAct);
                finish();

            }
        });

        final Intent playActivity = new Intent(this, AndroidLauncher.class);

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(playActivity);
            }
        });



        raiting.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(user).exists())
                {
                    if(!user.isEmpty()){
                        textRait.setText(Score);
                        login =  dataSnapshot.child(user).getValue(Rait.class);
                        save(user,login.getScore(),0);
                    }
                }
                else {
                    raiting.child(Common.currentUser.getUserName()).setValue(new Rait(Common.currentUser.getUserName(),0 ));
                    save(user,score,0);
                    textRait.setText("0");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onResume (){
        super.onResume();

 //       Toast.makeText(this, "Resume() "+existsNorm+" "+noExistsNorm+ user+"_"+"Нормальные условия", Toast.LENGTH_SHORT).show();

        sum=0;

        raiting.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(user).exists())
                {
                    if(!user.isEmpty()){
                        textRait.setText(Score);
                        login =  dataSnapshot.child(user).getValue(Rait.class);
                   //     save(user,login.getScore(),0);
                        open();

                        if(!(textRaitnew.equals(Long.toString(login.getScore()))) && (textNamefile.equals(user))){

                            if (normYsloviya!=null && textRaitNorm!=null){
                                question_score.child(normYsloviya).setValue(new QuestionScore(normYsloviya, Common.currentUser.getUserName(), textRaitNorm));
                              //  save(user,login.getScore(),1);
                            }

                       /*     updateScore(Common.currentUser.getUserName(), new RaitingCallBack<Rait>() {
                                @Override
                                public void callBack(Rait rait) {
                                    raitingTbl.child(rait.getUserName()).setValue(rait);
                                    textRait.setText(String.valueOf(rait.getScore()));
                                    // showRaiting();
                                }
                            }); */

                     //       Score = Long.toString(login.getScore());
                      //      textRait.setText(Score);

                        }
                        else {
                         //   Score = Long.toString(login.getScore());
                         //   textRait.setText(Score);
                         //   textRait.setText(Long.toString(login.getScore()));
                          //  save(user,login.getScore(),0);
                        }
                    }
                }
                else {
                    raiting.child(Common.currentUser.getUserName()).setValue(new Rait(Common.currentUser.getUserName(),0 ));
                    textRait.setText("0");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        open();


        updateScore(Common.currentUser.getUserName(), new RaitingCallBack<Rait>() {
            public Rait tempRait;

            @Override
            public void callBack(Rait rait) {
                tempRait=rait;
                raitingTbl.child(rait.getUserName()).setValue(rait);
                // showRaiting();
                textRait.setText(String.valueOf(rait.getScore()));

                questionScore.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(user+"_"+"Нормальные условия").exists())
                        {
                            if(!(user+"_"+"Нормальные условия").isEmpty()){
                                normYsloviyaInBD = dataSnapshot.child((user+"_"+"Нормальные условия")).getValue(QuestionScore.class);
                                normYsloviyaScore=normYsloviyaInBD.getScore();
                                save(user,tempRait.getScore(),1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        questionScore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(user+"_"+"Нормальные условия").exists())
                {
                    if(!(user+"_"+"Нормальные условия").isEmpty()){
                        normYsloviyaInBD = dataSnapshot.child((user+"_"+"Нормальные условия")).getValue(QuestionScore.class);
                        normYsloviyaScore=normYsloviyaInBD.getScore();
                        save(user,login.getScore(),1);
                    }
                    else {
                        save(user,login.getScore(),0);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void save(String gamer, Long rait, int existsNorm){

        fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(gamer.getBytes());
            fos.write("\n".getBytes());
            fos.write(Long.toString(rait).getBytes());

            if (existsNorm==1){
                fos.write("\n".getBytes());
                fos.write((user+"_"+"Нормальные условия").getBytes());
                fos.write("\n".getBytes());
                fos.write(normYsloviyaScore.getBytes());
            }
            else {
                fos.write("\n".getBytes());
                fos.write((user+"_"+"Нормальные условия").getBytes());
                fos.write("\n".getBytes());
                fos.write((0+"").getBytes());
            }
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void open(){

        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            String[] words = text.split("\n");
            for (String word : words) {
                    textRaitnew=words[1];
            }
            textNamefile=words[0];
            normYsloviya=words[2];
            textRaitNorm=words[3];
           // textRait.setText(textRaitnew);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateScore1(final String userName, final RaitingCallBack<Rait> callback) {

        if (textRaitnew!=null){
            raitInBD = Integer.parseInt(textRaitnew);
            Rait rait = new Rait (userName, raitInBD);
            callback.callBack(rait);
        }

    }

    private void updateScore(final String userName, final RaitingCallBack<Rait> callback) {
      //  sum=0;

        questionScore.orderByChild("user").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    QuestionScore quest = data.getValue(QuestionScore.class);
                    sum+= Integer.parseInt(quest.getScore());
                }
                Rait rait = new Rait (userName, sum);
                callback.callBack(rait);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

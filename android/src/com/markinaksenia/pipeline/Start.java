package com.markinaksenia.pipeline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class Start extends AppCompatActivity {
    Button btnPlay;
    TextView txtInform;

    FirebaseDatabase database;
    DatabaseReference questions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        txtInform = (TextView) findViewById(R.id.txtInform);

        Intent intent = getIntent();
        String inf = intent.getStringExtra("inform");
        txtInform.setText(inf);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");

        loadQuestions(Common.categoryId);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, Playing.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void loadQuestions(String categoryId) {

        if(Common.questionList.size()>0){
            Common.questionList.clear();
        }

        questions.orderByChild("categoryId").equalTo(categoryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Question ques = postSnapshot.getValue(Question.class);
                    Common.questionList.add(ques);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Collections.shuffle(Common.questionList);
    }
}

package com.markinaksenia.pipeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Raiting extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raiting);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RaitingFragment rait_fragm = RaitingFragment.newInstance();
        transaction.replace(R.id.frame_layout, rait_fragm);
        transaction.commit();


        bottomNavigationView =  (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_back:
                        intent = new Intent(Raiting.this, Home.class);
                        startActivity(intent);
                        break;
                }
                return true ;
            }
        });
    }


}

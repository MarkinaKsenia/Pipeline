package com.markinaksenia.pipeline;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Categories extends AppCompatActivity {
    Intent intent;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CategoryFragment c_fragm = CategoryFragment.newInstance();
        transaction.replace(R.id.frame_layout, c_fragm);
        transaction.commit();

        bottomNavigationView =  (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_back:
                        intent = new Intent (Categories.this, Home.class);
                        startActivity(intent);
                        break;
                }
                return true ;
            }
        });

    }
}

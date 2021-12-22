package com.markinaksenia.pipeline;

import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Menu extends BaseActivity {


    MaterialEditText edtNewUser, edtNewPassword,edtNewPassword2, edtNewEmail;
    TextInputEditText edtUser, edtPassword;

    Button btnSignUp, btnSignIn;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Firebase
    database = FirebaseDatabase.getInstance();
    users = database.getReference("Users");

    edtUser = ( TextInputEditText) findViewById(R.id.edtUser);
    edtPassword = ( TextInputEditText) findViewById(R.id.edtPassword);


    btnSignIn = (Button) findViewById(R.id.btn_sign_in);
    btnSignUp = (Button) findViewById(R.id.btn_sign_up);

        btnSignUp.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view){
            showSignUpDialog();
        }
    });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            signIn(edtUser.getText().toString(), edtPassword.getText().toString());

        }
    });

}

    private void signIn(final String user, final String pwd) {

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(user).exists())
                {
                    if(!user.isEmpty()){
                        User login =  dataSnapshot.child(user).getValue(User.class);
                        if (login.getPassword().equals(pwd)) {
                            Intent homeActivity = new Intent(Menu.this, Home.class);
                            Common.currentUser = login;
                            startActivity(homeActivity);
                            finish();
                        }
                        else
                            Toast.makeText(Menu.this, "Неверный пароль! Попробуйте еще раз", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Menu.this, "Пожалуйста, введите имя пользователя", Toast.LENGTH_SHORT).show();
                    }
                }
                else   Toast.makeText(Menu.this, "Такого пользователя не существует", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showSignUpDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Menu.this);
        alertDialog.setTitle("Зарегистрироваться");
        alertDialog.setMessage("Пожалуйста, введите полную информацию о себе");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate (R.layout.sign_up_layout,null);

        edtNewUser = (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewUserName);
        edtNewEmail = (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewEmail);
        edtNewPassword= (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewPassword);
        edtNewPassword2= (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewPassword2);

        alertDialog.setView(sign_up_layout);
        alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);

        alertDialog.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final User user = new User(edtNewUser.getText().toString(), edtNewPassword.getText().toString(), edtNewEmail.getText().toString());

                int ok1=1, ok2=1, ok3=1;

                if( edtNewPassword2.getText().toString()== null || edtNewUser.getText().toString().length()< 2 || edtNewEmail.getText().toString()==null || edtNewPassword2.getText().toString()== null || edtNewPassword.getText().toString()== null){
                    Toast.makeText(Menu.this, "Заполните все данные!", Toast.LENGTH_SHORT).show();
                    ok1=0;
                }

                if( edtNewPassword.getText().toString().length()<6 && edtNewPassword.getText().toString().length()!=0){
                    Toast.makeText(Menu.this, "Введите пароль не менее 6 знаков!", Toast.LENGTH_SHORT).show();
                    ok2=0;
                }

                if( ! edtNewPassword.getText().toString().equals(edtNewPassword2.getText().toString()) ){
                    Toast.makeText(Menu.this, "Введенные пароли не совпадают!", Toast.LENGTH_SHORT).show();
                    ok3=0;
                }


                if((ok1+ok2+ok3)==3){
                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child (user.getUserName()).exists())
                                Toast.makeText(Menu.this, "Такой пользователь уже существует!", Toast.LENGTH_SHORT).show();
                            else {
                                users.child(user.getUserName()).setValue(user);
                                Toast.makeText(Menu.this, "Успешная регистрация!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    dialog.dismiss();
                }



            }
        });
        alertDialog.show();
    }



@Override
    protected void onResume() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onResume();
    }

}

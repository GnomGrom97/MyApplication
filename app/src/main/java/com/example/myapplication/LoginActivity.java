package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//теперь это главная активити для входа
public class LoginActivity  extends AppCompatActivity {
    private EditText edLogin,edPassword;
    private FirebaseAuth mAuth;//  подключение бд
    private Button bStart, bSignUp, bSignIn, bSignOut;
    private TextView tvUserName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser cUser = mAuth.getCurrentUser();
            // updateUI(currentUser);
        if (cUser!=null){
            ShowSignet();
                  String userName = "Вы вошли как:" + cUser.getEmail();
                  tvUserName.setText(userName);
            //если пользователь не 0 то покажем окно приложения
            Toast.makeText( this, "User not null", Toast.LENGTH_SHORT).show();
        }
        else
        {
                notSignet();
            Toast.makeText( this, "User  null", Toast.LENGTH_SHORT).show();
        }

    }

    private  void init(){
        bSignOut=findViewById(R.id.bSignOut); //кнопка выхода
        tvUserName =findViewById(R.id.tvUserEmail);
        bStart =findViewById(R.id.bStart);//кнопка начать
        bSignIn =findViewById(R.id.bSignIn);//
        bSignUp =findViewById(R.id.bSignUp);
        edLogin=findViewById(R.id.edLogin);
        edPassword=findViewById(R.id.edPassword);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }
    public void OnClickSignUp(View view){    //регистрация пользователя + проверка на корректность данных
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString()))
        {
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(),edPassword.getText().toString())
             .addOnCompleteListener(task -> {

          if (task.isSuccessful()){
              ShowSignet();
              sendEmailVer();//сообщение о подтверждении почты
             Toast.makeText( getApplicationContext(), "User SigUp  Successful", Toast.LENGTH_SHORT).show();
          }
           else {
               notSignet();
              Toast.makeText( getApplicationContext(), "User SigUp not Successful", Toast.LENGTH_SHORT).show();
          }
             });
    }
      else {
           Toast.makeText( getApplicationContext(), "Please  enter Email and Password", Toast.LENGTH_SHORT).show();
        }
    }
    public void OnClickSignIn(View view){    //авторизация пользователя
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())) {
            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            ShowSignet();
                            Toast.makeText(getApplicationContext(), "User SigIp  Successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            notSignet();
                            Toast.makeText(getApplicationContext(), "User SigIp not Successful", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
              else {
        Toast.makeText( getApplicationContext(), "Please  enter Email and Password", Toast.LENGTH_SHORT).show();
   }
}
   public  void onClickSignOut(View view){
        FirebaseAuth.getInstance().signOut();
        notSignet();
 }
 private void ShowSignet(){
           FirebaseUser user =mAuth.getCurrentUser();
           
          assert  user != null;
        if(user.isEmailVerified()) { //запускается при подтверждении майла
              String userName = "Вы вошли как:" + user.getEmail();
              tvUserName.setText(userName);                         
            bSignOut.setVisibility(View.VISIBLE);
            bStart.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.VISIBLE);
            edLogin.setVisibility(View.GONE); //если пользователь вошел, то логин невидимый
            edPassword.setVisibility(View.GONE);
            bSignUp.setVisibility(View.GONE);
            bSignUp.setVisibility(View.GONE);
        }
        else {
            Toast.makeText( getApplicationContext(), "Подтвердите вашу почту    ", Toast.LENGTH_SHORT).show();
        }
        }

 private  void notSignet(){
           edLogin.setVisibility(View.VISIBLE); //если пользователь вошел, то логин невидимый
           edPassword.setVisibility(View.VISIBLE);
           bSignIn.setVisibility(View.VISIBLE);
           bSignUp.setVisibility(View.VISIBLE);
          bStart.setVisibility(View.GONE);   //GONE- невидимость
        tvUserName.setVisibility(View.GONE);
         bSignOut.setVisibility(View.GONE);
 }
 public void OnClickStart(View view) {   //запуск активити
     Intent  i =new Intent(LoginActivity.this,MenyActivity.class);//запуск гглавного класса
     startActivity(i);
 }
 private  void sendEmailVer(){   //подтверждение почты
        FirebaseUser user = mAuth.getCurrentUser();
       assert  user != null;   //проверка полььзователя на 0
  user.sendEmailVerification().addOnCompleteListener(task -> {
      if(task.isSuccessful()) {
       Toast.makeText( getApplicationContext(), "Проверьте вашу почту для подтверждения Email адреса", Toast.LENGTH_SHORT).show();
      }
      else{
       Toast.makeText( getApplicationContext(), "Send Email failed", Toast.LENGTH_SHORT).show();
      }
  });
 }
}
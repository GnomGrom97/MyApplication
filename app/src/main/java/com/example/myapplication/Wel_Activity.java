package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Wel_Activity extends AppCompatActivity {//отображение окна приветствия и фото
    private ImageView iV_Wel;
    private TextView tV_Wel;
    private Button buStart, button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//метод  создания с 1 параметром бамбле
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wel_layout);///////////////////////
        init();// инициализация переменной
    }
    @Override
    protected void onStart() {
        super.onStart();
       Show();
   }
    private  void  init (){//запуск действий
       iV_Wel = findViewById(R.id.iV_Wel);
        tV_Wel = findViewById(R.id.tV_Wel);
       buStart=findViewById(R.id.buStart);

    }
    public void OnClickRun(View view) {   //запуск с кнопки вход
      Intent i =new Intent(Wel_Activity.this, LoginActivity.class);//запуск гглавного класса
        startActivity(i);
    }
   public  void  Show (){
        tV_Wel.setText("Спасибо, что пользуетесь нашим сервисом.Вы делаете мир лучше");

    }
}

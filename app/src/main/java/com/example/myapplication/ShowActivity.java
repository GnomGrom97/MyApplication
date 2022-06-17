package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//активити отвечающая за отображение переменных
public class ShowActivity extends AppCompatActivity {
    private TextView tvName,tvSec_Name,tvEmail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);//подключение экрана
        init();//инициализация
        getIntentMain();//запуск намерения
    }
    private void  init(){
        tvName = findViewById(R.id.tvName);
        tvSec_Name = findViewById(R.id.tvSec_Name);
        tvEmail = findViewById(R.id.tvEmail);
    }
    private  void  getIntentMain(){
        Intent i = getIntent();
        if (i!=null){
            tvName.setText(i.getStringExtra(Constant.USER_NAME));
            tvSec_Name.setText(i.getStringExtra(Constant.USER_Sec_Name));
            tvEmail.setText(i.getStringExtra(Constant.USER_Email));
        }

    }
}

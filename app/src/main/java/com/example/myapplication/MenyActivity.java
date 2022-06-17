package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MenyActivity extends AppCompatActivity {
    private TextView tvOrder;//окно с чтением
    private Button Bburgers, BTvisterOR, bchBurger, bLepechka, bDrinks, bDesserts, bPepsi, bDonats, bPay;
    private FirebaseAuth mAuth;//


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//метод  создания с 1 параметром бамбле
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meny_layout);//запуск отрисовки  меню
        init();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Meny();
    }

    public void onClickBurgers(View view) {
        tvOrder.setText("Чизбургер 79р");
        Bburgers.setVisibility(View.GONE);
        tvOrder.setVisibility(View.GONE);
        BTvisterOR.setVisibility(View.GONE);
        bLepechka.setVisibility(View.GONE);
        bDrinks.setVisibility(View.GONE);
        bDesserts.setVisibility(View.GONE);//десерт
        tvOrder.setVisibility(View.VISIBLE);//заказ
        bchBurger.setVisibility(View.VISIBLE);//чиз
        bPepsi.setVisibility(View.GONE);
    }

    public void OnClickLepechka(View view) {
        tvOrder.setText("Твистер OR 139р");
        Bburgers.setVisibility(View.GONE);
        tvOrder.setVisibility(View.VISIBLE);
        BTvisterOR.setVisibility(View.VISIBLE);
        bLepechka.setVisibility(View.GONE);
        bDrinks.setVisibility(View.GONE);
        bDesserts.setVisibility(View.GONE);//десерт
        tvOrder.setVisibility(View.VISIBLE);//заказ
        bchBurger.setVisibility(View.GONE);//чиз
    }

    private void init() {//инициализация отрисовка всего
        tvOrder = findViewById(R.id.tvOrder);
        Bburgers = findViewById(R.id.Bburgers);
        BTvisterOR = findViewById(R.id.BTviterOR);
        bchBurger = findViewById(R.id.bchBurger);
        bLepechka = findViewById(R.id.bLepechka);
        bDrinks = findViewById(R.id.bDrinks);
        bDesserts = findViewById(R.id.bDesserts);
        bPepsi = findViewById(R.id.bPepsi);
        bDonats = findViewById(R.id.bDonats);
        bPay = findViewById(R.id.bPay);

    }

    private void Meny() {
        tvOrder.setVisibility(View.VISIBLE);
        Bburgers.setVisibility(View.VISIBLE);
        bLepechka.setVisibility(View.VISIBLE);
        bDrinks.setVisibility(View.VISIBLE);
        bDesserts.setVisibility(View.VISIBLE);
        bchBurger.setVisibility(View.GONE);//чиз
        bPepsi.setVisibility(View.GONE);
        BTvisterOR.setVisibility(View.GONE);
        bDonats.setVisibility(View.GONE);
        bPay.setVisibility(View.VISIBLE);


    }

    public void onClickBchBurger(View view) {
        Meny();
        tvOrder.setText("Чизбургер 79р\n");


    }

    public void onClickDrinks(View view) {
        tvOrder.setText("Пепси 79р\n");
        bPepsi.setVisibility(View.VISIBLE);
        tvOrder.setVisibility(View.VISIBLE);
        Bburgers.setVisibility(View.GONE);
        tvOrder.setVisibility(View.GONE);
        BTvisterOR.setVisibility(View.GONE);
        bLepechka.setVisibility(View.GONE);
        bDrinks.setVisibility(View.GONE);
        bDesserts.setVisibility(View.GONE);//десерт
        tvOrder.setVisibility(View.GONE);//заказ
        bchBurger.setVisibility(View.GONE);//чиз
        bPay.setVisibility(View.GONE);


    }

    public void onClickPepsi(View view) {
        Meny();
        tvOrder.setText("Чизбургер 79р\n Твистер OR 139р\nПепси 79р\n");
    }

    public void onClickTviterOR(View view) {
        Meny();
        tvOrder.setText("Чизбургер 79р\n Твистер OR 139р");
    }

    public void onClickDesserts(View view) {
        tvOrder.setText("Клубничный пончик 79р\n");
        Bburgers.setVisibility(View.GONE);
        tvOrder.setVisibility(View.GONE);
        BTvisterOR.setVisibility(View.GONE);
        bLepechka.setVisibility(View.GONE);
        bDrinks.setVisibility(View.GONE);
        bDesserts.setVisibility(View.GONE);//десерт
        tvOrder.setVisibility(View.GONE);//заказ
        bchBurger.setVisibility(View.GONE);//чиз
        bDonats.setVisibility(View.VISIBLE);
        bPay.setVisibility(View.GONE);
    }

    public void onClickbDonats(View view) {
        Meny();
        tvOrder.setText("Чизбургер 79р\n Твистер OR 139р\nКлубничный пончик 79р\nПепси 79р\n");
    }

    public void onClickbPay(View view) {
        Toast.makeText(getApplicationContext(), "Оплата прошла успешно\n Списано 376р", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Wel_Activity.class);//запуск гглавного класса
        startActivity(i);
    }

}


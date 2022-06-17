package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



///подключит базу данных кот
//ограничить базу данных на кол-во пользователей  по окончанию работ
public class MainActivity extends AppCompatActivity {
    private EditText edName, edEmail, edSurame;//создание приватных переменных
    private DatabaseReference myDataBase;//создание базы данных
    private String User_KEY = "User";// создание текстового ключа




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);//запуск активити майн
        init();
    }

    public void init() {//метод присваивания
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edSurame = findViewById(R.id.edSurame);
        myDataBase = FirebaseDatabase.getInstance().getReference(User_KEY);
    }

    public void onClickSave(View view) {//кнопка сохранения
        String id = myDataBase.getKey();//поле с передачей id в базу данных
        String name = edName.getText().toString(); // передача имени
        String sec_name = edSurame.getText().toString();//фамилии
        String email = edEmail.getText().toString();//майла
        User newUser = new User(id, name, sec_name, email);//инициализация переменной пользователя
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sec_name) && !TextUtils.isEmpty(email)) {//если поле имя не пустое то
            myDataBase.push().setValue(newUser);//добавление нового пользователя
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRead(View view) {
        //создание изапуск нового намерения- нового окна
        Intent i = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(i);
    }
}

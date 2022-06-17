package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

///нужно создать активити с текстовой вьшкой в которую передаются данные после выбора нужного пункта
public class ReadActivity extends AppCompatActivity {
    private ListView listView;//подключение чтения из бд
    private ArrayAdapter<String> adapter;//создание адаптера
    private List<String> listData;//создание списочного массива
    private List<User> listTemp;
    private DatabaseReference myDataBase;//создание базы данных
    private String User_KEY = "User";// создание текстового ключа

    //ошибка при запуске окна вылетает//добавить техт вью для окна заказов
    //если не робит добавляем активити в андроид манифест
    // <activity android:name=".ReadActivity"
//            android:exported="true">
//
//        </activity>
    //создать кнопку которая открывает окно с
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//метод с 1 параметром бамбле
        super.onCreate(savedInstanceState);

        setContentView(R.layout.read_layout);//дело не в окне
        init();
        //инициализация экрана чтения
        getDataFromDB();
        setOnClickItem();
    }

    private void init() {//инициализация чтения бд
        listView = findViewById(R.id.listView);//подключение и поиск по id листа
        listData = new ArrayList<>();//создание списочного массива
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, listData);//создание нового адаптера
        listView.setAdapter(adapter);//указываем что в listView будем указывать этот адаптер
        myDataBase = FirebaseDatabase.getInstance().getReference(User_KEY);
    }

    private void getDataFromDB() {//изменение информации в бд
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//метод для выдачи инфы
                // цикл для извлечения пользователей из бд
                if (listData.size() > 0)
                    listData.clear();//убеждаемся что список чистый, если нет, то чистим
                if (listTemp.size() > 0) listTemp.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    User user = ds.getValue(User.class);//получает каждого пользователя и помещает в массив
                    assert user != null;//проверка на то что User не пустой/
                    listData.add(user.name);
                    listTemp.add(user);//сохраняет имя пользователя в листдата
                }
                // оповещение arrayadapter об изменении данных для его обновления
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        myDataBase.addValueEventListener(vListener);
    }

    private void setOnClickItem() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            User user = listTemp.get(position);
            Intent i=new Intent(ReadActivity.this,ShowActivity.class);
            i.putExtra(Constant.USER_NAME,user.name);
            i.putExtra(Constant.USER_Sec_Name,user.sec_name);
            i.putExtra(Constant.USER_Email,user.email);
            startActivity(i);

        });
    }
}

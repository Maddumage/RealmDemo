package com.roshan.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.roshan.realmdemo.data.RealmManager;
import com.roshan.realmdemo.model.Student;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    EditText sId, sName, sAge;
    String id, name;
    int age;
    Button btnAdd;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sId = findViewById(R.id.etId);
        sName = findViewById(R.id.etName);
        sAge = findViewById(R.id.etAge);
        btnAdd = findViewById(R.id.btnAdd);

        try {
            RealmManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = sId.getText().toString();
                name = sName.getText().toString();
                age = Integer.parseInt(sAge.getText().toString());

                try {
                    Student student = new Student(id, name, age);
                    RealmManager.addStudent(student);
                    Student s = RealmManager.getStudent("xghh");
                    Log.d("student", " " + s.getId() + "" + s.getName());
                } finally {
                    RealmManager.close();
                    Toast.makeText(MainActivity.this, "Added Success", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

package com.roshan.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.roshan.realmdemo.adapter.StudentAdapter;
import com.roshan.realmdemo.data.RealmManager;
import com.roshan.realmdemo.model.Student;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    EditText sId, sName, sAge;
    String id, name;
    int age;
    Button btnAdd;
    PopupWindow mPopupWindow;
    ListView listView;
    RealmResults<Student> students;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPopupWindow = new PopupWindow();
        try {
            RealmManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Student> students = new ArrayList<>(RealmManager.getAll());
        listView = findViewById(R.id.list);

        studentAdapter = new StudentAdapter(this, students);
        listView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                addStudent();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addStudent() {

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_window, null);

        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setContentView(customView);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);


        sId = customView.findViewById(R.id.etId);
        sName = customView.findViewById(R.id.etName);
        sAge = customView.findViewById(R.id.etAge);
        btnAdd = customView.findViewById(R.id.btnAdd);

        mPopupWindow.showAtLocation(customView, Gravity.CENTER, 0, 0);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = sId.getText().toString();
                name = sName.getText().toString();
                age = Integer.parseInt(sAge.getText().toString());

                try {
                    Student student = new Student(id, name, age);
                    RealmManager.addStudent(student);
                   // Student s = RealmManager.getStudent("xghh");
                  //  Log.d("student", " " + s.getId() + "" + s.getName());
                } finally {
                    studentAdapter.notifyDataSetChanged();
                    mPopupWindow.dismiss();
                    Toast.makeText(MainActivity.this, "Added Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
        studentAdapter.notifyDataSetChanged();
    }
}

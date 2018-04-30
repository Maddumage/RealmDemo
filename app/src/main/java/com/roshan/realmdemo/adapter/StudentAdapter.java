package com.roshan.realmdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.roshan.realmdemo.R;
import com.roshan.realmdemo.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

    private Context context;
    private ArrayList<Student> students;

    public StudentAdapter(@NonNull Context context,ArrayList<Student> list) {
        super(context, 0 , list);
        this.context = context;
        this.students = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        Student student = students.get(position);
        TextView name = view.findViewById(R.id.textView_name);
        name.setText(""+student.getName());

        TextView release = view.findViewById(R.id.textView_age);
        release.setText(""+student.getAge());

        return view;
    }
}

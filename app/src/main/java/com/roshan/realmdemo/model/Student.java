package com.roshan.realmdemo.model;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject {

    @PrimaryKey
    @NonNull
    private String id;

    private String name;
    private int age;

    public Student (){}
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

//    public Student (@NonNull Realm realm){
//        this.realm = realm;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

package com.roshan.realmdemo.data;

import com.roshan.realmdemo.model.Student;

import io.realm.Realm;

public class RealmManager {

    private static Realm realm;

    public static Realm open(){
        realm =Realm.getDefaultInstance();
        return realm;
    }

    public static void close(){
        if(realm != null) {
            realm.close();
        }
    }

//    public static Student createStudent(){
//        checkForOpenRealm();
//        return new Student(realm);
//    }

    public static void clear(){
        checkForOpenRealm();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Student.class).findAll().deleteAllFromRealm();
            }
        });
    }


    public static void deleteStudent(final String id){

        checkForOpenRealm();

        final Student studentToDelete = realm.where(Student.class).equalTo("id", id).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                studentToDelete.deleteFromRealm();
            }
        });
    }

    public static void addStudent(final Student student){
        checkForOpenRealm();
        //final Student s = realm.where(Student.class).equalTo("id",student.getId()).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(student);
            }
        });
        //realm.commitTransaction();
    }

    private static void checkForOpenRealm() {
        if (realm == null || realm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }

    public static Student getStudent(String id){
        checkForOpenRealm();
        final Student student = realm.where(Student.class).equalTo("id",id).findFirst();
        return student;
    }

}

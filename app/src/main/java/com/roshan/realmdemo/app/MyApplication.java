package com.roshan.realmdemo.app;

import android.app.Application;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private static MyApplication myInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize realm database
        myInstance = this;
        Realm.init(this);

//         The RealmConfiguration is created using the builder pattern.
//         The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("my_realm_db.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Use the config
        Realm.setDefaultConfiguration(config);

    }

    public byte[] getKey() {
        KeyGenerator gen = null;
        try {
            gen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        gen.init(512); /* 512-bit AES */
        SecretKey secret = gen.generateKey();
        byte[] binary = secret.getEncoded();
       return binary;
    }
}

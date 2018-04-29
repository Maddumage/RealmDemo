# RealmDemo
Realm Database Demo Application

## About Realm Database
* Realm Database is new database platform to manage android application data. 
* It is a alternative database for SQLite and Core Data. 
* Realm is an Object Database and much faster than SQLite. (10x)

## How to start 

* Add classpath to the build.gradle in Project level

      repositories {
              google()
              jcenter()
          }
          dependencies {
              classpath "io.realm:realm-gradle-plugin:5.1.0"
          }
        
* Add realm plugin to the build.gradle in Application level

      apply plugin: 'realm-android'

* Define your model class by extending the RealmObject class

      public class Student extends RealmObject {
      
          @PrimaryKey   // set primary key
          private String id;

          private String name;
          private int age;
          
          // getters and setters
          ...
* First we need to initialize Realm (Only one for an application) using this syntax and best place to initiate the instance of realm is in onCreate method on an Application sub class. (Also you need add your Application subclass to Manifest.xml file)

      Realm.init(context);
      ------------------------------------
      
      public class MyApplication extends Application {
    
        @Override
        public void onCreate() {
            super.onCreate();
            Realm.init(this);
        }
      }
      ------------------------------------
      <application
        android:name=".app.MyApplication"
     
* Get a Realm instance

      Realm realm = Realm.getDefaultInstance();
      
* This default instance method will initiate default realm configuration. To create our own realm configuration,

      RealmConfiguration config = new RealmConfiguration.Builder().build();
      
      RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .encryptionKey(getKey())
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Use the config
        Realm.setDefaultConfiguration(config);
      
* Insert Record to the database

      // create new instance of realm
      Realm realm = Realm.getDefaultInstance();
      // start transaction with realm then execute
      realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(student); // add student model to realm dab
            }
        });
        
* Update Record

      realm.executeTransaction(new Realm.Transaction() {
                  @Override
                  public void execute(Realm realm) {
                      realm.copyToRealmOrUpdate(student); // update student model
                  }
              });
              
* Delete Record
       
       // find item to delete
      final Student studentToDelete = realm.where(Student.class)
                      .equalTo("id", id)
                      .findFirst();

              realm.executeTransaction(new Realm.Transaction() {
                  @Override
                  public void execute(Realm realm) {
                      studentToDelete.deleteFromRealm();
                  }
              });
      
     

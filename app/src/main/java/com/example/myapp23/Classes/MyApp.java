package com.example.myapp23.Classes;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyApp extends Application {

    public static final String TAG = "MyApp";

    public static FirebaseFirestore myCollStore;
    public static FirebaseDatabase myDB;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: MyApp Class");

        myCollStore = FirebaseFirestore.getInstance();
        myDB = FirebaseDatabase.getInstance();

    }
}

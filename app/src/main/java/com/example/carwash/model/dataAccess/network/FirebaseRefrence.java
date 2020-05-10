package com.example.carwash.model.dataAccess.network;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRefrence {
    public static String USER = "Users";
    public static String WORKPLACE_INFOR = "Workplace_information";
    public static String WORKPLACE_DEFAULTSETTING = "Workplace_default_setting";

    public static FirebaseAuth getFirebaseInnstance() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseDatabase getFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }
}

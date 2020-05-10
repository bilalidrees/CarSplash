package com.example.carwash.model.essentials;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.carwash.model.dataAccess.entities.User;
import com.google.gson.Gson;

public class SessionClass {
    static SessionClass sessionClass;

    public static SessionClass getSession() {
        if (sessionClass == null)
            sessionClass = new SessionClass();
        return sessionClass;
    }

    public void setUser(Context context, User user, String key) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(key, json);
        prefsEditor.apply();
    }

    public User getUser(Context context, String key) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = mPrefs.getString(key, "");
        return gson.fromJson(json, User.class);
    }
}

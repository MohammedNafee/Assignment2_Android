package edu.cs.birzeit.assignment2_android.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferencesManager {

    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String FLAG = "FLAG";
    private static final String REGISTERED = "REGISTERED";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    // Singleton instance
    private static UserPreferencesManager instance;

    private UserPreferencesManager(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
    }

    public static synchronized UserPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserPreferencesManager(context.getApplicationContext());
        }
        return instance;
    }

    public void saveUserDetails(String firstName, String lastName, String email, String password) {
        editor.putString(FIRST_NAME, firstName);
        editor.putString(LAST_NAME, lastName);
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void saveLoginState(boolean flag) {
        editor.putBoolean(FLAG, flag);
        editor.apply();
    }

    public void setRegistered(boolean registered) {
        editor.putBoolean(REGISTERED, registered);
        editor.apply();
    }

    public boolean isRegistered() {
        return prefs.getBoolean(REGISTERED, false);
    }

    public String getFirstName() {
        return prefs.getString(FIRST_NAME, "");
    }

    public String getLastName() {
        return prefs.getString(LAST_NAME, "");
    }

    public String getEmail() {
        return prefs.getString(EMAIL, "");
    }

    public String getPassword() {
        return prefs.getString(PASSWORD, "");
    }

    public boolean getLoginState() {
        return prefs.getBoolean(FLAG, false);
    }
}

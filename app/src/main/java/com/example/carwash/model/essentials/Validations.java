package com.example.carwash.model.essentials;

import android.util.Patterns;
import android.widget.EditText;

public class Validations {
    public static boolean validation(EditText email, EditText password){

        if (!email.getText().toString().isEmpty())
            if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                if (!password.getText().toString().isEmpty())
                    if (!(password.getText().length() < 6))
                        return true;
                    else {
                        password.setError("Minimum Password Limit is 6");
                        password.requestFocus();
                        return false;
                    }
                else {
                    password.setError("Enter your Password!");
                    password.requestFocus();
                    return false;
                }
            else {
                email.setError("Invalid Email!");
                email.requestFocus();
                return false;
            }
        else {
            email.setError("Enter your Email!");
            email.requestFocus();
            return false;
        }
    }
}

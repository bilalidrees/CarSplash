package com.example.carwash.model.utility;

import android.app.ProgressDialog;

public class AlertDialogUtility {
    public static void  showDialog(ProgressDialog progressDialog, String message){
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static void dismissDialog(ProgressDialog progressDialog){
        progressDialog.dismiss();
    }
}

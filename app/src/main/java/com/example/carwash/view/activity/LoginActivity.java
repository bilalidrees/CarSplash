package com.example.carwash.view.activity;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carwash.R;
import com.example.carwash.databinding.ActivityLoginBinding;
import com.example.carwash.model.dataAccess.entities.User;
import com.example.carwash.model.dataAccess.network.FirebaseRefrence;
import com.example.carwash.model.essentials.AppLevelConstant;
import com.example.carwash.model.essentials.SessionClass;
import com.example.carwash.model.essentials.Validations;
import com.example.carwash.model.utility.AlertDialogUtility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    ActivityLoginBinding activityLoginBinding;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseRefrence.getFirebaseDatabase();
        mUserDatabaseReference = mFirebaseDatabase.getReference().child(FirebaseRefrence.USER);
        setOnnclickListners();
    }

    private void setOnnclickListners() {
        activityLoginBinding.btnSubmit.setOnClickListener(this);
        activityLoginBinding.signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                Login();
                break;

            case R.id.signUp:
                Intent loginIntent = new Intent(this, SignUpActivity.class);
                startActivity(loginIntent);
                finish();
                break;

        }
    }

    private void Login() {
        if (Validations.validation(activityLoginBinding.etUserName, activityLoginBinding.etPassword)) {
            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            AlertDialogUtility.showDialog(progressDialog, "Authenticating...");

            mAuth.signInWithEmailAndPassword(activityLoginBinding.etUserName.getText().toString(), activityLoginBinding.etPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                AlertDialogUtility.dismissDialog(progressDialog);
                                mUserDatabaseReference.child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        User user = dataSnapshot.getValue(User.class);
                                        if (user != null) {
                                            SessionClass.getSession().setUser(LoginActivity.this, user, AppLevelConstant.USER_KEY);
                                            navigateToNextScreen();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                        }
                    });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null && SessionClass.getSession().getUser(this, AppLevelConstant.USER_KEY) != null) {
            navigateToNextScreen();
        }
    }

    private void navigateToNextScreen() {
        Intent studentIntent = new Intent(LoginActivity.this, LocationPermission.class);
        studentIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(studentIntent);
        finish();
    }


}

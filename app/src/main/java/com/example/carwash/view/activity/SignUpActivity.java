package com.example.carwash.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.carwash.R;
import com.example.carwash.databinding.ActivitySignUpBinding;
import com.example.carwash.model.dataAccess.entities.User;
import com.example.carwash.model.dataAccess.network.FirebaseRefrence;
import com.example.carwash.model.essentials.Validations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {
    ActivitySignUpBinding activitySignUpBinding;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseRefrence.getFirebaseDatabase();
        mUserDatabaseReference = mFirebaseDatabase.getReference().child(FirebaseRefrence.USER);
        setOnnclickListners();

    }

    private void setOnnclickListners() {
        activitySignUpBinding.btnSubmit.setOnClickListener(this);
        activitySignUpBinding.login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                registerUser();
                break;

            case R.id.login:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
                break;

        }
    }

    private void registerUser() {
        if (Validations.validation(activitySignUpBinding.etEmail, activitySignUpBinding.etPassword))
            mAuth.createUserWithEmailAndPassword(activitySignUpBinding.etEmail.getText().toString(), activitySignUpBinding.etPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String mUserID = mAuth.getUid();
                                User currentUser = new User(mUserID, activitySignUpBinding.etUserName.getText().toString(), activitySignUpBinding.etEmail.getText().toString(), activitySignUpBinding.etPhone.getText().toString());
                                Toast.makeText(SignUpActivity.this, "Sign Up Successfull!", Toast.LENGTH_SHORT).show();
                                mUserDatabaseReference = mUserDatabaseReference.child(mUserID);
                                mUserDatabaseReference.setValue(currentUser);

                                Intent studentIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                                studentIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(studentIntent);
                                finish();

                            } else {
                                task.getException();
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }


}

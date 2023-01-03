package com.project.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInSignUp extends AppCompatActivity {

    Button sign_in, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
    }
    // End Of On Create Method.

    public void goToSignUp(View view){
        Intent intent = new Intent(SignInSignUp.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
    // End Of Go To Sign Up Activity.

    public void goToSignIn(View view){
        Intent intent = new Intent(SignInSignUp.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
    // End Of Go To Sign In Activity.
}
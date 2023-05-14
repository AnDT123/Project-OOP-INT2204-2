package com.oop.iotapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email;
    private EditText et_password;
    private Button bt_loginButton;
    private TextView tv_registerForward;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        bt_loginButton.setOnClickListener(e -> {
            loginProcess();
        });

        tv_registerForward.setOnClickListener(e -> {
            Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(register);
        });
    }

    private void loginProcess() {

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("uid", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid().toString());
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                            et_email.setText("");
                            et_password.setText("");
                        }
                    }
                });
    }

    private void initViews() {
        et_email = findViewById(R.id.edittext_email_login);
        et_password = findViewById(R.id.edittext_password_login);
        bt_loginButton = findViewById(R.id.button_login);
        tv_registerForward = findViewById(R.id.textview_register_foward);

        mAuth = FirebaseAuth.getInstance();
    }
}
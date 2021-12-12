package com.nth.gamevui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {

    EditText edtGmailQuenMK;
    Button btnSendPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        anhxa();
        forgotPassword();
    }

    private void forgotPassword(){
        btnSendPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = edtGmailQuenMK.getText().toString().trim();
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotActivity.this, "Email sent! please check your email!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(ForgotActivity.this, "Email sending failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    private void anhxa(){
        edtGmailQuenMK= (EditText) findViewById(R.id.edtGmailQuenMK);
        btnSendPass= (Button) findViewById(R.id.btnSendPass);
    }
}
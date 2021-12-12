package com.nth.gamevui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText edtGmailLogin, edtPassLogin;
    Button btnDangNhap;
    TextView txt_chua_co_tai_khoan, tvName, tvQuenMK;
    FirebaseAuth mAuth;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        txt_chua_co_tai_khoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        mAuth= FirebaseAuth.getInstance();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

//        tvQuenMK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
//                startActivity(intent);
//            }
//        });

    }
    private void login(){
        String gmail= edtGmailLogin.getText().toString().trim();
        String pass= edtPassLogin.getText().toString().trim();
        if(TextUtils.isEmpty(gmail)){
            edtGmailLogin.setError("Nhập gmail!!!");
            return;
        }

        if(TextUtils.isEmpty(pass)){
            edtPassLogin.setError("Nhập pass!!!");
            return;
        }
        if(pass.length()<6){
            edtPassLogin.setError("Pass phải >= 6 ký tự!!!");
            return;
        }

        mAuth.signInWithEmailAndPassword(gmail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()==true){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(LoginActivity.this, LevelActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_LONG).show();
//                            count++;
//                            if(count == 3) {
//                                btnDangNhap.setVisibility(View.INVISIBLE);
//                                count = 0;
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        btnDangNhap.setVisibility(View.VISIBLE);
//                                    }
//                                }, 5000);
//                            }
                        }
                    }
                });
    }


    private void mapping(){
        edtGmailLogin= (EditText) findViewById(R.id.edtGmailLogin);
        edtPassLogin= (EditText) findViewById(R.id.edtPassLogin);
        btnDangNhap= (Button) findViewById(R.id.btnDang_Nhap);
        txt_chua_co_tai_khoan= (TextView) findViewById(R.id.txt_chua_co_tai_khoan);
        tvName= (TextView) findViewById(R.id.tvName);
//        tvQuenMK= (TextView) findViewById(R.id.tvQuenMK);
    }


}
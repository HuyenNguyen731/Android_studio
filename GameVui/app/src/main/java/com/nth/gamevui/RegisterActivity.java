package com.nth.gamevui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText edtHo, edtTen,edtGmail, edtPass, edtRePass;
    Button btnDangKy;
    TextView txt_dang_ky_3;
    FirebaseAuth mAuth;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mapping();

        txt_dang_ky_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() !=null){
            Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
            finish();
        }
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
            }
        });
    }

    protected void register(){
        String ho= edtHo.getText().toString().trim();
        String ten= edtTen.getText().toString().trim();
        String gmail= edtGmail.getText().toString().trim();
        String pass= edtPass.getText().toString().trim();

        if(TextUtils.isEmpty(gmail)){
            edtGmail.setError("Nhập gmail!!!");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            edtPass.setError("Nhập password!!!");
            return;
        }
        if(pass.length()<6){
            edtPass.setError("Pass phải >= 6 ký tự!!!");
            return;
        }
        mAuth.createUserWithEmailAndPassword(gmail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()==true){
                            FirebaseUser user= mAuth.getInstance().getCurrentUser();
                            FirebaseDatabase database= FirebaseDatabase.getInstance();
                            assert user != null;
                            String id= user.getUid();
                            DatabaseReference ref= database.getReference("list_user").child(id);
                            HashMap<String, Object> hash= new HashMap<>();
                            hash.put("Id", id);
                            hash.put("Họ", ho);
                            hash.put("Tên", ten);
                            hash.put("Gmail", gmail);
                            ref.setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()==true) {
                                        Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this,"Add user fail",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    private void mapping(){
        edtHo= (EditText) findViewById(R.id.edtHo);
        edtTen= (EditText) findViewById(R.id.edtTen);
        edtGmail= (EditText) findViewById(R.id.edtGmail);
        edtPass= (EditText) findViewById(R.id.edtPass);
        edtRePass= (EditText) findViewById(R.id.edtRePass);
        btnDangKy= (Button) findViewById(R.id.btnDangKy);
        txt_dang_ky_3= (TextView) findViewById(R.id.txt_dang_ky_3);
    }
}
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
import com.google.firebase.auth.FirebaseUser;

public class ChangeActivity extends AppCompatActivity {

    EditText edtNewPass;
    Button btnChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        anhxa();
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePass();
            }
        });
    }

    private void changePass(){
        String newPass= edtNewPass.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(newPass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangeActivity.this, "User password updated", Toast.LENGTH_SHORT).show();
                        }else{
                            //hiển thị lên nhập lại mật khẩu và email để xác nhận người dùng lại khi đăng nhập quá lâu
                        }
                    }
                });
    }

    private void anhxa(){
        edtNewPass= (EditText) findViewById(R.id.edtNewPass);
        btnChangePass= (Button) findViewById(R.id.btnChangePass);
    }
}
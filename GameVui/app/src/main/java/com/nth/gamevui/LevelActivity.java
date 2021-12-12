package com.nth.gamevui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class LevelActivity extends AppCompatActivity {
    Button btnEasy, btnMedium, btnDifficult;
    TextView tvName;
    Menu menu_logout, menu_change_pass;
    FirebaseAuth mAuth;
    FirebaseUser fUser;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        btnEasy= (Button)findViewById(R.id.btnEasy);
        btnMedium= (Button)findViewById(R.id.btnMedium);
        btnDifficult= (Button)findViewById(R.id.btnDifficult);
        tvName= (TextView) findViewById(R.id.tvName);
        menu_logout= (Menu) findViewById(R.id.menu_logout);
        menu_change_pass= (Menu) findViewById(R.id.menu_change_pass);

        setNameUser();
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, PlayActivity.class);
                intent.putExtra("level", "list_question");
                startActivity(intent);
                LevelActivity.this.finish();
            }
        });

        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, PlayActivity.class);
                intent.putExtra("level", "list_question2");
                startActivity(intent);
                LevelActivity.this.finish();
            }
        });

        btnDifficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, PlayActivity.class);
                intent.putExtra("level", "list_question3");
                startActivity(intent);
                LevelActivity.this.finish();
            }
        });
    }
    private void setNameUser() {
        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        if (fUser != null) {
            database = FirebaseDatabase.getInstance();
            ref = database.getReference("list_user").child(fUser.getUid());
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String userName = snapshot.child("Họ").getValue().toString() + " " + snapshot.child("Tên").getValue().toString();
                    tvName.setText(userName);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LevelActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(LevelActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gamevui, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        FirebaseAuth.getInstance().signOut();
//        Intent intent1= new Intent(LevelActivity.this, MainActivity.class);
//        startActivity(intent1);
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        Intent intent1= new Intent(LevelActivity.this, MainActivity.class);
        startActivity(intent1);
        switch (item.getItemId()){
            case R.id.menu_logout:{
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(LevelActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
            }

            case R.id.menu_change_pass:{
                Intent intent = new Intent(LevelActivity.this, ChangeActivity.class);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
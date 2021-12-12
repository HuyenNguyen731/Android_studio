package com.nth.gamevui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Button btnPlayAgain;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initUi();

        Intent intent = getIntent();
        String noiDung = intent.getStringExtra("Score");
//        playDung();
        score.setText(noiDung);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, LevelActivity.class);
                startActivity(intent);
                ResultActivity.this.finish();
            }
        });

    }

    private void initUi() {
        btnPlayAgain = findViewById(R.id.btn_playagain);
        score        = findViewById(R.id.tv_diem);
    }

    private void playDung(){
        MediaPlayer mediaPlayer= MediaPlayer.create(this, R.raw.result);
        mediaPlayer.start();

    }
}
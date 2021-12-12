package com.nth.gamevui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huawei.hms.api.bean.HwAudioPlayItem;
import com.huawei.hms.audiokit.player.callback.HwAudioConfigCallBack;
import com.huawei.hms.audiokit.player.manager.HwAudioManager;
import com.huawei.hms.audiokit.player.manager.HwAudioManagerFactory;
import com.huawei.hms.audiokit.player.manager.HwAudioPlayerConfig;
import com.huawei.hms.audiokit.player.manager.HwAudioPlayerManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.feature.service.AuthService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="Fire Hua";
    TextView tvRegister, tvLogin;
    Button btnPlay, btnRank, btnOff;
    ImageButton imgLoa, imgKhongLoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        register();
        login();
        play();
        off();
//        playSound();

    }


    private void register(){
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(){
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void  play(){
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPlay = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intentPlay);
            }
        });
    }

    private void off(){
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    private void mapping(){
        btnPlay     = findViewById(R.id.btnPlay);
        btnOff = (Button) findViewById(R.id.btnOff);
        tvRegister= (TextView) findViewById(R.id.tvRegister);
        tvLogin= (TextView) findViewById(R.id.tvLogin);
//        imgLoa= (ImageButton) findViewById(R.id.imgLoa);
//        imgKhongLoa= (ImageButton) findViewById(R.id.imgKhongLoa);
    }

//------------------- nhạc nền -----------------------//
    private void playSound(){
        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.result);
        mPlayer.start();
        mPlayer.setLooping(true);
        imgLoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.start();
                mPlayer.setLooping(true);
            }
        });
        imgKhongLoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.pause();
            }
        });
    }


}
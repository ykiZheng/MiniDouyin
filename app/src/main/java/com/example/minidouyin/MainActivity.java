package com.example.minidouyin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.minidouyin.API.Constants;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MainActivity extends AppCompatActivity {

    public static final int LOGIN_REQUEST_CODE = 6342;
    public static final int LOGIN_ANONIMAL_CODE= 6343;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView gif_login = findViewById(R.id.gif_login);
        ImageView gif_start = findViewById(R.id.gif_start);
        ImageView cloth = findViewById(R.id.cloth);

        Button quickstart = findViewById(R.id.quickstart);
        Button login = findViewById(R.id.login);

        Glide.with(MainActivity.this).load(R.raw.music_dance)
                .transition(withCrossFade())
                .into(gif_login);

        Glide.with(MainActivity.this).load(R.raw.music)
                .into(gif_start);

        ObjectAnimator start_end = ObjectAnimator.ofFloat(gif_start, "alpha", 1, 0);
        start_end.setStartDelay(4530);
        start_end.setDuration(300);
        start_end.start();

        ObjectAnimator cloth_end = ObjectAnimator.ofFloat(cloth, "alpha", 1, 0);
        cloth_end.setStartDelay(4530);
        cloth_end.setDuration(3000);
        cloth_end.start();

        quickstart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainPageActivity.class);
            intent.putExtra("username", "Anonymous");
            intent.putExtra("id", "000");
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            // 如果登录成功

        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK && data != null){
//            Constants.stduentID = data.getStringExtra("user_id");
//            Constants.studentName = data.getStringExtra("user_name");
//            Log.i("Constant",Constants.stduentID);
//            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//        }
//    }
}

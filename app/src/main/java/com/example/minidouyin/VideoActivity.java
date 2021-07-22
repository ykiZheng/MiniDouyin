package com.example.minidouyin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatExtras;

import com.bumptech.glide.Glide;
import com.example.minidouyin.API.UserMessage;
import com.example.minidouyin.Personal.MyvideoFragment;

import java.net.URL;

import retrofit2.http.Url;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class VideoActivity extends AppCompatActivity {

    private int star = 0;
    private int like = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        final ImageView gif_center = findViewById(R.id.gif_center);

        final ImageButton star_empty = findViewById(R.id.star_empty);
        final ImageButton star_full = findViewById(R.id.star_full);
        final ImageView star_mov = findViewById(R.id.star_mov);

        final ImageButton like_empty = findViewById(R.id.like_empty);
        final ImageButton like_full = findViewById(R.id.like_full);

        final VideoView video = findViewById(R.id.video);
        final TextView text1 = findViewById(R.id.video_id1);
        final TextView text2 = findViewById(R.id.video_id2);
        final TextView text3 = findViewById(R.id.video_id3);
        final TextView text4 = findViewById(R.id.video_id4);
        final TextView text5 = findViewById(R.id.video_id5);

        UserMessage user;
        Intent intent = getIntent();
        user = (UserMessage)intent.getSerializableExtra("User");
        Uri addr = Uri.parse(user.video_url);
        video.setVideoURI(addr);
        video.setMediaController(new MediaController(this));
        video.start();

        text1.setText("用户名:" + user.user_name);
        text2.setText("用户ID:" + user.studentId);
        text3.setText("备注信息:" + user.extra_value);
        text4.setText("创建时间:" + user.updatedAt);
        text5.setText("上传时间:" + user.createdAt);

        // 收藏标志：star
        star_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star == 0) {
                    star_empty.setAlpha((float) 0);
                    star_mov.setAlpha((float)1);
                    star = 1;

                    ObjectAnimator star_disx = ObjectAnimator.ofFloat(star_mov, "scaleX", 0, 20);
                    ObjectAnimator star_disy = ObjectAnimator.ofFloat(star_mov, "scaleY", 0, 20);
                    ObjectAnimator star_disa = ObjectAnimator.ofFloat(star_mov, "alpha", 1, 0);
                    ObjectAnimator star_ful = ObjectAnimator.ofFloat(star_full, "alpha", 0, 1);
                    AnimatorSet star_dis  = new AnimatorSet();
                    star_dis.playTogether(star_disx, star_disy, star_disa, star_ful);
                    star_dis.setDuration(1000);
                    star_dis.start();

                    MainActivity.personalHome.add_like(user.getmessage());
                }
                else{
                    star_empty.setAlpha((float)0.3);
                    star_full.setAlpha((float)0);
                    star = 0;
                }
            }
        });

        // 点赞标志：like
        like_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(like == 0) {
                    like = 1;
                    ObjectAnimator like_empr = ObjectAnimator.ofFloat(like_empty, "rotationY", 0F, 360F);
                    ObjectAnimator like_fulr = ObjectAnimator.ofFloat(like_full, "rotationY", 0F, 360F);
                    ObjectAnimator like_empa = ObjectAnimator.ofFloat(like_empty,"alpha",(float)0.3, (float)0);
                    ObjectAnimator like_fula = ObjectAnimator.ofFloat(like_full,"alpha",(float)0, (float)1);

                    like_empr.setDuration(1000);
                    like_fulr.setStartDelay(500);
                    like_fulr.setDuration(1000);
                    like_empa.setDuration(1000);
                    like_fulr.setStartDelay(500);
                    like_fula.setDuration(1000);

                    like_empa.start();
                    like_empr.start();
                    like_fula.start();
                    like_fulr.start();
                }
                else{
                    like_empty.setAlpha((float)0.3);
                    like_full.setAlpha((float)0);
                    like = 0;
                }
            }
        });
        like_full.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Glide.with(VideoActivity.this).load(R.raw.heart)
                        .transition(withCrossFade())
                        .into(gif_center);
                return false;
            }
        });
        like_full.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){     // 长按结束
                    Glide.with(VideoActivity.this).load("")
                            .transition(withCrossFade())
                            .into(gif_center);
                }
                return false;
            }
        });

    }
}

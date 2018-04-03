package com.example.vanphu.qlchitieu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class intro extends AppCompatActivity {
    ImageView imgview;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);
        init();
        Animation animation= AnimationUtils.loadAnimation(intro.this,R.anim.anim_translate);
        imgview.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences =getSharedPreferences("datalogin",MODE_PRIVATE);
                final String username=sharedPreferences.getString("taikhoan","");
                if(username==""){
                    Intent intent=new Intent(intro.this,introslide.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(intro.this,manhinhchinh.class);
                    intent.putExtra("emailintro",username);
                    startActivity(intent);
                }

            }
        }, 5500);
    }
    public void init(){
        imgview=(ImageView) findViewById(R.id.imgview);
    }
}

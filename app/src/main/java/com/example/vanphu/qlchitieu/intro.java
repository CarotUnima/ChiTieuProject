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
//        declare animation
        Animation animation= AnimationUtils.loadAnimation(intro.this,R.anim.anim_translate);
//        set animation
        imgview.startAnimation(animation);
//        delay Intent
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                declare sharedpreferences
                sharedPreferences =getSharedPreferences("datalogin",MODE_PRIVATE);
//                get email to shared saved
                final String username=sharedPreferences.getString("taikhoan","");
//              if email == null to ordered app
                if(username==""){
                    Intent intent=new Intent(intro.this,introslide.class);
                    startActivity(intent);
//               to activity main
                }else{
                    Intent intent=new Intent(intro.this,manhinhchinh.class);
                    intent.putExtra("email",username);
                    startActivity(intent);
                }

            }
        }, 5500);
    }
    public void init(){
        imgview=(ImageView) findViewById(R.id.imgview);
    }
}

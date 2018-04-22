package com.example.vanphu.qlchitieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class forgetpassword extends AppCompatActivity {
    TextView textview;
    Button btnquaylai,btnchanger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        hidden title main
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgetpassword);
        init();
//        text align center textview
        textview.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//        Previous
        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        next page activity
        btnchanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(forgetpassword.this,changerpassword.class);
                startActivity(intent);
            }
        });
    }
    public void init(){
        textview=(TextView) findViewById(R.id.textview);
        btnquaylai=(Button) findViewById(R.id.btnquaylai);
        btnchanger=(Button) findViewById(R.id.btnchanger);
    }
}

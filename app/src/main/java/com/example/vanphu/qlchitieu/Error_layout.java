package com.example.vanphu.qlchitieu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class Error_layout extends AppCompatActivity {
    Button btnthulai;
    ProgressBar pro1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_error_layout);
        init();
        pro1.setVisibility(View.INVISIBLE);
        btnthulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Progresbar
                pro1.setVisibility(View.VISIBLE);
//                hidden button
                btnthulai.setVisibility(View.INVISIBLE);
//                delay intent
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(Error_layout.this,intro.class);
                        startActivity(intent);
                    }
                }, 3500);
            }
        });
    }
    public void init(){
        btnthulai=(Button) findViewById(R.id.btnthulai);
        pro1=(ProgressBar) findViewById(R.id.pro1);
    }
}

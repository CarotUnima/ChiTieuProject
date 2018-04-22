package com.example.vanphu.qlchitieu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.transform.Templates;

public class thongke extends AppCompatActivity implements OnChartValueSelectedListener{
    PieChart pieChart;
    Button btnquayve;
    ArrayList<PieEntry> yValues=new ArrayList<>();
    PieDataSet dataset;
    String url1="https://vanphudhsp2015.000webhostapp.com/getchitieu.php?email=";
    int sum=0;
    String matien="đ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_thongke);
        init();

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(31f);
        Intent intent=getIntent();
        matien=intent.getStringExtra("matien");
        ReadJson1(url1+""+intent.getStringExtra("email"));
        btnquayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void ReadJson1(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i=0;i<response.length();i++){
                            try{
                                JSONObject object=response.getJSONObject(i);
                                int tien=Integer.parseInt(object.getString("giachitieu"));
                                String tenchitieu=object.getString("tenchitieu");
                                yValues.add(new PieEntry(tien,tenchitieu));
                                pieChart.animateY(1000, Easing.EasingOption.EaseInCubic);
                                sum+=tien;
                                 dataset=new PieDataSet(yValues,"Dữ Liệu");
                                dataset.setSliceSpace(3f);
                                dataset.setSelectionShift(5f);
                                dataset.setColors(ColorTemplate.MATERIAL_COLORS);

                                PieData data=new PieData((dataset));
                                data.setValueTextSize(20f);
                                data.setValueTextColor(Color.RED);
                                data.setValueFormatter(new PercentFormatter());

                                pieChart.setData(data);
                                pieChart.setOnChartValueSelectedListener(thongke.this);
                            }catch (Exception e){
                                Toast.makeText(thongke.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
                            }
                        }
                        pieChart.setCenterText("Tổng chi tiêu là: "+sum);
                        pieChart.setCenterTextSize(20f);
                        pieChart.setCenterTextColor(Color.GRAY);
                        pieChart.setNoDataTextColor(Color.BLUE);
                        ;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(manhinhchinh.this,"Lỗi 123"+error.toString(),Toast.LENGTH_LONG).show();
                        error();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void error(){
        Intent intent=new Intent(thongke.this,Error_layout.class);
        startActivity(intent);
    }
    public void data(){
        yValues.add(new PieEntry(50000,"Party A"));
        yValues.add(new PieEntry(6000,"Party B"));
        yValues.add(new PieEntry(120000,"Party C"));
        yValues.add(new PieEntry(9500,"Party A"));
    }
    public void init(){
        pieChart=(PieChart) findViewById(R.id.pieChart);
        btnquayve=(Button) findViewById(R.id.btnquayve);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        try{
            if (e == null)
                return;
            int intSelectedPos = (int) h.getX();
            if (intSelectedPos >= 0) {
                for (int i = 0; i < dataset.getColors().size(); i++) {

                }
                if (intSelectedPos == (int) h.getX()) {

                }
            }
            Toast.makeText(thongke.this, "Giá Chi Tiêu: " + e.getY()+" "+matien,Toast.LENGTH_LONG).show();
        }catch (Exception e1){
            Toast.makeText(thongke.this,"Lỗi"+e.toString().trim(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onNothingSelected() {

    }
}

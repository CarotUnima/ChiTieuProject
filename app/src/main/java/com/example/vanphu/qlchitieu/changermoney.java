package com.example.vanphu.qlchitieu;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class changermoney extends AppCompatActivity {
    ImageView imgnhom;
    Button btnquayve,btnchanger;
    TextInputEditText edittienvao;
    String email;
    ArrayList<String> arrayhinhanh;
    int idhinh=0;
//    link php
    String url="https://vanphudhsp2015.000webhostapp.com/changermoney.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_changermoney);
        init();
//      Previous page
        btnquayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//      Get email
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
//       Get Image
        imgnhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(changermoney.this,selectimagechitieu.class),100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK && data!=null){
//            declare string [] list
            final String[] mangten=getResources().getStringArray(R.array.list_hinh_chitieu);
            arrayhinhanh=new ArrayList<>(Arrays.asList(mangten));
//            get idhinh
            idhinh=Integer.parseInt(data.getStringExtra("idhinh"));
//            cover idhinh
            final int idHinh=getResources().getIdentifier(arrayhinhanh.get(idhinh),"drawable",getPackageName());
            imgnhom.setImageResource(idHinh);
//            event btnchanger
            btnchanger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Fill full info
                    if(edittienvao.getText().toString().trim().equals("")){
                        Toast.makeText(changermoney.this,"Nhập Đầy Đủ Thông Tin",Toast.LENGTH_LONG).show();
                    }else{
//                        function update money
                        CapNhatSV(url);
                        Intent intent=new Intent(changermoney.this,manhinhchinh.class);
                        intent.putExtra("email",email);
                        startActivity(intent);
                    }
                }
            });
        }
    }
    private void CapNhatSV(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Thành Công")){
                            Toast.makeText(changermoney.this,"Cập Nhật Thàng Công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(changermoney.this,"Cập Nhật THất Bại",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(changermoney.this,"Lỗi"+error,Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
//                post item data
                params.put("idhinh",String.valueOf(idhinh));
                params.put("tienvao",edittienvao.getText().toString().trim());
                params.put("email",email);
                return params;
            }
        } ;
        requestQueue.add(stringRequest);
    }
    public void init(){
        imgnhom=(ImageView) findViewById(R.id.imgnhom);
        btnquayve=(Button) findViewById(R.id.btnquayve);
        btnchanger=(Button) findViewById(R.id.btnchanger);
        edittienvao=(TextInputEditText) findViewById(R.id.edittienvao);
    }
}

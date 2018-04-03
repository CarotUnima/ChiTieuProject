package com.example.vanphu.qlchitieu;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class suachitieu extends AppCompatActivity {
    TextInputEditText edittenct,edittienct,editluuy;
    Button btnquayve;
    String email="";
    Button btnsua;
    int id=0;
    String url="https://vanphudhsp2015.000webhostapp.com/updatechitieu.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_suachitieu);
        init();
        final Intent intent=getIntent();
        email=intent.getStringExtra("email");
        edittenct.setText(intent.getStringExtra("tenchitieu"));
        edittienct.setText((intent.getStringExtra("giachitieu")));
        id=Integer.parseInt(intent.getStringExtra("id"));
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittenct.getText().toString().trim().equals("") && edittienct.getText().toString().trim().equals("")){
                    Toast.makeText(suachitieu.this,"Nhập Đầy Đủ Thông Tin",Toast.LENGTH_LONG).show();
                }else{
                    try{
                        int tien=Integer.parseInt(edittienct.getText().toString().trim());
                        CapNhatSV(url);
                        Intent intent1=new Intent(suachitieu.this,manhinhchinh.class);
                        intent1.putExtra("emailsua",email);
                        startActivity(intent1);
                    }catch (Exception e){
                        Toast.makeText(suachitieu.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnquayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void CapNhatSV(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Thành Công")){
                            Toast.makeText(suachitieu.this,"Cập Nhật Thàng Công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(suachitieu.this,"Cập Nhật THất Bại",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(suachitieu.this,"Lỗi"+error,Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("id",String.valueOf(id));
                params.put("tenchitieu",edittenct.getText().toString().trim());
                params.put("giachitieu",edittienct.getText().toString().trim());
                return params;
            }
        } ;
        requestQueue.add(stringRequest);
    }
    public void init(){
        btnquayve=(Button) findViewById(R.id.btnquayve);
        edittenct=(TextInputEditText) findViewById(R.id.edittenct);
        edittienct=(TextInputEditText) findViewById(R.id.edittienct);
        editluuy=(TextInputEditText) findViewById(R.id.editluuy);
        btnsua=(Button) findViewById(R.id.btnsua);
    }
}

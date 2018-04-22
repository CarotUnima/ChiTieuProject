package com.example.vanphu.qlchitieu;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
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
    Button btnsua,btnxoa;
    int id=0;
    String url="https://vanphudhsp2015.000webhostapp.com/updatechitieu.php";
    String urldelte="https://vanphudhsp2015.000webhostapp.com/deletechitieu.php";
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
                        CapNhatSV(url);
                        dialogxoa("Đang Sửa");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent1=new Intent(suachitieu.this,manhinhchinh.class);
                                intent1.putExtra("email",email);
                                startActivity(intent1);
                            }
                        }, 3500);
                    }catch (Exception e){
                        Toast.makeText(suachitieu.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deletect(id);
                dialogxoa("Đang Xóa");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent1=new Intent(suachitieu.this,manhinhchinh.class);
                        intent1.putExtra("email",email);
                        startActivity(intent1);
                    }
                }, 3500);
            }
        });
        btnquayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void dialogxoa(String str){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cho);
        TextView txttext=(TextView) dialog.findViewById(R.id.txttext);
        txttext.setText(str);
        dialog.show();
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
    public void Deletect(final int id){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urldelte,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Thành Công")){
                            Toast.makeText(suachitieu.this,"Xóa Thành Công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(suachitieu.this,"Lỗi",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("id",String.valueOf(id));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void error(){
        Intent intent=new Intent(suachitieu.this,Error_layout.class);
        startActivity(intent);
    }
    public void init(){
        btnquayve=(Button) findViewById(R.id.btnquayve);
        edittenct=(TextInputEditText) findViewById(R.id.edittenct);
        edittienct=(TextInputEditText) findViewById(R.id.edittienct);
        editluuy=(TextInputEditText) findViewById(R.id.editluuy);
        btnsua=(Button) findViewById(R.id.btnsua);
        btnxoa=(Button) findViewById(R.id.btnxoa);
    }
}

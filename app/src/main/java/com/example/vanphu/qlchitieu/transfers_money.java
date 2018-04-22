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

public class transfers_money extends AppCompatActivity {
    Button btnquayve,btnsend;
    TextInputEditText editemailfisrt,editemailin,edittien;
    String email="";
    String url="https://vanphudhsp2015.000webhostapp.com/chuyentien.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_transfers_money);
        init();
        btnquayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        editemailfisrt.setText(email);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapNhatSV(url);
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
                            Toast.makeText(transfers_money.this,"Cập Nhật Thàng Công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(transfers_money.this,"Cập Nhật THất Bại",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(transfers_money.this,"Lỗi"+error,Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("TransferAmount",edittien.getText().toString().trim());
                params.put("emailfirst",editemailfisrt.getText().toString().trim());
                params.put("emaillast",editemailin.getText().toString().trim());
                return params;
            }
        } ;
        requestQueue.add(stringRequest);
    }
    public void init(){
        btnquayve=(Button) findViewById(R.id.btnquayve);
        btnsend=(Button) findViewById(R.id.btnsend);
        editemailfisrt=(TextInputEditText) findViewById(R.id.editemailfisrt);
        editemailin=(TextInputEditText) findViewById(R.id.editemailin);
        edittien=(TextInputEditText) findViewById(R.id.edittien);
    }
}

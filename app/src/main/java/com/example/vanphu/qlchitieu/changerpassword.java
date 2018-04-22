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

public class changerpassword extends AppCompatActivity {
    Button btnchanger,btnquayve;
    TextInputEditText editemail,editpassword,edticonfigpassword;
    String url="https://vanphudhsp2015.000webhostapp.com/updateuser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_changerpassword);
//        declare
        init();
//        Previous page
        btnquayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        next page changer
        btnchanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editpassword.getText().toString().trim().equals(edticonfigpassword.getText().toString().trim())){
//                    update data
                    CapNhatUser(url);
//                    dialog success
                    dialogxoa("Đang Đổi Mật Khẩu Trên Server");
//                    delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent1=new Intent(changerpassword.this,login.class);
                            startActivity(intent1);
                        }
                    }, 3500);
                }else{
                    Toast.makeText(changerpassword.this,"Mật khẩu nhập lại không trùng",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void CapNhatUser(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Thành Công")){
                            Toast.makeText(changerpassword.this,"Cập Nhật Thàng Công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(changerpassword.this,"Cập Nhật THất Bại",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(changerpassword.this,"Lỗi"+error,Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",editemail.getText().toString().trim());
                params.put("password",editpassword.getText().toString().trim());
                return params;
            }
        } ;
        requestQueue.add(stringRequest);
    }
//    declare dialog
    public void dialogxoa(String str){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cho);
        TextView txttext=(TextView) dialog.findViewById(R.id.txttext);
        txttext.setText(str);
        dialog.show();
    }
    public void init(){
        btnchanger=(Button) findViewById(R.id.btnchanger);
        btnquayve=(Button) findViewById(R.id.btnquayve);
        editemail=(TextInputEditText) findViewById(R.id.editemail);
        editpassword=(TextInputEditText) findViewById(R.id.editpassword);
        edticonfigpassword=(TextInputEditText) findViewById(R.id.edticonfigpassword);
    }
}

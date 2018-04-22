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
import android.widget.ImageView;
import android.widget.TextView;
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

public class themchitieu extends AppCompatActivity {
    ImageView imgnhom;
    TextInputEditText edittenct,edittienct,editluuy;
    Button btnthem;
    int idhinh=0;
    String tenhinhanh="";
    String email="";
    ArrayList<String> arrayList;
    String url="https://vanphudhsp2015.000webhostapp.com/insertchitieu.php";
    String thamsohien="an";
    Button btnquayve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_themchitieu);
        init();
        String[] mangten=getResources().getStringArray(R.array.list_hinh_chitieu);
        arrayList=new ArrayList<>(Arrays.asList(mangten));
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        imgnhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(themchitieu.this,selectimagechitieu.class),100);
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogxoa("Đang Thêm");
                if(edittenct.getText().toString().equals("") && edittienct.getText().toString().trim().equals("")){
                    Toast.makeText(themchitieu.this,"Điền Đầy Đủ Thông Tin",Toast.LENGTH_LONG).show();
                }else{
                    try{
                        int tien=Integer.parseInt(edittienct.getText().toString().trim());
                        ThemSinhVien(url);
                    }catch (Exception e){
                        Toast.makeText(themchitieu.this,"LỖi"+e,Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK && data!=null){
            idhinh=Integer.parseInt(data.getStringExtra("idhinh"));
            final int idHinh=getResources().getIdentifier(arrayList.get(idhinh),"drawable",getPackageName());
            imgnhom.setImageResource(idHinh);
            tenhinhanh=data.getStringExtra("tenchitieu");
            edittenct.setText(tenhinhanh);
        }
    }
    private void ThemSinhVien(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Thành Công")){
                            Toast.makeText(themchitieu.this,"Thêm Thành Công",Toast.LENGTH_LONG).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(themchitieu.this,manhinhchinh.class);
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                }
                            }, 3500);
                        }else {
                            Toast.makeText(themchitieu.this,"Lỗi Thêm",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(themchitieu.this,"Lỗi Xảy ra! "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("tenchitieu",edittenct.getText().toString().trim());
                params.put("giachitieu",edittienct.getText().toString().trim());
                params.put("idhinh",String.valueOf(idhinh));
                params.put("email",email);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void dialogxoa(String str){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cho);
        TextView txttext=(TextView) dialog.findViewById(R.id.txttext);
        txttext.setText(str);
        dialog.show();
    }
    public void init(){
        btnquayve=(Button) findViewById(R.id.btnquayve);
        imgnhom=(ImageView) findViewById(R.id.imgnhom);
        edittenct=(TextInputEditText) findViewById(R.id.edittenct);
        edittienct=(TextInputEditText) findViewById(R.id.edittienct);
        editluuy=(TextInputEditText) findViewById(R.id.editluuy);
        btnthem=(Button) findViewById(R.id.btnthem);
    }
}

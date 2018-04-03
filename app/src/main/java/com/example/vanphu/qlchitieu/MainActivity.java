package com.example.vanphu.qlchitieu;

import android.app.Dialog;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    ImageView imagedk;
    TextView txtemail,txtname,txtmatien;
    ArrayList<String> arrayhinhanh;
    TextInputEditText edittienvao;
    Button btnhoanthanh;
    int idhinh=0;
    String url="https://vanphudhsp2015.000webhostapp.com/insertuser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        init();
        xuathien();
        txtmatien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,selectmoney.class),100);
            }
        });
        btnhoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_cho);
                dialog.show();
                ThemDuLieu(url);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK && data!=null){
            String matien=data.getStringExtra("matien");
            txtmatien.setText(matien);

        }
    }
    private void ThemDuLieu(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Thành Công")){
                            Toast.makeText(MainActivity.this,"Thêm Thành Công",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(MainActivity.this,manhinhchinh.class);
                            intent.putExtra("emaillogin",txtemail.getText().toString().trim());
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this,"Lỗi Thêm",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Lỗi Xảy ra! "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",txtemail.getText().toString().trim());
                params.put("matien",txtmatien.getText().toString().trim());
                params.put("tienvao",edittienvao.getText().toString().trim());
                params.put("idhinh",String.valueOf(idhinh));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void xuathien(){
        Intent intent=getIntent();
        String[] mangten=getResources().getStringArray(R.array.list_hinh_login);
        arrayhinhanh=new ArrayList<>(Arrays.asList(mangten));
        idhinh=Integer.parseInt(intent.getStringExtra("idhinh"));
        String name=intent.getStringExtra("name");
        String email=intent.getStringExtra("email");
        final int idHinh=getResources().getIdentifier(arrayhinhanh.get(idhinh),"drawable",getPackageName());
        imagedk.setImageResource(idHinh);
        txtname.setText(name);
        txtemail.setText(email);
    }
    public void init(){
        imagedk=(ImageView) findViewById(R.id.imagedk);
        txtemail=(TextView) findViewById(R.id.txtemail);
        txtname=(TextView) findViewById(R.id.txtname);
        txtmatien=(TextView) findViewById(R.id.txtmatien);
        edittienvao=(TextInputEditText) findViewById(R.id.edittienvao);
        btnhoanthanh=(Button) findViewById(R.id.btnhoanthanh);
    }
}

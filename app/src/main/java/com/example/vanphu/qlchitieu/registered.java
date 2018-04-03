package com.example.vanphu.qlchitieu;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class registered extends AppCompatActivity {
    ImageView imgnhom;
    ArrayList<String> arrayhinhanh;
    TextInputEditText editusername,editpassword,editname;
    Button btndangky,btnquayve;
    private RequestQueue requestQueue;
    private static final String URL="https://vanphudhsp2015.000webhostapp.com/login.php";
    private StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registered);
        init();
        String[] mangten=getResources().getStringArray(R.array.list_hinh_login);
        arrayhinhanh=new ArrayList<>(Arrays.asList(mangten));

        imgnhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(registered.this,selectionimage.class),100);
            }
        });
        requestQueue= Volley.newRequestQueue(this);
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
            final int idhinh=Integer.parseInt(data.getStringExtra("idhinh"));
            final int idHinh=getResources().getIdentifier(arrayhinhanh.get(idhinh),"drawable",getPackageName());
            imgnhom.setImageResource(idHinh);
            btndangky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog=new Dialog(registered.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_cho);
                    dialog.show();
                    request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.names().get(0).equals("success")){
                                    startActivity(new Intent(registered.this,MainActivity.class));
                                    Toast.makeText(registered.this,"Đăng Ký Thành Công",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(registered.this,MainActivity.class);
                                    intent.putExtra("email",editusername.getText().toString().trim());
                                    intent.putExtra("name",editname.getText().toString().trim());
                                    intent.putExtra("idhinh",String.valueOf(idhinh));
                                    startActivity(intent);
                                }else {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Tài khoản đã được đăng ký hoặc không hợp lệ", Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException e){
                                dialog.dismiss();
                                Toast.makeText(registered.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Error"+error , Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("email",editusername.getText().toString());
                            hashMap.put("password",editpassword.getText().toString());
                            hashMap.put("name",editname.getText().toString().trim());
                            hashMap.put("idhinh",String.valueOf(idhinh));
                            return hashMap;
                        }
                    };
                    requestQueue.add(request);
                }
            });
        }
    }
    public void dialog(){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cho);
        dialog.show();
    }
    public void init(){
        imgnhom=(ImageView) findViewById(R.id.imgnhom);
        editusername=(TextInputEditText) findViewById(R.id.editusername);
        editpassword=(TextInputEditText) findViewById(R.id.editpassword);
        editname=(TextInputEditText) findViewById(R.id.editname);
        btndangky=(Button) findViewById(R.id.btndangky);
        btnquayve=(Button) findViewById(R.id.btnquayve);
    }
}

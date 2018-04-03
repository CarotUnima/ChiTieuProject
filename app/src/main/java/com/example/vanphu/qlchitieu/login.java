package com.example.vanphu.qlchitieu;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    CheckBox checkbox;
    TextInputEditText editusername,editpassword;
    Button btnlogin;
    private RequestQueue requestQueue;
    private static final String URL="https://vanphudhsp2015.000webhostapp.com/user_control.php";
    private StringRequest request;
    SharedPreferences sharedPreferences;
    TextView txtdk;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        init();
        sharedPreferences =getSharedPreferences("datalogin",MODE_PRIVATE);
        editusername.setText(sharedPreferences.getString("taikhoan",""));
        editpassword.setText(sharedPreferences.getString("matkhau",""));
        checkbox.setChecked(sharedPreferences.getBoolean("checked",false));
        requestQueue= Volley.newRequestQueue(this);
        txtdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,registered.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(login.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_cho);
                dialog.show();
                request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(login.this,"Đăng Nhập Thành Công",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(login.this,manhinhchinh.class);
                                intent.putExtra("emaillogin",editusername.getText().toString().trim());
                                startActivity(intent);

                                if(checkbox.isChecked()){
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString("taikhoan",editusername.getText().toString().trim());
                                    editor.putString("matkhau",editpassword.getText().toString().trim());
                                    editor.putBoolean("checked",true);
                                    editor.commit();
                                }else{
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.remove("taikhoan");
                                    editor.remove("matkhau");
                                    editor.remove("checked");
                                    editor.commit();
                                }
                            }else {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Đăng Nhập Không Thành Công", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            dialog.dismiss();
                           Toast.makeText(login.this,"Tài Khoản Đăng Nhập Không Thành Công",Toast.LENGTH_LONG).show();
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
                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
    public void dialog(){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cho);
        dialog.show();
    }
    public void init(){
        checkbox=(CheckBox) findViewById(R.id.checkbox);
        editusername=(TextInputEditText) findViewById(R.id.editusername);
        editpassword=(TextInputEditText) findViewById(R.id.editpassword);
        btnlogin=(Button) findViewById(R.id.btnlogin);
        txtdk=(TextView) findViewById(R.id.txtdk);
    }
}

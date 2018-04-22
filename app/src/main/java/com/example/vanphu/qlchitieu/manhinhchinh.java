package com.example.vanphu.qlchitieu;


import android.animation.TimeAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vanphu.qlchitieu.adapter.chitieuAdapter;
import com.example.vanphu.qlchitieu.model.chitieu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class manhinhchinh extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txttienvao,txtemail,txtname,txttienngay,txtienra,txttongtien,txtngay,txtthu,txtthang,txtnam;
    ImageView btnadd;
    ImageView imageView;
    String name,email;
    int idhinh=0;
    SharedPreferences sharedPreferences;
    ArrayList<String> arrayhinhanh,arrrthang;
    String url="https://vanphudhsp2015.000webhostapp.com/getdatatienvao.php?email=";
    String url1="https://vanphudhsp2015.000webhostapp.com/getuser.php?email=";
    String url2="https://vanphudhsp2015.000webhostapp.com/getchitieu.php?email=";
    String urlsum="https://vanphudhsp2015.000webhostapp.com/tinhtongchitieu.php?email=";
    String urlngay="https://vanphudhsp2015.000webhostapp.com/tinhngay.php?email=";
    String urldelte="https://vanphudhsp2015.000webhostapp.com/deletechitieu.php";
    ListView listct;
    ArrayList<chitieu> arrayList;
    chitieuAdapter adapter;
    public static String matien="";
    int tienra=0;
    int tientong=0;
    int tienvao=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        declare item on sidebar
        View v=navigationView.getHeaderView(0);
        txtemail=(TextView) v.findViewById(R.id.txtemail);
        txtname=(TextView) v.findViewById(R.id.txtname);
        imageView=(ImageView) v.findViewById(R.id.imageView);
        final Intent intent=getIntent();
//        String noidung=intent.getStringExtra("emaillogin");
//        String noidung1=intent.getStringExtra("email");
//        String noidung2=intent.getStringExtra("emaillogin");
//        String noidung3=intent.getStringExtra("emailthem");
//        String noidung4=intent.getStringExtra("emailsua");
//
//        if(noidung!=null){
//            email=noidung;
//        }else if(noidung1!=null){
//            email=noidung1;
//        }else if(noidung2!=null){
//            email=noidung2;
//        } else if (noidung3!=null) {
//            email=noidung3;
//        } else if(noidung4!=null){
//            email=noidung4;
//        }
//        else {
//            email = "vanphu123";
//        }
        String content=intent.getStringExtra("email");
        if(content!=null){
            email=content;
        }else{
            email="vanphu123@gmail.com";
        }
        init();
        try{
            ReadJson(url+""+email);
            dialogxoa("Đang tải dữ liệu");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ReadJson1(url1+""+email);
                    ReadJsonchitieu(url2+""+email);
                    ReadJsonchitieutong(urlsum+""+email);
                    ReadJsonngaythang(urlngay+""+email);
                }
            }, 2500);
        }catch (Exception e){
            Toast.makeText(manhinhchinh.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
        }
        String a=txttienvao.getText().toString().trim();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(manhinhchinh.this,themchitieu.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        listct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(manhinhchinh.this,suachitieu.class);
                chitieu ct=arrayList.get(i);
                intent.putExtra("email",email);
                intent.putExtra("id",String.valueOf(ct.getId()));
                intent.putExtra("tenchitieu",ct.getTenchitieu());
                intent.putExtra("giachitieu",String.valueOf(ct.getGiachitieu()));
                startActivity(intent);
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
                                txtname.setText(object.getString("name"));
                                txtemail.setText(object.getString("email"));
                                idhinh=object.getInt("idhinh");
                                String[] mangten=getResources().getStringArray(R.array.list_hinh_login);
                                arrayhinhanh=new ArrayList<>(Arrays.asList(mangten));
                                final int idHinh=getResources().getIdentifier(arrayhinhanh.get(idhinh),"drawable",getPackageName());
                                imageView.setImageResource(idHinh);
                            }catch (Exception e){
                                Toast.makeText(manhinhchinh.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
                            }
                        }
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
    public void ReadJson(String url){

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        svlist.clear();
                        for(int i=0;i<response.length();i++){
                            try{
                                JSONObject object=response.getJSONObject(i);
                                DecimalFormat formatter = new DecimalFormat("###,###,###");
                                tienvao=object.getInt("tienvao");
                                matien =object.getString("matien");
                                String tong1=formatter.format(tienvao)+" "+ matien;
                                txttienvao.setText(tong1);
                                tientong=object.getInt("tienvao");

                            }catch (Exception e){
                                Toast.makeText(manhinhchinh.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
                            }
                        }
//                        adapter.notifyDataSetChanged();

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
    public void ReadJsonngaythang(String url){

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        svlist.clear();
                        for(int i=0;i<response.length();i++){
                            try{
                                JSONObject object=response.getJSONObject(i);
                                txtngay=(TextView) findViewById(R.id.txtngay);
                                txtthu=(TextView) findViewById(R.id.txtthu);
                                txtngay.setText(object.getString("ngay"));
                                int numberngay=object.getInt("ngay");
                                int numberthang=object.getInt("thang");
                                int numbernam=object.getInt("nam");
                                if(numberthang<3){
                                    numberthang=numberthang+12;
                                    numbernam=numbernam-1;
                                }
                                int n= (numberngay+2*numberthang+(3*(numberthang+1)) / 5 + numbernam + (numbernam / 4)) % 7;
                                String thu="";
                                if(n==0){
                                    thu="Chủ Nhật";
                                }else if(n==1){
                                    thu="Thứ Hai";
                                }else  if(n==2){
                                    thu="Thứ Ba";
                                }else if(n==3){
                                    thu="Thứ Tư";
                                }
                                else if(n==4){
                                    thu="Thứ Năm";
                                }
                                else if(n==5){
                                    thu="Thứ Sáu";
                                }
                                else if(n==6){
                                    thu="Thứ Bảy";
                                }
                                txtthu.setText(thu);
                                String[] mangten=getResources().getStringArray(R.array.thang);
                                arrrthang=new ArrayList<>(Arrays.asList(mangten));
                                txtthang=(TextView) findViewById(R.id.txtthang);
                                txtthang.setText(arrrthang.get(object.getInt("thang")));
                                txtnam=(TextView) findViewById(R.id.txtnam);
                                txtnam.setText(String.valueOf(object.getInt("nam")));
                            }catch (Exception e){
                                Toast.makeText(manhinhchinh.this,"Lỗi123"+e,Toast.LENGTH_LONG).show();
                            }
                        }
//                        adapter.notifyDataSetChanged();

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
    public void ReadJsonchitieu(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayList.clear();
                        for(int i=0;i<response.length();i++){
                            try{
                                JSONObject object=response.getJSONObject(i);
                                String[] mangten=getResources().getStringArray(R.array.list_hinh_chitieu);
                                arrayhinhanh=new ArrayList<>(Arrays.asList(mangten));
                                final int idHinh=getResources().getIdentifier(arrayhinhanh.get(object.getInt("idhinh")),"drawable",getPackageName());
                                arrayList.add(new chitieu(object.getInt("id"),object.getString("tenchitieu")
                                ,object.getInt("giachitieu"),idHinh,object.getString("ngay")
                                        ,object.getString("email")
                                ));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();

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
    public void ReadJsonchitieutong(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i=0;i<response.length();i++){
                            try{
                                txttienngay=(TextView) findViewById(R.id.txttienngay);
                                txtienra=(TextView) findViewById(R.id.txtienra);
                                txttongtien=(TextView) findViewById(R.id.txttongtien);
                                JSONObject object=response.getJSONObject(i);
                                tienra=object.getInt("sum1");
                                DecimalFormat formatter = new DecimalFormat("###,###,###");
                                String tong1=formatter.format(tienra)+" "+ matien;
                                txttienngay.setText(tong1);
                                txtienra.setText(tong1);
                                int tongtien=tienvao-tienra;
                                String tong2=formatter.format(tongtien)+" "+ matien;
                                txttongtien.setText(tong2);
                            }catch (Exception e){
                                Toast.makeText(manhinhchinh.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
                            }
                        }
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
        Intent intent=new Intent(manhinhchinh.this,Error_layout.class);
        startActivity(intent);
    }
    public void dialogxoa(String str){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cho);
        TextView txttext=(TextView) dialog.findViewById(R.id.txttext);
        txttext.setText(str);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 4000);
    }
    public void init(){
        btnadd=(ImageView) findViewById(R.id.btnadd);
        txttienvao=(TextView) findViewById(R.id.txttienvao);
        listct=(ListView) findViewById(R.id.listct);
        arrayList=new ArrayList<>();
        adapter=new chitieuAdapter(manhinhchinh.this,R.layout.dong_chi_tieu,arrayList);
        arrayList.add(new chitieu(1,"vanphu",121,R.drawable.hinh1,"2018-2-2","vanphu"));
        listct.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manhinhchinh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final Dialog dialog=new Dialog(manhinhchinh.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_cho);
            TextView txttext=(TextView) dialog.findViewById(R.id.txttext);
            txttext.setText("Đang đăng xuất");
            dialog.show();
            sharedPreferences =getSharedPreferences("datalogin",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.remove("taikhoan");
            editor.remove("matkhau");
            editor.remove("checked");
            editor.commit();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(manhinhchinh.this,login.class));
                }
            }, 3500);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_thongke) {
            Intent intent=new Intent(manhinhchinh.this,thongke.class);
            intent.putExtra("email",email);
            intent.putExtra("matien",matien);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.changermoney) {
            Intent intent=new Intent(manhinhchinh.this,changermoney.class);
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.chuyentien) {
            Intent intent=new Intent(manhinhchinh.this,transfers_money.class);
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

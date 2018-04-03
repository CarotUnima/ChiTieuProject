package com.example.vanphu.qlchitieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.vanphu.qlchitieu.adapter.hinhanhAdapter;
import com.example.vanphu.qlchitieu.model.hinhanh;

import java.util.ArrayList;
import java.util.Arrays;

public class selectionimage extends AppCompatActivity {
    GridView grivadd;
    ArrayList<hinhanh> arrayList;
    hinhanhAdapter adapter;
    ArrayList<String> arrayhinhanh;
    ArrayList<String> arrayten;
    Button btnquayve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_selectionimage);
        init();
        grivadd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                hinhanh ha=arrayList.get(i);
                int hinhanh=i;
                Intent intent=getIntent();
                intent.putExtra("idhinh",String.valueOf(hinhanh));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        btnquayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void init(){
        btnquayve=(Button) findViewById(R.id.btnquayve);
        String[] mangten=getResources().getStringArray(R.array.list_hinh_login);
        arrayhinhanh=new ArrayList<>(Arrays.asList(mangten));
        String[] mangten1=getResources().getStringArray(R.array.name_img);
        arrayten=new ArrayList<>(Arrays.asList(mangten1));
        grivadd=(GridView) findViewById(R.id.grivadd);
        arrayList=new ArrayList<>();
        adapter=new hinhanhAdapter(selectionimage.this,R.layout.dong_hinh_anh,arrayList);
        try{
            for(int i=0;i<20;i++){
                int idHinh=getResources().getIdentifier(arrayhinhanh.get(i),"drawable",getPackageName());
                String ten=arrayten.get(i);
                arrayList.add(new hinhanh(idHinh,ten));
            }
        }catch (Exception e){
            Toast.makeText(selectionimage.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
        }

        grivadd.setAdapter(adapter);

    }
}

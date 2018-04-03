package com.example.vanphu.qlchitieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.vanphu.qlchitieu.adapter.tienteAdapter;
import com.example.vanphu.qlchitieu.model.hinhanh;
import com.example.vanphu.qlchitieu.model.tiente;

import java.util.ArrayList;
import java.util.Arrays;

public class selectmoney extends AppCompatActivity {
    GridView grivadd;
    ArrayList<tiente> arrayList;
    tienteAdapter adapter;
    ArrayList<String> arrayhinhanh;
    ArrayList<String> arrayhinhanhname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_selectmoney);
        init();
        grivadd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tiente tt=arrayList.get(i);
                Intent intent=getIntent();
                intent.putExtra("matien",tt.getMatien());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    public void init(){
        grivadd=(GridView) findViewById(R.id.grivadd);
        String[] mangten=getResources().getStringArray(R.array.list_flag);
        arrayhinhanh=new ArrayList<>(Arrays.asList(mangten));
        String[] mangten1=getResources().getStringArray(R.array.list_flag_name);
        arrayhinhanhname=new ArrayList<>(Arrays.asList(mangten1));
        arrayList=new ArrayList<>();
        adapter=new tienteAdapter(selectmoney.this,R.layout.dong_hinh_anh,arrayList);
        for(int i=0;i<8;i++){
            int idHinh=getResources().getIdentifier(arrayhinhanh.get(i),"drawable",getPackageName());
            String ten=arrayhinhanhname.get(i);
            arrayList.add(new tiente(idHinh,ten));
        }
        grivadd.setAdapter(adapter);
    }
}

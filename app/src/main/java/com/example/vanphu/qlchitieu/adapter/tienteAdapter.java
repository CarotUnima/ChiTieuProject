package com.example.vanphu.qlchitieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanphu.qlchitieu.R;
import com.example.vanphu.qlchitieu.model.hinhanh;
import com.example.vanphu.qlchitieu.model.tiente;

import java.util.List;

/**
 * Created by VanPhu on 3/25/2018.
 */

public class tienteAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<tiente> hinhanhlist;

    public tienteAdapter(Context context, int layout, List<tiente> hinhanhlist) {
        this.context = context;
        this.layout = layout;
        this.hinhanhlist = hinhanhlist;
    }

    @Override
    public int getCount() {
        return hinhanhlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHoler{
        ImageView imghinhanh;
        TextView txtten;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       tienteAdapter.ViewHoler holder;
        if(view==null){
            holder=new tienteAdapter.ViewHoler();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.imghinhanh=(ImageView) view.findViewById(R.id.imghinhanh);
            holder.txtten=(TextView) view.findViewById(R.id.txtten);
            view.setTag(holder);
        }else{
            holder=(tienteAdapter.ViewHoler) view.getTag();
        }
        tiente h=hinhanhlist.get(i);
        holder.imghinhanh.setImageResource(h.getIdhinhtien());
        holder.txtten.setText(h.getMatien());
        return view;
    }
}

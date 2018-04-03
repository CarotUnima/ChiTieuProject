package com.example.vanphu.qlchitieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanphu.qlchitieu.R;
import com.example.vanphu.qlchitieu.manhinhchinh;
import com.example.vanphu.qlchitieu.model.chitieu;
import com.example.vanphu.qlchitieu.model.hinhanh;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by VanPhu on 3/26/2018.
 */

public class chitieuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<chitieu> chitieulist;

    public chitieuAdapter(Context context, int layout, List<chitieu> chitieulist) {
        this.context = context;
        this.layout = layout;
        this.chitieulist = chitieulist;
    }

    @Override
    public int getCount() {
        return chitieulist.size();
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
        ImageView img_ct;
        TextView txt_tenct,txt_giact;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        chitieuAdapter.ViewHoler holder;
        if(view==null){
            holder=new chitieuAdapter.ViewHoler();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.img_ct=(ImageView) view.findViewById(R.id.img_ct);
            holder.txt_tenct=(TextView) view.findViewById(R.id.txt_tenct);
            holder.txt_giact=(TextView) view.findViewById(R.id.txt_giact);
            view.setTag(holder);
        }else {
            holder=(chitieuAdapter.ViewHoler) view.getTag();
        }
        final chitieu ct=chitieulist.get(i);
        holder.txt_tenct.setText(ct.getTenchitieu());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String tong1=formatter.format(ct.getGiachitieu())+" "+ manhinhchinh.matien;
        holder.txt_giact.setText(tong1);
        holder.img_ct.setImageResource(ct.getIdhinh());
        return view;
    }
}

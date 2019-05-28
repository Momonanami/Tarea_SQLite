package com.example.a19134355_7.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter
{
    private Context context;
    private List<car> list;
    private int layout;

    public MyAdapter(Context context,List<car> list,int layout)
    {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount ()
    {
        return list.size();
    }
    @Override
    public car getItem(int position)
    {
        return list.get(position);
    }
    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        ViewHolder vh;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh = new ViewHolder();
            vh.id = convertView.findViewById(R.id.textViewID);
            vh.nombre= convertView.findViewById(R.id.textViewNombre);
            vh.color = convertView.findViewById(R.id.textViewColor);
            convertView.setTag(vh);
        }
        else
        {
            vh = (ViewHolder) convertView.getTag();
        }
        car currentCar= list.get(position);
        vh.id.setText(currentCar.getID()+"");
        vh.nombre.setText(""+currentCar.getNombre());
        vh.color.setText(" "+currentCar.getColor());
        return convertView;
    }

    public class ViewHolder
    {
        TextView id;
        TextView nombre;
        TextView color;
    }

}

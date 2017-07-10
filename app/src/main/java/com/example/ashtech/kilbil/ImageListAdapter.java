package com.example.ashtech.kilbil;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ashtech.kilbil.model.ImageUpload;

import java.util.List;

/**
 * Created by Ashtech on 6/17/2017.
 */

public class ImageListAdapter extends ArrayAdapter<ImageUpload>{


    private Activity context;
    private int resource;
    private List<ImageUpload> list;


    public ImageListAdapter(@NonNull Activity context,@LayoutRes int resource,@NonNull List<ImageUpload> objects) {
        super(context,resource,objects);

        this.context=context;
        this.resource=resource;
        list=objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View v=inflater.inflate(resource,null);
        ImageView img= (ImageView) v.findViewById(R.id.imgView);
        TextView tv = (TextView) v.findViewById(R.id.img_item_desc);
        if(list.get(position).getDesc()!=null && list.get(position).getDesc().length()>0 ) {
            tv.setText(list.get(position).getDesc());
        }else{
            tv.setVisibility(View.GONE);
        }
        Glide.with(context).load(list.get(position).getUrl()).into(img);
        return  v;
    }
}

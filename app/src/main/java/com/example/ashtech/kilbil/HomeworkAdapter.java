package com.example.ashtech.kilbil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ashtech on 6/14/2017.
 */
public class HomeworkAdapter extends BaseAdapter {

    private List<Homework> homeworkList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public HomeworkAdapter(List<Homework>homeworkList,Context mContext){
        this.homeworkList=homeworkList;
        this.mContext=mContext;
    }

    public void setHomeworkList(List<Homework>homeworkList){
        this.homeworkList=homeworkList;
    }
    @Override
    public int getCount() {
        return homeworkList.size();
    }

    @Override
    public Object getItem(int i) {
        return homeworkList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        ViewHolder viewHolder = null;
        if(v==null){
            layoutInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.homework_item, viewGroup, false);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();

        }
        viewHolder.txthwdesc.setText(homeworkList.get(i).getHwDesc());
        viewHolder.txtdate.setText(homeworkList.get(i).getDate());
        return v;
        }
    private class ViewHolder {

        TextView txtsection;
        TextView txthwdesc;
        TextView txtdate;


        public ViewHolder(View v) {
            txthwdesc = (TextView) v.findViewById(R.id.hwdesc);
            txtdate= (TextView) v.findViewById(R.id.date);

        }
    }
}

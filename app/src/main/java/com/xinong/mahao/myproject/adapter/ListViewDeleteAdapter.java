package com.xinong.mahao.myproject.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xinong.mahao.myproject.R;

import java.util.ArrayList;

/**
 * Created by mahao on 16/6/18.
 */
public class ListViewDeleteAdapter  extends BaseAdapter{

    private Activity mActivity;
    private ArrayList<String> mStringArrayList;

    public ListViewDeleteAdapter(Activity activity,ArrayList<String> mStringArrayList){

        this.mActivity = activity;
        this.mStringArrayList = mStringArrayList;
    }

    @Override
    public int getCount() {

        int len =0;
        if(mStringArrayList != null){

            len = mStringArrayList.size();
        }
        return len;
    }

    @Override
    public Object getItem(int position) {

        return mStringArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Viewholder viewholder;
        if(convertView == null){

            convertView = LayoutInflater.from(mActivity).inflate(R.layout.listview_deltete_btn_item,parent,false);
            viewholder = new Viewholder(convertView);
            convertView.setTag(viewholder);

        }else{

            viewholder = (Viewholder) convertView.getTag();
        }
        String txtString = mStringArrayList.get(position);
        viewholder.mImageView.setBackgroundResource(R.mipmap.ic_launcher);
        viewholder.mTextView.setText(txtString);
        return  convertView;
    }


    class Viewholder {

        private ImageView mImageView;
        private TextView mTextView;

        public Viewholder(View view){

            mImageView = (ImageView) view.findViewById(R.id.item_imageview);
            mTextView = (TextView) view.findViewById(R.id.item_textView);

        }
    }
}






package com.xinong.mahao.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xinong.mahao.myproject.adapter.ListViewDeleteAdapter;
import com.xinong.mahao.myproject.customview.CustomView;
import com.xinong.mahao.myproject.customview.ListViewDelete;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ListViewDelete mListView;
    private CustomView mCustomView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomView = (CustomView) this.findViewById(R.id.customText);
       // mCustomView.setOnClickListener(this);


    }

   @Override
    public void onClick(View v) {


        int id = v.getId();
        switch(id){

            case R.id.customText:

                mCustomView.setOnclickListenerMy(new CustomView.ClickListbtn() {
                    @Override
                    public String changeText(String text) {

                        Random random = new Random();
                        text = "5678"+random.nextInt(100);

                        return text;
                    }

                });
                break;

        }
       mCustomView.invalidate();
    }
}

package com.xinong.mahao.myproject.customview;

import android.content.Context;
import android.graphics.Color;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

/**
 * Created by mahao on 16/6/18.
 */
public class ListViewDelete extends ListView {


    private boolean mIsSliding;
    private static int mScaledTouchSlop;
    private View mCurrentView;

    public ListViewDelete(Context context) {
        this(context,null);
    }

    public ListViewDelete(Context context, AttributeSet attrs) {

        this(context,attrs,0);
    }

    public ListViewDelete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context,attrs,defStyleAttr);
    }

     public static void initData(Context context,AttributeSet attrs, int defStyleAttr){

         mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

     }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        mIsSliding = false;

        float downX = 0,downY= 0;
        int currentPosition;

        switch(action){

            case MotionEvent.ACTION_DOWN:

                downX = getX();
                downY = getY();
                currentPosition = pointToPosition((int)downX,(int)downY);
                View view = getChildAt(currentPosition-getFirstVisiblePosition());
                mCurrentView = view;
                break;

            case MotionEvent.ACTION_MOVE:

                float moveX = getX();
                float  moveY = getY();

                float dx = downX - moveX;
                float dy = downY - moveY;

                if(moveX < downX && Math.abs(dx) > mScaledTouchSlop && Math.abs(dy) < mScaledTouchSlop){

                    mIsSliding = true;
                    mCurrentView.setBackgroundColor(Color.parseColor("#f00"));
                }
                break;

            case MotionEvent.ACTION_UP:
                break;

        }

        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        if(mIsSliding){

            switch (action){

                case MotionEvent.ACTION_MOVE:

                    int[] location = new int[2];
                    mCurrentView.getLocationOnScreen(location);
                    mCurrentView.setBackgroundColor(Color.parseColor("#f00"));
                    break;

            }
        }

        return super.onTouchEvent(ev);
    }
}











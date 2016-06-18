package com.xinong.mahao.myproject.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.service.voice.VoiceInteractionService;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.xinong.mahao.myproject.R;

import java.net.InterfaceAddress;
import java.util.Random;

/**
 * Created by mahao on 16/6/18.
 */
public class CustomView extends View {


    private Paint mPaint;
    private String mTextContent;
    private Rect mBound;
    private float mTextSize;
    public  ClickListbtn mClickListbtn;


    public void setOnclickListenerMy(ClickListbtn  clickListbtn){

        this.mClickListbtn = clickListbtn;
    }

    public CustomView(Context context) {
       this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {

        this(context,attrs,0);
    }


    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context,attrs,defStyleAttr);
    }

    //获取自定义属性
    private void initData(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewText,defStyleAttr,0);
        mTextContent = typeArray.getString(R.styleable.CustomViewText_titleText);
        float  textColor  = typeArray.getColor(R.styleable.CustomViewText_titleTextColors, Color.RED);
        mTextSize = typeArray.getDimension(R.styleable.CustomViewText_titleTextSize,20);

        typeArray.recycle();

        mPaint = new Paint();
        mPaint.setColor((int) textColor);
        mPaint.setTextSize(mTextSize);

        mBound = new Rect();
        mPaint.getTextBounds(mTextContent,0, mTextContent.length(), mBound);

        //可以获取文字的宽高了;
        float txtWidth = mBound.width()+ mBound.left;
        float txtHeight = mBound.height() + mBound.bottom;


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
                int aa = random.nextInt(1000);
                mTextContent = String.valueOf(aa);
                invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if(widthMode == MeasureSpec.EXACTLY){

            width = widthSize;

        }else{

            mPaint.setTextSize(mTextSize);
            mBound = new Rect();
            mPaint.getTextBounds(mTextContent,0,mTextContent.length(),mBound);

            float textWidth = mBound.width();
            int desiredWidth = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desiredWidth;
        }


        if(heightMode == MeasureSpec.EXACTLY){

           height = heightSize;
        }else{

            mPaint.setTextSize(mTextSize);
            mBound = new Rect();
            mPaint.getTextBounds(mTextContent,0,mTextContent.length(),mBound);

            float textHeight = mBound.width();
            int desirdHeight = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desirdHeight;
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       mPaint.setColor(Color.BLUE);
       canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(Color.RED);

        if(mClickListbtn != null){

            String changeText  = mClickListbtn.changeText("5678");
            Toast.makeText(getContext(),"???",Toast.LENGTH_SHORT).show();
            canvas.drawText(changeText,getWidth()/2-mBound.width()/2,getHeight()/2-mBound.height()/2,mPaint);

        }else {

            canvas.drawText(mTextContent,getWidth()/2-mBound.width()/2,getHeight()/2-mBound.height()/2,mPaint);
        }


    }


     public interface  ClickListbtn{

         String  changeText(String Text);
     }

}









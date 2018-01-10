package com.ebsig.flygo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ebsig.flygo.util.Dp_Px_Changer;
import com.ebsig.zhengda.R;


/**
 * 优惠券控件
 */
public class CouponView extends LinearLayout {

    private Paint mPaint;
    private Paint mCirclePaint;
    //边缘小半圆的半径
    private float radius = 20;
    //小半圆之间的间距  
    private float spacing = 20;
    //左右边距  
    private float paddingLeft;
    private float paddingRight;
    //半圆的个数  
    private int numCircle;
    //控件的高宽度  
    private int height;
    private int width;

    private float remain;
    private int mDp1;
    private RectF mRectF;

    public CouponView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//      initView(context);  
    }

    public CouponView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CouponView(Context context) {
        super(context);
//      initView(context);  
    }

    private void initView(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(context.getResources().getColor(R.color.light_grey2));
        mDp1 = Dp_Px_Changer.dipTopx(context, 0.5f);
        mPaint.setStrokeWidth(mDp1);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CouponView);
        float radius = ta.getDimension(R.styleable.CouponView_radius, 20);
        float spacing = ta.getDimension(R.styleable.CouponView_spacing, 20);
        setRadius(radius);
        setSpacing(spacing);

        ta.recycle();
        mRectF = new RectF();
        paddingLeft = paddingRight = spacing;
    }


    private void setSpacing(float spacing) {
        this.spacing = spacing;
    }

    private void setRadius(float radius) {
        this.radius = radius;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("这里");
        // getWidth在OnCreat的时候得到的是0. 当一个view对象创建时，android并不知道其大小，所以getWidth()和   getHeight()返回的结果是0，真正大小是在计算布局时才会计算.
        width = this.getWidth();
        height = this.getHeight();
        //圆的数量始终比边距数量少一个，所以总长度减去左边距除以2*radius+spacing的值就是圆的数量  
        numCircle = (int) ((width - paddingLeft) / (2 * radius + spacing));
        //除以所有圆和边距的所余下的长度  
        remain = ((width - paddingLeft) % (2 * radius + spacing));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        numCircle = (int) ((w - paddingLeft) / (2 * radius + spacing));
        //除以所有圆和边距的所余下的长度
        remain = ((w - paddingLeft) % (2 * radius + spacing));
        width = w;
        height = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //remain/2保证左右两边边距一样  
        float cx = (paddingLeft + radius + remain / 2);
        for (int i = 0; i < numCircle; i++) {
            // canvas.drawCircle(cx, 0, radius, mPaint);
            mRectF.left = cx - radius;
            mRectF.top = height - radius;
            mRectF.right = cx + radius;
            mRectF.bottom = height + radius;
            canvas.drawArc(mRectF, 180, 180, false, mPaint);
            canvas.drawCircle(cx, height, radius - mDp1, mCirclePaint);
            cx = (int) (cx + (2 * radius + spacing));
        }
    }
}  
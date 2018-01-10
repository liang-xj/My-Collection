package com.ebsig.flygo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.ebsig.flygo.util.Dp_Px_Changer;
import com.ebsig.zhengda.R;


public class CircleShadow extends RelativeLayout {

    private static final String DEFAULT_SHADOW_COLOR = "#7F7F7F";

    private static final String DEFAULT_SHADOW_AROUND_COLOR = "#6a6a6a";
    private int mDp5;

    private Paint paint;

    private float radius;
    private float xCenter;
    private float yCenter;

    private float shadowRadius = 12.0f;
    private float shadowXDelta = 2.0f;
    private float shadowYDelta = 2.0f;

    private boolean canDraw = true;

    private String initialColor = DEFAULT_SHADOW_COLOR;
    private String initialRoundColor = DEFAULT_SHADOW_AROUND_COLOR;

    private int mDp20;
    private Context mContext;
    private float mRadius;

    /*
     *
	 */
    public CircleShadow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    /*
     *
	 */
    public CircleShadow(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDp20 = Dp_Px_Changer.dipTopx(context, 20);
        mDp5 = Dp_Px_Changer.dipTopx(context, 5);
        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleShadow);
        int color = ta.getColor(R.styleable.CircleShadow_circleBackground, Color.WHITE);
        int ShadowColor = ta.getColor(R.styleable.CircleShadow_shadowColor, context.getResources().getColor(R.color.light_grey2));
        setShadowValus(10, 2, 2, mDp20);
        radius = ta.getDimension(R.styleable.CircleShadow_circleRadius, 0);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);
        paint.setShadowLayer(shadowRadius, shadowXDelta, shadowYDelta,
                ShadowColor);
        ta.recycle();
        setWillNotDraw(false);
    }

    /*
     *
	 */
    public CircleShadow(Context context) {
        super(context);
    }

    private void init() {

    }

    /*
     * 
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        xCenter = getWidth() / 2;
        yCenter = getHeight() / 2;
        if (radius == 0) {
            radius = xCenter - mDp5;
        }
    }


    public void setShadowValus(float r, float dx, float dy, float radius) {
        shadowRadius = r;
        shadowXDelta = dx;
        shadowYDelta = dy;
        this.radius = radius;
    }


    /*
     *
	 */
    public void setDraw(boolean l) {
        canDraw = l;
        postInvalidate();
    }

    /*
     *
	 */
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (canDraw) {
            canvas.drawCircle(xCenter, yCenter, radius, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return super.onTouchEvent(event);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);

    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {
            radius += 4;
            postInvalidate();
        } else {
            radius -= 4;
            postInvalidate();
        }
    }
}
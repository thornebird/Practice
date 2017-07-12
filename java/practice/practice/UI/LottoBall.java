
package practice.practice.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import practice.practice.R;


public class LottoBall extends View {

    private boolean isBonus;
    private int defaultColor = Color.parseColor("#00BFFF");
    private int bonusColor=Color.parseColor("#66ef66");
    private int circleColor;
    private int textColor = Color.BLACK;
    private String number = "blank";

    private Paint circlePaint;
    private Paint textPaint;

    private float mCenterX;
    private float mCenterY;
    private float mRadius;


    public LottoBall(Context context,boolean isBonus) {
        super(context, null);
        this.isBonus=isBonus;
        init();
    }

    public LottoBall(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray tp = context.obtainStyledAttributes(R.styleable.LottoBall);
        circleColor = tp.getColor(R.styleable.LottoBall_circleColor, defaultColor);
        number = tp.getString(R.styleable.LottoBall_circleLabel);
        textColor = tp.getColor(R.styleable.LottoBall_labelColor, textColor);
        isBonus = tp.getBoolean(R.styleable.LottoBall_isBonus,false);
        tp.recycle();
        init();
    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldh, oldw);
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        mRadius = Math.min(w, h) / 2f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
            width = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.AT_MOST) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            height = width;
        }

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = height;
        }

        setMeasuredDimension(widthSize, heightSize);
    }

    private void init() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (isBonus)
            defaultColor=bonusColor;
        circlePaint.setColor(defaultColor);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        textPaint.setColor(textColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int width = getWidth();
        final int height = getHeight();
        final float centerX = width / 5f;
        final float centerY = height / 5f;
        final float outerRadius = Math.min(centerX, centerY) / 0.5F;
        canvas.drawCircle(mCenterX, mCenterY, outerRadius, circlePaint);


        textPaint.setTextSize(outerRadius);
        textPaint.setTextAlign(Paint.Align.CENTER);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
        canvas.drawText(number, xPos, yPos, textPaint);
    }

    public void setText(String text) {
        number = text;
        invalidate();
    }

    public void setBonusColor(boolean isBonus){
//        defaultColor=
        this.isBonus=isBonus;
        init();
        invalidate();
    }

}


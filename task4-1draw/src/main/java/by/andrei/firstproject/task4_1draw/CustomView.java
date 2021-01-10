package by.andrei.firstproject.task4_1draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Random;

public class CustomView extends View {

    interface OnCustomViewActionListener {
        void onActionDown(float x, float y, int color);
    }

    private int diameter;
    private boolean defaultStateFlag = true;
    private int centerX;
    private int centerY;
    private int radius1;
    private int radius2;

    private final Region[] sector = {new Region(), new Region(), new Region(), new Region(), new Region()};
    private final Paint[] paint = {new Paint(), new Paint(), new Paint(), new Paint(), new Paint()};

    private final RectF circle = new RectF();
    private OnCustomViewActionListener onCustomViewActionListener;
    private final Random rand = new Random();

    private int[] colorNewFill = getResources().getIntArray(R.array.android_colors);
    public int gerRandColor() {
        return colorNewFill[rand.nextInt(colorNewFill.length)];
    }

    public CustomView(Context context) {
        super(context);
    }
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        try {
            TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.CustomView);
            diameter = (int) typedArray.getDimension(R.styleable.CustomView_circleDefaultDiameter, .0f);
            typedArray.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        centerX = viewWidth / 2;
        centerY = viewHeight / 2;
        calculateCords();
    }

    public void calculateCords() {
        radius1 = diameter / 2;
        radius2 = diameter / 6;
        circle.set(centerX - radius1, centerY - radius1, centerX + radius1, centerY + radius1);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        sector[0] = new Region(centerX, centerY, centerX + radius1, centerY + radius1);
        sector[1] = new Region(centerX - radius1, centerY, centerX, centerY + radius1);
        sector[2] = new Region(centerX - radius1, centerY - radius1, centerX, centerY);
        sector[3] = new Region(centerX, centerY - radius1, centerX + radius1, centerY);
        sector[4] = new Region(centerX - radius2, centerY - radius2, centerX + radius2, centerY + radius2);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (defaultStateFlag) {
            paint[0].setColor(Color.YELLOW);
            paint[1].setColor(Color.BLUE);
            paint[2].setColor(Color.RED);
            paint[3].setColor(Color.GREEN);
            paint[4].setColor(Color.CYAN);
            defaultStateFlag = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(circle, 0, 90, true, paint[0]);
        canvas.drawArc(circle, 90, 90, true, paint[1]);
        canvas.drawArc(circle, 180, 90, true, paint[2]);
        canvas.drawArc(circle, 0, -90, true, paint[3]);
        canvas.drawCircle(centerX, centerY, radius2, paint[4]);
        super.onDraw(canvas);
    }

    public void setOnCustomViewActionListener(OnCustomViewActionListener onCustomViewActionListener) {
        this.onCustomViewActionListener = onCustomViewActionListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        float x = event.getX();
        float y = event.getY();
        int sectorLength = sector.length;
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (onCustomViewActionListener != null) {

                for (int i = 0; i < sectorLength; i++) {
                    if (sector[i].contains((int) x, (int) y)) {
                        if (i < 4) {
                            paint[i].setColor(gerRandColor());
                            onCustomViewActionListener.onActionDown(x, y, paint[i].getColor());
                        } else {
                            for (int j = 0; j < sectorLength; j++) {
                                paint[j].setColor(gerRandColor());
                                onCustomViewActionListener.onActionDown(x, y, paint[i].getColor());
                            }
                        }
                    }
                }
            }
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}

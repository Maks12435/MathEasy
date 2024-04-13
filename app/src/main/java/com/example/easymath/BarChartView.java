package com.example.easymath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BarChartView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] data;
    private String[] labels;

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(float[] data, String[] labels) {
        this.data = data;
        this.labels = labels;
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        float columnWidth = width / (float) data.length;
        float maxDataValue = 10;
        float columnMaxHeight = height * 0.9f;

        paint.setColor(Color.rgb(238, 0, 85));
        paint.setStrokeWidth(columnWidth * 0.6f);
        paint.setTextSize(30);

        for (int i = 0; i < data.length; i++) {
            float left = i * columnWidth + columnWidth * 0.2f;
            float top = height - (data[i] / maxDataValue) * columnMaxHeight;
            float right = left + columnWidth * 0.6f;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(42);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(String.valueOf((int)data[i]), (left + right) / 2, top - 10, paint);

            paint.setColor(Color.rgb(238, 0, 85));
            canvas.drawText(labels[i], (left + right) / 2, height - 10, paint);
        }
    }
}

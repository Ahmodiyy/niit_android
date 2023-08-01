package com.example.myapplication;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.List;

public class DrawDotsActivity extends Activity {

    int x, y;
    Paint paint;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Panel(this));

    }

    class Panel extends View implements OnTouchListener{
        List<Point> points = new ArrayList<Point>();
        public Panel(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.FILL);
            this.setOnTouchListener(this);

        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLACK);

            for (Point point : points) {
                canvas.drawCircle(point.x, point.y, 15, paint);
                // Log.d(TAG, "Painting: "+point);
            }
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                Point point = new Point();
                point.x = (int) event.getX();
                point.y = (int) event.getY();
                points.add(point);
                invalidate();
            }
            return true;
        }
    }

}

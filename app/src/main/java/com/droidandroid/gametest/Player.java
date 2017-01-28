package com.droidandroid.gametest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by oscar on 28/01/17.
 */

public class Player {

    private Rect rect;
    private Paint paint;

    public Player(Rect rect, int color) {
        this.rect = rect;
        this.paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas){
        canvas.drawRect(rect, paint);
    }

    public void update(Point point){
        rect.set(point.x - rect.width()/2, point.y - rect.height()/2,
                point.x + rect.width()/2, point.y + rect.height()/2);
    }
}

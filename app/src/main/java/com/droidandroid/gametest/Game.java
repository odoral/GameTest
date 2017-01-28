package com.droidandroid.gametest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by oscar on 28/01/17.
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;

    private Player player;
    private Point playerPoint;

    public Game(Context context) {
        super(context);

        getHolder().addCallback(this);

        gameThread = new GameThread(getHolder(), this);

        player = new Player(new Rect(0, 0, 128, 128), Color.BLUE);
        playerPoint = new Point(256, 960);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try {
                gameThread.setRunning(false);
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            playerPoint.set((int) motionEvent.getX(), 960);
        }

        return true;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
    }

    public void update() {
        player.update(playerPoint);
    }
}

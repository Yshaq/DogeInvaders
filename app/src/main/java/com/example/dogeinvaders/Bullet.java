package com.example.dogeinvaders;

import android.graphics.RectF;

public class Bullet {

    private float x;
    private float y;

    private RectF rect;

    public final int UP = 0;
    public final int DOWN = 1; //movt dirn

    int heading = -1;
    float speed = 700;

    private int width = 2;
    private int height;
    private int screenY;

    private boolean isActive;

    public Bullet(int y) {
        height = y/20;
        isActive = false;
        screenY = y;

        rect = new RectF();
    }

    public RectF getRect() {
        return rect;
    }

    public boolean getStatus() {
        return isActive;
    }

    public void setInactive() {
        isActive = false;
    }

    public float getImpactPointY() {
        if(heading == DOWN)
            return (y + height);
        else
            return y;

    }

    public boolean shoot(float startX,  float startY, int direction) {
        if (!isActive) {
            x = startX;
            y = startY;
            heading = direction;
            isActive = true;
            return true;
        }

        return false; //already active
    }

    public void update (long fps) {
        if (heading == UP)
            y -= speed/fps;
        else
            y += speed/fps;

        rect.left = x;
        rect.right = x + width;
        rect.top = y;
        rect.bottom = y + height;

        if(heading == UP && rect.top <= 0)
            isActive = false;
        else if (heading == DOWN && rect.bottom >= screenY)
            isActive = false;

    }
}

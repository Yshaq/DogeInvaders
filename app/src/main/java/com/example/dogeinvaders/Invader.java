package com.example.dogeinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import java.util.Random;

public class Invader {
    RectF rect;

    Random generator = new Random();

    private Bitmap bitmap1;
    private Bitmap bitmap2;

    private float length;
    private float height;

    private float x;
    private float y;

    private float speed;
    private float multiplier;

    public final int LEFT = 1;
    public final int RIGHT = 2;

    private int direction = RIGHT;

    boolean isVisible;

    public Invader(Context context, int row, int column, int screenX, int screenY) {

        rect = new RectF();

        length = screenX/20;
        height = screenY/20;

        isVisible = true;

        int padding = screenX/25;

        x = column * (length + padding);
        y = row * (length + padding/4);

        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader1);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader2);

        bitmap1 = Bitmap.createScaledBitmap(bitmap1, (int)length, (int)height, false);
        bitmap2 = Bitmap.createScaledBitmap(bitmap2, (int)length, (int)height, false);

        speed = 80;
        multiplier = 1.18f;
    }

    public void setInvisible() { isVisible = false; }
    public boolean getVisibility() { return isVisible; }
    public RectF getRect() { return rect; }
    public Bitmap getBitmap1() { return bitmap1; }
    public Bitmap getBitmap2() { return bitmap2; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getLength() {return length; }

    public void update(long fps) {
        if(direction == LEFT) {
            x -= speed/fps;
        }
        if (direction == RIGHT) {
            x += speed/fps;
        }

        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;
    }

    public void drop() {
        if (direction == LEFT)
            direction = RIGHT;
        else
            direction = LEFT;

        y += height;
        speed *= multiplier;
    }

    public boolean takeAim(float playerShipX, float playerShipLength) {
        int randomNumber = -1;
        if (x < playerShipX + playerShipLength && x > playerShipX - length) {
            randomNumber = generator.nextInt(150);
            if(randomNumber == 0)
                return true;
        }
        randomNumber = generator.nextInt(2000);
        if(randomNumber == 0)
            return true;

        return false;
    }


}

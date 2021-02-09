package com.example.dogeinvaders;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import static java.lang.System.currentTimeMillis;

public class DogeInvadersEngine extends SurfaceView implements Runnable {

    private Thread gameThread=null;

    private SurfaceHolder ourHolder;

    private Canvas canvas;
    private Paint paint;

    private long fps;
    private long timeThisFrame;

    private int screenX;
    private int screenY;

    private boolean playing;
    private boolean paused = true;

    Player player;
    Bullet bullet;
    Bullet [] invadersBullet = new Bullet[200];
    Bullet nextBullet;
    int maxInvadersBullets = 10;

    Invader [] invaders = new Invader[60];
    int numInvaders = 0;

    DefenceBrick [] bricks = new DefenceBrick[400];
    int numBricks = 0;

    SoundPool soundPool;
    int damageShelterID = -1;
    int invaderExplodeID = -1;
    int playerExplodeID = -1;
    int shootID = -1;
    int uhID = -1;
    int ohID = -1;

    int score = 0;
    int lives = 3;

    private long menaceInteval = 1000;
    private boolean uhOrOh;
    private long lastMenaceTime = System.currentTimeMillis();


    public DogeInvadersEngine(Context context, int x, int y) {
        super(context);

        ourHolder = getHolder();

        paint = new Paint();

        screenX = x;
        screenY = y;

        //Loading the sounds
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("damageshelter.ogg");
            damageShelterID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("invaderexplode.ogg");
            invaderExplodeID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("playerexplode.ogg");
            playerExplodeID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("shoot.ogg");
            shootID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("uh.ogg");
            uhID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("oh.ogg");
            ohID = soundPool.load(descriptor, 0);

        }catch(IOException e) {
            Log.e("error","failed to load sound files");
        }

        prepareLevel();
    }


    @Override
    public void run() {

        while (playing) {

            long startFrameTime = System.currentTimeMillis();

            if (!paused) {
                update();
            }

            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame>=1) {
                fps = 1000/timeThisFrame;
            }

        }

    }

    private void update() {
        //did invader bump side of screen
        boolean bumped = false;

        //did player lose ?
        boolean lost = false;

        //move player

        //update invaders

        //update invader bullets

        //did invader bump

        if (lost) {
            prepareLevel();
        }

        //update player bullet

        //did player bullet hit top of screen

        //did invader bullet hit bottom of screen

        //did player bullet hit invader

        //did invader bullet hit shelter

        //did player bullet hit shelter

        //did invader bullet hit player

    }

    public void pause() {
        playing = false;
        paused = true;
        try{
            gameThread.join();
        } catch(InterruptedException e) {
            Log.e("Error: ","joining Thread");
        }


    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void prepareLevel() {
        //initialize game objects

        //new player

        //prepare player bullet

        //initialize invadersBullets array

        //build Invader army

        //reset menace level

        //Build shelters

    }

    private void draw() {
        if (ourHolder.getSurface().isValid()){
            canvas = ourHolder.lockCanvas();
            //bg
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            //player

            //invaders

            //bricks

            //player bullet

            //invader bullets

            //draw HUD
            paint.setColor(Color.argb(255,255,255,255));
            paint.setTextSize(70);
            canvas.drawText("Score: " + score + " Lives: " + lives, 10, 80, paint);

            //post to screen
            ourHolder.unlockCanvasAndPost(canvas);
        }

    }

    //touch controls
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch(motionEvent.getAction() & motionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }
}

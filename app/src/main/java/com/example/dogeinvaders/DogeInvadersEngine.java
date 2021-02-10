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

public class DogeInvadersEngine extends SurfaceView implements Runnable {

    private Thread gameThread=null;

    private SurfaceHolder ourHolder;

    Context context;

    private Canvas canvas;
    private Paint paint;

    private long fps;
    private long timeThisFrame;

    private int screenX;
    private int screenY;

    private boolean playing;
    private boolean paused = true;

    Player playerShip;
    Bullet bullet;
    Bullet [] invadersBullets = new Bullet[200];
    int nextBullet;
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

        this.context = context;

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

            if(!paused) {
                if(startFrameTime - lastMenaceTime > menaceInteval) {
                    if(uhOrOh)
                        soundPool.play(uhID,1,1,0,0,1);
                    else
                        soundPool.play(ohID,1,1,0,0,1);

                    lastMenaceTime = System.currentTimeMillis();
                    uhOrOh = !uhOrOh;
                }

            }

        }

    }

    private void update() {
        //did invader bump side of screen
        boolean bumped = false;

        //did player lose ?
        boolean lost = false;

        //move player
        playerShip.update(fps);

        //update invaders
        for (int i=0; i<numInvaders; i++) {
            if (invaders[i].getVisibility()){
                invaders[i].update(fps);

                if(invaders[i].takeAim(playerShip.getX(),playerShip.getLength())){
                    if(invadersBullets[nextBullet].shoot(invaders[i].getX() + invaders[i].getLength()/2,
                            invaders[i].getY(), bullet.DOWN)) {
                        nextBullet++;

                        if (nextBullet == maxInvadersBullets) {
                            nextBullet = 0;
                        }
                    }
                }
                if (invaders[i].getX() > screenX - invaders[i].getLength() || invaders[i].getX() < 0) {
                    bumped = true;
                }
            }
        }

        //update player bullet
        if(bullet.getStatus()) {
            bullet.update(fps);
        }

        //update invader bullets
        for (int i=0; i<invadersBullets.length; i++) {
            if(invadersBullets[i].getStatus()) {
                invadersBullets[i].update(fps);
            }
        }
        //did invader bump
        if (bumped) {
            for(int i=0; i<numInvaders; i++) {
                invaders[i].drop();

                if(invaders[i].getY() > screenY - screenY/8) {
                    lost = true;
                }
            }
            menaceInteval -= 80;
        }

        if (lost) {
            paused = true;
            prepareLevel();
        }

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
        numInvaders = 0;
        //initialize game objects

        //new player
        playerShip = new Player(context, screenX, screenY);

        //prepare player bullet
        bullet = new Bullet(screenY);

        //initialize invadersBullets array
        for (int i = 0; i < invadersBullets.length; i++) {
            invadersBullets[i] = new Bullet(screenY);
        }

        //build Invader army
        for(int column=0; column<6; column++) {
            for (int row = 0; row<3; row++) {
                invaders[numInvaders] = new Invader(context, row, column, screenX, screenY);
                numInvaders++;
            }
        }

        //reset menace level
        menaceInteval = 1000;

        //Build shelters

    }

    private void draw() {
        if (ourHolder.getSurface().isValid()){
            canvas = ourHolder.lockCanvas();
            //bg
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            //player
            canvas.drawBitmap(playerShip.getBitmap(), playerShip.getX(), screenY - playerShip.getHeight(), paint);

            //invaders
            for(int i=0; i<numInvaders; i++) {
                if (invaders[i].getVisibility()) {
                    if (uhOrOh)
                        canvas.drawBitmap(invaders[i].getBitmap1(), invaders[i].getX(), invaders[i].getY(), paint);
                    else
                        canvas.drawBitmap(invaders[i].getBitmap2(), invaders[i].getX(), invaders[i].getY(), paint);

                }
            }

            //bricks

            //player bullet
            if (bullet.getStatus()) {
                canvas.drawRect(bullet.getRect(),paint);
            }

            //invader bullets
            for (int i=0; i<invadersBullets.length; i++) {
                if(invadersBullets[i].getStatus()) {
                    canvas.drawRect(invadersBullets[i].getRect(), paint);
                }
            }

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
                paused = false;

                if(motionEvent.getY() > screenY - screenY/8) {
                    if(motionEvent.getX() > screenX/2)
                        playerShip.setMovementState(playerShip.RIGHT);
                    else
                        playerShip.setMovementState(playerShip.LEFT);
                }
                else {
                    if(bullet.shoot(playerShip.getX() + playerShip.getLength()/2, screenY, bullet.UP ))
                        soundPool.play(shootID, 1, 1, 0, 0, 1);
                }

                break;

            case MotionEvent.ACTION_UP:
                if (motionEvent.getY() > screenY - screenY/8) {
                    playerShip.setMovementState(playerShip.STOPPED);
                }

                break;
        }

        return true;
    }
}

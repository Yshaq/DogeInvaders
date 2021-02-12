package com.example.dogeinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class DogeInvadersActivity extends AppCompatActivity {



    DogeInvadersEngine dogeInvadersEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        dogeInvadersEngine = new DogeInvadersEngine(this, size.x, size.y);
        setContentView(dogeInvadersEngine);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        dogeInvadersEngine.pause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        dogeInvadersEngine.resume();
    }
}
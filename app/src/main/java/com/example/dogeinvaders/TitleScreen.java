package com.example.dogeinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TitleScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
    }

    public void goToDogeInvaders(View view)
    {
        Intent dogeInvadersScreen = new Intent(this, DogeInvadersActivity.class);
        startActivity(dogeInvadersScreen);
    }

    public void goToStoryScreen(View view)
    {
        Intent storyScreen = new Intent(this, StoryScreen.class);
        startActivity(storyScreen);
    }
}
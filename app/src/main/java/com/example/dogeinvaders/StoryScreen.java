package com.example.dogeinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryScreen extends AppCompatActivity {

    ImageView mainImage;
    ImageView speakerImage;
    TextView text;
    Button nextButton;

    Story story = new Story(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_screen);
        mainImage = (ImageView)findViewById(R.id.mainImage);
        speakerImage = (ImageView)findViewById(R.id.speakerImage);
        text = (TextView)findViewById(R.id.mainText);
        nextButton = (Button)findViewById(R.id.nextButton);

        story.starting();
    }

    public void button(View view) {
        story.goNext(story.next);
    }
}
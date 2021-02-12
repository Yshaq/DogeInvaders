package com.example.dogeinvaders;

import android.view.View;

public class Story {
    StoryScreen gs;
    String next;


    public Story(StoryScreen gs) {
        this.gs = gs;

    }

    public void goNext(String next) {
        switch(next) {
            case "starting": starting(); break;
            case "s2": s2(); break;
        }
    }

    public void starting()
    {
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.doge);

        gs.text.setText("Finally, I have achieved my dream");

        next="s2";
    }

    public void s2(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.doge);

        gs.text.setText("I made it into IIT");

        next="starting";
    }

}

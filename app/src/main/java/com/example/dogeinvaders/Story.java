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
            case "s3": s3(); break;
            case "s4": s4(); break;
            case "s5": s5(); break;
            case "s6": s6(); break;
            case "s7": s7(); break;
            case "s8": s8(); break;
            case "s9": s9(); break;
            case "s10": s10(); break;
            case "s11": s11(); break;
            case "s12": s12(); break;

        }
    }

    public void starting()
    {
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.doge);

        gs.text.setText("Finally, I have achieved my dreams.....");

        next="s2";
    }

    public void s2(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.doge);

        gs.text.setText("I made it into IIT. After working hard for 2 years. Finally.");

        next="s3";
    }

    public void s3(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.doge);

        gs.text.setText("And with the Rs. 1 lakh prize money I got from my coaching institute, I bought this mountain of burbgers.");

        next="s4";
    }

    public void s4(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.doge);

        gs.text.setText("I spent all the money on it, but no worries. I will join COPS and become OP coder, and I will get 1 crore placement" +
                " in Goomgle. Then I can buy even bigger burbger mountain.");

        next="s5";
    }

    public void s5(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.doge);

        gs.text.setText("I also want to find girlfrend, I hope seniors will give me tips for that. OMG, I am set for life :D");

        next="s6";
    }

    public void s6(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.billy);

        gs.text.setText("NOT SO FAST CHEEMS!!!");

        next="s7";
    }

    public void s7(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.doge);

        gs.text.setText("BILLY !!?!?");

        next="s8";
    }

    public void s8(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.billy);

        gs.text.setText("Yes, it's me! Dont think I will let you live so peacefully. Did you think you could escape me if you moved to varanasi?");

        next="s9";
    }


    public void s9(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.billy);

        gs.text.setText("You FOOL! You can never escape me! I will destroy your life, and I will start by destroying your college");

        next="s10";
    }


    public void s10(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.angrydoge);

        gs.text.setText("No! I will not let you destroy my college, f*** off you pussy");

        next="s11";
    }

    public void s11(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.billy);

        gs.text.setText("Mwahahhaaaa! You fatso, all you can do is eat burgers, I'd like to see you stop if, if you can move your phat a** that is");

        next="s12";
    }


    public void s12(){
        gs.mainImage.setImageResource(R.drawable.bhu1o);
        gs.speakerImage.setImageResource(R.drawable.angrydoge);

        gs.text.setText("BRING IT ONN!!");

        gs.nextButton.setVisibility(View.GONE);
        gs.dogePlay.setVisibility(View.VISIBLE);



    }


}

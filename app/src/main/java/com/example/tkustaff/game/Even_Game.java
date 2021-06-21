package com.example.tkustaff.game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class Even_Game extends AppCompatActivity{
    public static boolean isPlay;
    private float x_01,x_02,y_01,y_02;
    public static MediaPlayer mp ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even__game);
        mp = new MediaPlayer();
       mp=MediaPlayer.create(Even_Game.this,R.raw.music);
       mp.start();


    }



    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                x_01 = x;
                y_01 = y;
                break;

            case MotionEvent.ACTION_UP:
                x_02 = x;
                y_02 = y;

                if(x_01!=0 && y_01!=0) {
                    if(x_01-x_02>8) {

                        Intent intent = new Intent();
                        intent.setClass(Even_Game.this  , Teach.class);
                        startActivity(intent);  //向右滑
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    }

                    if(x_01-x_02<-8) { Intent intent = new Intent();
                        intent.setClass(Even_Game.this  , people.class);
                        startActivity(intent);    //向左滑
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            default:
                break;

        }
        return super.onTouchEvent(event);
    }
        public void onselect(View view) {

        Intent intent = new Intent();
        intent.setClass(Even_Game.this  , MainActivity.class);
            startActivity(intent);
    }


    public void sounddd(View view) {
        Intent intent = new Intent();
        intent.setClass(Even_Game.this  , setting.class);
        startActivity(intent);
    }

    public void score(View view) {
        Intent intent = new Intent();
        intent.setClass(Even_Game.this  , Score.class);
        startActivity(intent);
    }


}

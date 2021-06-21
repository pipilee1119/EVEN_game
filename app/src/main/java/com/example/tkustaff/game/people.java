package com.example.tkustaff.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class people extends AppCompatActivity {
    private float x_01,x_02,y_01,y_02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
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
                        intent.setClass(people.this  , Even_Game.class);
                        startActivity(intent);  //向右滑
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }

                    if(x_01-x_02<-8) { Intent intent = new Intent();
                        intent.setClass(people.this  , Teach.class);
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

    public void a(View view) {
        Uri uri=Uri.parse("https://www.facebook.com/profile.php?id=100000468822085");
        Intent i=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);

    }

    public void b(View view) {
        Uri uri=Uri.parse("https://www.facebook.com/profile.php?id=100001315207239");
        Intent i=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);

    }

    public void c(View view) {
        Uri uri=Uri.parse("https://www.facebook.com/profile.php?id=100009104498901");
        Intent i=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);

    }

    public void d(View view) {
        Uri uri=Uri.parse("https://www.facebook.com/chan.jo.7543");
        Intent i=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);

    }
}

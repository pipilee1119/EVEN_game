package com.example.tkustaff.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvScore;
    private int score=0;

//    private MediaPlayer mp;

    public MainActivity(){//用來與外界呼應
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore= (TextView) findViewById(R.id.tvScore);




//        try{
//            mp = MediaPlayer.create(this,R.raw.gm);
//            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mp.setLooping(true);
//            mp.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
//    @Override
//    protected void onResume(){
//        super.onResume();
//        mp.start();
//    }
//    @Override
//    protected void onPause(){
//        super.onPause();
//        mp.pause();
//    }
//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//        mp.release();
//    }
    public void showScore(){//顯示分數
        tvScore.setText(score+"");
    }
    public void addScore(int s){//加分數
        score+=s;
        showScore();
    }
    public void clearScore(){
        score=0;
    }
    private static MainActivity mainActivity=null;
    public static MainActivity getMainActivity(){
        return mainActivity;
    }


}

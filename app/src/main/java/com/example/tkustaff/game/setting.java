package com.example.tkustaff.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.tkustaff.game.R.layout;

public class setting extends AppCompatActivity {

private Button music;
private Button souund;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_setting);
music =(Button)findViewById(R.id.MUSIC);
souund=(Button)findViewById(R.id.SOUND);

    }


    public void musiconclick(View view) {



    }

    public void soundonclick(View view) {
    }
}

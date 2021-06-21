package com.example.tkustaff.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by tkustaff on 2017/12/1.
 */

public class Card extends FrameLayout {
    public Card(@NonNull Context context) {//設定卡格式
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);//字體
        label.setGravity(Gravity.CENTER);//置中
        label.setBackgroundResource(R.mipmap.bc);//顏色

        LayoutParams lp=new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);
        addView(label,lp);
        setNum(0);
    }
    private int num=0;


    public int getNum() {//提取數值用
        return num;
    }

    public void setNum(int num) {//輸入數值用
        this.num = num;

        if(num<=0){
            if(num==-1){
                label.setTextSize(32);
                label.setText("×");
            }
            else if(num==-2){
                label.setTextSize(32);
                label.setText("+");
            }
            else if(num==-3){
                label.setTextSize(32);
                label.setText("-");
            }
            else{
                label.setTextSize(32);
                label.setText("");
            }
        }
        else{
            if(num>999&&num<99999){
                label.setTextSize(24);
                label.setText(num+"");
            }
            else if(num>99999){
                label.setTextSize(12);
                label.setText(num+"");
            }
            else {
                label.setTextSize(32);
                label.setText(num+"");
            }
        }
    }
    private TextView label;


}

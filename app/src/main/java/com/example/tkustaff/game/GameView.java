package com.example.tkustaff.game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkustaff on 2017/12/1.
 */

public class GameView extends GridLayout {



    public GameView(Context context) {
        super(context);
        initGameView();
    }
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }
    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }
    private int n [][] =new int[4][4];//紀錄移動到過哪個點用
    private int co =1;//計算次數用 和 紀錄經過觸控順序
    private int total=0;//總數
    private int math=0;
    private int math2=0;
    private int math3=0;
    private int re=0;



    private void initGameView(){
        setColumnCount(3);//行數
        setBackgroundColor(0xffFFBF00);//底色
        setOnTouchListener(new OnTouchListener() {//觸控事件
            private float startX,startY,offsetX,offsetY,mX,mY,x,y,mx,my;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN: //抓 手指按下
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        x = motionEvent.getX();                  //觸控的X軸位置
                        y = motionEvent.getY();                  //觸控的Y軸位置
                        invalidate();
                        checkXY(startX,startY);
                        if(startX>734||startY>734||startX<1||startY<1) {//方格外
                            co=4;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE: //抓 手指移動
                        mX = motionEvent.getX();
                        mY = motionEvent.getY();
                        mx = (int) (motionEvent.getRawX() - x);
                        my = (int) (motionEvent.getRawY() - y);
                        invalidate();
                        checkmXY(mX,mY);
                        if(mX>734||mY>734||mX<1||mY<1) {//方格外
                            co=4;
                        }

                        break;
                    case MotionEvent.ACTION_UP://抓 手指放開
                        if(math!=0&&math2!=0&&math3!=0){
                            checkup();
                            invalidate();
                        }
                        for(int y=0;y<3;y++){
                            for(int x=0;x<3;x++){
                                if(n[x+1][y+1]>0&&n[x+1][y+1]!=5){n [x+1][y+1]=0;}//將沒用到的重回0次
                            }
                        }
                        if(re>=8){//當全部運算與數字都用完
                            for(int y=0;y<3;y++){
                                for(int x=0;x<3;x++){
                                    n [x+1][y+1]=0;//記錄歸0
                                }
                            }
                            if(total%2==0){//為偶數的話
                                MainActivity.getMainActivity().addScore(1);
                                for(int i=0;i<2;i++){ //加入兩個乘
                                    addRandom1();
                                }
                                addRandom2();//一個加
                                addRandom3();//一個減
                                for(int i=0;i<4;i++){ //加入四個隨機數
                                    addRandomNum();
                                }
                                re=0;//歸0
                            }
                            else {//為奇數
                                MainActivity.getMainActivity().clearScore();
                                checkcomplete();//重來
                                re=0;//歸0
                            }
                        }
                        math=0;math2=0;math3=0;//數值回歸0
                        total=0;//總歸0
                        co=1;
                        break;
                }
                return true;
            }
        });

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { //照各種不同螢幕調整卡片大小
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth = (Math.min(w,h)-10)/3;
        addCards(cardWidth,cardWidth);
        startGame(); //執行遊戲

    }
    private void addCards(int cardWidth,int cardHeight){ //加入數字卡
        Card c;
        for(int y=0;y<3;y++){
            for (int x=0;x<3;x++){
                c = new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);
                cardsMap[x][y]=c;

            }
        }

    }
    private void checkXY(float startX,float startY){ //抓起始點
        int cx = 0,cy=0;
        if(startX<245&&startX>1){//抓第一直行 1-1 1-2 1-3
            if(startY<245&&startY>1&&n[1][1]<=0&&co<4){//1-1 如果起始按下處不為1-1且移動到1-1已計算一次且總計次不超過三次則執行
                cx=1;cy=1;
            }
            else if(startY>245&&startY<490&&n[1][2]<=0&&co<4){
                cx=1;cy=2;
            }
            else if(startY>490&&startY<734&&n[1][3]<=0&&co<4){
                cx=1;cy=3;
            }
        }
        else if(startX>245&&startX<490){//抓第二直行 2-1 2-2 2-3
            if(startY<245&&startY>1&&n[2][1]<=0&&co<4){//2-1 如果起始按下處不為2-1且移動到2-1已計算一次且總計次不超過三次則執行
                cx=2;cy=1;
            }
            else if(startY>245&&startY<490&&n[2][2]<=0&&co<4){
                cx=2;cy=2;
            }
            else if(startY>490&&startY<734&&n[2][3]<=0&&co<4){
                cx=2;cy=3;
            }
        }
        else if(startX>490&&startX<734) {//抓第三直行 3-1 3-2 3-3
            if (startY < 245 && startY > 1 && n[3][1] <= 0&&co<4) {//3-1 如果起始按下處不為3-1且移動到3-1已計算一次且總計次不超過三次則執行
                cx=3;cy=1;
            } else if (startY > 245 && startY < 490 && n[3][2] <= 0&&co<4) {
                cx=3;cy=2;

            } else if (startY > 490 && startY < 734 && n[3][3] <= 0&&co<4) {
                cx=3;cy=3;
            }
        }

        if(cx!=0&&cy!=0) {
            System.out.println(cx + "-" + cy);
            math = cardsMap[cx - 1][cy - 1].getNum();
            System.out.println(math);
            n[cx][cy] = co;
            checkNum();
            System.out.println(math);
            co++;
        }
    }
    private void checkmXY(float mX,float mY){//抓使用者移動座標
        int cx = 0,cy=0;
        if(mX<245&&mX>1){
            if(mY<245&&mY>1&&n[1][1]<=0&&co<4){
                cx=1;cy=1;
            }
            else if(mY>245&&mY<490&&n[1][2]<=0&&co<4){
                cx=1;cy=2;
            }
            else if(mY>490&&mY<734&&n[1][3]<=0&&co<4){
                cx=1;cy=3;
            }
        }
        else if(mX>245&&mX<490){
            if(mY<245&&mY>1&&n[2][1]<=0&&co<4){
                cx=2;cy=1;
            }
            else if(mY>245&&mY<490&&n[2][2]<=0&&co<4){
                cx=2;cy=2;
            }
            else if(mY>490&&mY<734&&n[2][3]<=0&&co<4){
                cx=2;cy=3;
            }
        }
        else if(mX>490&&mX<734) {
            if (mY < 245 && mY > 1 && n[3][1] <= 0&&co<4) {
                cx=3;cy=1;
            } else if (mY > 245 && mY < 490 && n[3][2] <= 0&&co<4) {
                cx=3;cy=2;

            } else if (mY > 490 && mY < 734 && n[3][3] <= 0&&co<4) {
                cx=3;cy=3;
            }
        }
        if(cx!=0||cy!=0){
        System.out.println(cx+"-"+cy+"m");
        n[cx][cy]=co;
        if(co==2){math2=cardsMap[cx-1][cy-1].getNum();System.out.println(math2);checkCuc();System.out.println(math2);}
        else if(co==3){math3=cardsMap[cx-1][cy-1].getNum();System.out.println(math3);checkNum3();System.out.println(math3);}
        co++;
        }
    }
    private void checkup(){//手放開執行運算
        if(math2==-2&&math3>0){//加
            total=math+math3;
            System.out.println(total);
        }
        else if(math2==-1&&math3>0){//乘
            total=math*math3;
            System.out.println(total);
        }
        else if(math2==-3&&math3>0){//減
            total=math-math3;
            System.out.println(total);
        }
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++) {
                if (n[x+1][y+1] == 3) {cardsMap[x][y].setNum(total);n[x+1][y+1] = 0;}
                else if (n[x+1][y+1] > 0 && n[x+1][y+1] != 3&&n[x+1][y+1]!=5) {cardsMap[x][y].setNum(0);n[x+1][y+1] = 5;re++;}
            }
        }
        if(total<=0){//計算為負數 則失敗
            for(int y=0;y<3;y++){
                for(int x=0;x<3;x++){
                    n [x+1][y+1]=0;//記錄歸0
                }
            }
            MainActivity.getMainActivity().clearScore();
            checkcomplete();
        }
    }
    private void checkNum(){ //確認按下第一個 是否為數字
        if(math<0){
            math=0;
            total=0;
            co=4;
        }
    }
    private void checkCuc(){ //確認按下第二個 是否為乘除
        if(math2>0){
            math2=0;
            total=0;
            co=4;
        }
    }
    private void checkNum3(){ //確認按下第三個 是否為數字
        if(math3<0){
            math3=0;
            total=0;
            co=4;
        }
    }

    private void clearn(){//清空 觸控紀錄
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                n [x][y]=0;
            }
        }
    }
    private void startGame(){ //遊戲起始
        for(int y=0;y<3;y++){ //清空原本的值
            for(int x=0;x<3;x++){
                cardsMap[x][y].setNum(0);//清空 卡片
            }
        }
        clearn();
        for(int i=0;i<2;i++){ //加入兩個乘
            addRandom1();
        }
        addRandom2();//一個加
        addRandom3();//一個減
        for(int i=0;i<5;i++){ //加入五個隨機數
            addRandomNum();
        }

    }
    private void addRandomNum(){ //加入隨機亂數
        emptyPoints.clear();//清空　以防上次所抓的空格　早已填入值　又重新用一次
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                if(cardsMap[x][y].getNum()==0){
                    emptyPoints.add(new Point(x,y));//抓取空的格子
                }
            }
        }
        Point p= (Point) emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum((int)(Math.random()*19)+1);
    }
    private void addRandom1(){ //加入乘
        emptyPoints.clear();
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                if(cardsMap[x][y].getNum()==0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        Point p= (Point) emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(-1);
    }
    private void addRandom2(){ //加入加
        emptyPoints.clear();
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                if(cardsMap[x][y].getNum()==0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        Point p= (Point) emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(-2);
    }
    private void addRandom3(){ //加入減
        emptyPoints.clear();
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                if(cardsMap[x][y].getNum()==0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        Point p= (Point) emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(-3);
    }
    private void checkcomplete(){ //確認是否為奇數 =>結束
        boolean complete = true;
        if (complete){//跳出警示框 顯示結束
            new AlertDialog.Builder(getContext()).setTitle("笑死").setMessage("連算術都不會").setPositiveButton("重來", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   startGame();//重新開始t

                }
            }).show();
        }
    }

    public Card[][] cardsMap = new Card[3][3]; //3x3方格
    private List emptyPoints = new ArrayList(); //計算空方格數






//    public void onWindowFocusChanged(boolean hasFocus){
//        super.onWindowFocusChanged(hasFocus);
//        if(hasFocus){
//            for(int x=0;x<3;x++){
//                for(int y=0;y<3;y++){
//
//                    int[] location = new int [2];
//                    cardsMap[x][y].getLocationOnScreen(location);
//                    int lx = location[0];
//                    int ly = location[1];
//                    System.out.println("x:"+lx+"y:"+ly);
//                    System.out.println("Left:"+cardsMap[x][y].getLeft()+"Right:"+cardsMap[x][y].getRight()+"Top:"+cardsMap[x][y].getTop()+"Bottom:"+cardsMap[x][y].getBottom());}
//            }
//
//        }
//    }

}

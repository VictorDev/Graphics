package com.example.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/*Диалоговое облако с текстом. По умолчанию в облако помещается 96 символов, включая пробелы, запятые и т.д.
 * ВАЖНО. Облако имеет начальный отступ 100px слева и 300px сверху*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint paint, paintBackground, fontPaint;
        Path path, pathBackground;
        Matrix mainMatrix;
        boolean needChangeText = false;
        private String text="default text";
        int size;
        int fontSize = 50;
        private float translateX = 0, translateY = 0;
        private float sX = 1, sY = 1, pX = 1, pY = 1;

        public DrawView(Context context) {
            super(context);
            mainMatrix = new Matrix();
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);

            path = new Path();
            path.reset();
            path.moveTo(300,300);
            path.rQuadTo(150, 150, 300, 0);
            path.rLineTo(150,-150);

            Matrix matrix = new Matrix();
            matrix.reset();
            matrix.setRotate(135,300,300);


            path.transform(matrix);

            Matrix matrix2 = new Matrix();
            matrix2.setRotate(45,100,750);
            matrix2.postTranslate(-12,-40);

            Path path2 = new Path();
            path2.moveTo(100,750);
            path2.rQuadTo(100,100,200,0);
            path2.transform(matrix2);
            path2.rLineTo(-30,100);
            path2.rLineTo(80,-100);
            path2.rLineTo(500,0);

            Path path3 = new Path();
            path3.moveTo(778,852);
            path3.rQuadTo(100,100,200,0);

            Matrix matrix3 = new Matrix();
            matrix3.setRotate(-45,778,852);
            matrix3.postTranslate(0,-1);

            path3.transform(matrix3);
            path3.rLineTo(0,-200);

            Path path4 = new Path();
            path4.moveTo(920,510);
            path4.rQuadTo(150,150,300,0);

            Matrix matrix4 = new Matrix();
            matrix4.setRotate(-135,920,510);
            matrix4.postTranslate(0,3);

            path4.transform(matrix4);
            path4.rLineTo(-430,0);

            path3.addPath(path4);
            path2.addPath(path3);
            path.addPath(path2);

            pathBackground = new Path();
            pathBackground.moveTo(88,710);
            pathBackground.rLineTo(690,141);
            pathBackground.rLineTo(142,-342);
            pathBackground.rLineTo(-620,-210);

            paintBackground = new Paint();
            paintBackground.setStrokeWidth(3);
            paintBackground.setStyle(Paint.Style.FILL);
            paintBackground.setColor(Color.WHITE);

            fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            fontPaint.setTextSize(fontSize);

            size = text.length();
            if(size>24){
                needChangeText = true;
            }
            changeCloud();
        }



        @Override
        protected void onDraw(Canvas canvas) {
            //changeCloud();
            canvas.drawPath(path,paintBackground);
            canvas.drawPath(pathBackground,paintBackground);
            canvas.drawPath(path, paint);
            if (needChangeText) {
                widhtText(canvas);
            }else{
                float translate = 200 + translateX;
                canvas.drawText(text,translate,600+translateY,fontPaint);
            }
        }

        void changeCloud(){
            mainMatrix.setTranslate(translateX,translateY);
            mainMatrix.postScale(sX,sY,pX,pY);
            path.transform(mainMatrix);
            pathBackground.transform(mainMatrix);
        }

        void widhtText(Canvas canvas){
            float defaultY=400+translateY;
            int cout = size/24;
            int start = 0, end = 24;
            for(int i = 0; i<cout;i++){
                canvas.drawText(text,start,end,200+translateX,defaultY,fontPaint);
                start+=24;
                end+=24;
                defaultY+=100;
                if(start>96){
                    return;
                }
            }
        }

        public void setTranslateCloud(float x, float y){
            translateX = x;
            translateY = y;
        }

        public void setScaleCloud(float sx, float sy){
            sX = sx;
            sY = sy;
        }

        public void setScaleCloud(float sx, float sy, float px, float py){
            sX = sx;
            sY = sy;
            pX = px;
            pY = py;
        }
    }


}

package com.example.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint paint;
        Path path,path2;
        Matrix matrix;

        public DrawView(Context context) {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);


            path = new Path();
            path.reset();
            path.moveTo(300,300);
            path.rQuadTo(150, 150, 300, 0);
            path.rLineTo(150,-150);

            matrix = new Matrix();
            matrix.reset();
            matrix.setRotate(135,300,300);


            path.transform(matrix);
            Matrix matrix2 = new Matrix();
            matrix2.setRotate(45,100,750);
            matrix2.postTranslate(-12,-40);

            path2 = new Path();
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

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            canvas.drawPath(path, paint);

        }
    }


}

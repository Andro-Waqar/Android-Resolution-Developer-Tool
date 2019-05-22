package com.softwindevs.androidresolution;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.tv_width);
        t2 = (TextView) findViewById(R.id.tv_height);

        setScreenSize(this);

    }

    public void setScreenSize(Context context) {
        int x, y, orientation = context.getResources().getConfiguration().orientation;

        WindowManager wm = ((WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE));
        Display display = wm.getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point screenSize = new Point();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                display.getRealSize(screenSize);
                x = screenSize.x;
                y = screenSize.y;
            } else {
                display.getSize(screenSize);
                x = screenSize.x;
                y = screenSize.y;
            }
        } else {
            x = metrics.widthPixels;
            y = metrics.heightPixels;
        }

        int width = getWidth(x, y, orientation);
        int height = getHeight(x, y, orientation);

        t1.setText(String.valueOf(width)+" px");
        t2.setText(String.valueOf(height)+" px");

    }

    private int getWidth(int x, int y, int orientation) {
        return orientation == Configuration.ORIENTATION_PORTRAIT ? x : y;
    }

    private int getHeight(int x, int y, int orientation) {
        return orientation == Configuration.ORIENTATION_PORTRAIT ? y : x;
    }


}

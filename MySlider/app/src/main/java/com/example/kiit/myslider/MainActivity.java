package com.example.kiit.myslider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  {

    public int currentimageindex=0;
    ImageView slidingimage;

    private int[] IMAGE_IDS = {
            R.mipmap.splash0, R.mipmap.splash1, R.mipmap.splash2,
            R.mipmap.splash3, R.mipmap.splash4,R.mipmap.splash5,
            R.mipmap.splash6,R.mipmap.splash7,R.mipmap.splash8,R.mipmap.splash9,
            R.mipmap.splash10};


    static TextView dots[];

    private LinearLayout mDotsLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mDotsLayout = (LinearLayout) findViewById(R.id.layoutDots);


        callanimation();
        addBottomDots(0);





    }
    public void callanimation(){
        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 4000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);



    }


  /*  public void onClick(View v) {

        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }*/



    /**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateandSlideShow() {

        slidingimage = (ImageView) findViewById(R.id.ImageView3_Left);

        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);


        currentimageindex++;

        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);


        slidingimage.startAnimation(rotateimage);

        addBottomDots(currentimageindex);

    }
    private void addBottomDots(int currentPage) {
        dots = new TextView[IMAGE_IDS.length];
        mDotsLayout.removeAllViews();
       // mDotsText=new TextView[IMAGE_IDS.length];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
           // dots[i].setTypeface(null, Typeface.BOLD);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            mDotsLayout.addView(dots[i]);
        }
        try {
            if (dots.length>0){
                 // dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
                 dots[currentPage%IMAGE_IDS.length].setTextColor(getResources().getColor(R.color.dot_active));
            }
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }


}

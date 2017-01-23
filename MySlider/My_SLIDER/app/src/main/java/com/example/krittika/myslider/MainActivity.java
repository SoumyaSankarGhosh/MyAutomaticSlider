package com.example.krittika.myslider;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

   public int currentimageindex=0;
    //    Timer timer;
//    TimerTask task;
    ImageView slidingimage;





    private int[] IMAGE_IDS = {
            R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4
    };







    private LinearLayout dotsLayout;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);


        addBottomDots(0);
        callanimation();

    }

    /*public void onClick(View v) {

        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }*/

    /**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateandSlideShow() {

        slidingimage = (ImageView)findViewById(R.id.ImageView3_Left);

        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);
        currentimageindex++;

        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);


        slidingimage.startAnimation(rotateimage);

       addBottomDots(currentimageindex);   ////////////////////////////nxt rnd
        Log.e("error",String.valueOf(currentimageindex));


    }
/*for moving dots under the slider */
    private void addBottomDots(int currentPage) {
        dots = new TextView[IMAGE_IDS.length];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);

        }

        try {
            if (dots.length > 0)
            {
                dots[currentPage%IMAGE_IDS.length].setTextColor(getResources().getColor(R.color.dot_active));

            }
           /* for (int i = 0 ; i < dots.length ; i++)
            {
                if (currentPage == i)
                {
                    dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
                }
                else {
                    dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
                }

            }*/

        }catch (ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
        /*finally {

              //callanimation();
          //  addBottomDots(IMAGE_IDS.);
        }
*/


       /* if (dots.length == currentPage)
        {
            addBottomDots(currentPage);

        }*/
        /*if (IMAGE_IDS.length == 0)
        {
            addBottomDots(currentimageindex);
        }*/



    }

    public void callanimation()
    {
        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();



            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 5000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);


    }



}

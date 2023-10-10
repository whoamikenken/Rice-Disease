package org.tensorflow.lite.examples.ricedisease;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Onboarding extends AppCompatActivity {

    ViewPager pager;
    LinearLayout indicate;
    SlideAdapter adapter;
    TextView[] dots;
    Button start, skip, next;
    int cPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //Hide ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //instantiate
        pager = findViewById(R.id.pager);
        indicate = findViewById(R.id.indicate);
        start = findViewById(R.id.start);
        skip = findViewById(R.id.skip);
        next = findViewById(R.id.next);

        //skip button
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Onboarding.this, Click_Main.class);
                startActivity(home);
                finish();
            }
        });

        //start button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Onboarding.this, Login.class);
                startActivity(home);
                finish();
            }
        });

        //next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(cPosition + 1);
            }
        });

        //PAGER
        adapter = new SlideAdapter(this);
        pager.setAdapter(adapter);

        //dots
        progress(0);
        pager.addOnPageChangeListener(change);
    }

    private void progress(int position){
        dots = new TextView[5];
        indicate.removeAllViews();

        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);

            indicate.addView(dots[i]);
        }

        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.yellow));
        }
    }

    //scrolled
    ViewPager.OnPageChangeListener change = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            progress(position);

            cPosition = position;

            if (position == 4) {
                start.setVisibility(View.VISIBLE);
            }
            else {
                start.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
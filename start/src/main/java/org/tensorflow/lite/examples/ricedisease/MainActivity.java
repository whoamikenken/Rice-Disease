package org.tensorflow.lite.examples.ricedisease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static  int SPLASH_SCREEN = 4000;
    Animation animate;
    ImageView splashImg;

    SharedPreferences sharedP;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Splash Screen
        animate = AnimationUtils.loadAnimation(this,R.anim.animate);
        splashImg = findViewById(R.id.splashImg);

        splashImg.setAnimation(animate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Show onBoarding screen only to new user
                sharedP = getSharedPreferences("Onboarding", MODE_PRIVATE);
                boolean firstimer = sharedP.getBoolean("firstimer", true);

                if(firstimer){
                    SharedPreferences.Editor edit = sharedP.edit();
                    edit.putBoolean("firstimer", false);
                    edit.apply();

                    Intent onboard = new Intent(MainActivity.this, Onboarding.class);
                    startActivity(onboard);
                    finish();
                }
                else{
                    Intent gotohome = new Intent(MainActivity.this, Login.class);
                    startActivity(gotohome);
                    finish();
                }
            }
        }, SPLASH_SCREEN);

    }
}

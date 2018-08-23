package project.dblearning.splashScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import project.dblearning.R;
import project.dblearning.login.LoginActivity;
import project.dblearning.introduction.IntroductionActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imgVwSplashLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Preferences
        SharedPreferences preferencesSplash = getSharedPreferences(getString(R.string.string_splash_preferences), Context.MODE_PRIVATE);
        Boolean bandActivity = preferencesSplash.getBoolean(getString(R.string.string_splash_flag), false);

        getSupportActionBar().hide();

        imgVwSplashLogo = findViewById(R.id.img_vw_splash_logo);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.transition_splash_screen);
        imgVwSplashLogo.startAnimation(myanim);
        if(bandActivity == false){

        final Intent i = new Intent (this, IntroductionActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();

    }

        else if (bandActivity == true){

            final Intent i = new Intent (this, LoginActivity.class);
            Thread timer = new Thread(){
                public void run(){
                    try{
                        sleep(3000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();

        }
    }

}

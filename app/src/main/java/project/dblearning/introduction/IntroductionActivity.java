package project.dblearning.introduction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import project.dblearning.R;
import project.dblearning.login.LoginActivity;

public class IntroductionActivity extends AppCompatActivity{

    private ViewPager viewPagerIntroContent;
    private LinearLayout viewIntroIcoSlide;

    private TextView[] mIcoSlide;

    private SlideAdapterIntroduction slideAdapterIntroduction;

    private Button btnIntroSlidePrev;
    private Button btnIntroSlideNext;
    private Button btnIntroClose;

    private int mCurrentPage;

    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        //Preferences
        SharedPreferences prefs = getSharedPreferences(getString(R.string.string_splash_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(getString(R.string.string_splash_flag), false);
        editor.commit();


        getSupportActionBar().hide();

        viewPagerIntroContent = findViewById(R.id.view_pager_intro_content);
        viewIntroIcoSlide = findViewById(R.id.view_intro_ico_slide);
        btnIntroSlidePrev = findViewById(R.id.btn_intro_slide_prev);
        btnIntroSlideNext = findViewById(R.id.btn_intro_slide_next);
        btnIntroClose = findViewById(R.id.btn_intro_close_intro);

        resources = getResources();


        slideAdapterIntroduction = new SlideAdapterIntroduction(this);

        viewPagerIntroContent.setAdapter(slideAdapterIntroduction);

        addIcoSlideIndicator(0);

        viewPagerIntroContent.addOnPageChangeListener(viewListener);

        //OnClickListeners

        btnIntroClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToLogin = new Intent(IntroductionActivity.this, LoginActivity.class);
                startActivity(goToLogin);

                //Preferences
                SharedPreferences preferences = getSharedPreferences(getString(R.string.string_splash_preferences), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(getString(R.string.string_splash_flag), true);
                editor.commit();
            }
        });

        btnIntroSlideNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int value = mCurrentPage + 1;

                if( value ==  mIcoSlide.length){
                    Intent goToLogin = new Intent(IntroductionActivity.this, LoginActivity.class);
                    startActivity(goToLogin);
                }else {
                    viewPagerIntroContent.setCurrentItem(value);
                }


            }
        });

        btnIntroSlidePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPagerIntroContent.setCurrentItem(mCurrentPage - 1);

            }
        });

        //Preferences
    }



    public void addIcoSlideIndicator(int position){

        mIcoSlide = new TextView[4];
        viewIntroIcoSlide.removeAllViews();

        for (int i = 0; i < mIcoSlide.length; i++){

            mIcoSlide[i] = new TextView(this);
            mIcoSlide[i].setText(Html.fromHtml("&#8226;"));
            mIcoSlide[i].setTextSize(35);
            mIcoSlide[i].setTextColor(getResources().getColor(R.color.transparentWhite));

            viewIntroIcoSlide.addView(mIcoSlide[i]);
        }

        if (mIcoSlide.length > 0){

            mIcoSlide[position].setTextColor(getResources().getColor(R.color.white));
            mIcoSlide[position].setTextSize(36);

        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override

        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {

            addIcoSlideIndicator(i);

            mCurrentPage = i;

            if (i == 0) {

                btnIntroSlideNext.setEnabled(true);
                btnIntroSlidePrev.setEnabled(false);
                btnIntroSlidePrev.setVisibility(View.INVISIBLE);

                btnIntroSlideNext.setText(R.string.string_intro_btn_next);
                btnIntroSlidePrev.setText("");

            } else if (i == mIcoSlide.length -1) {

                btnIntroSlideNext.setEnabled(true);
                btnIntroSlidePrev.setEnabled(true);
                btnIntroSlidePrev.setVisibility(View.VISIBLE);

                btnIntroSlideNext.setText(R.string.string_intro_btn_close);
                btnIntroSlidePrev.setText(R.string.string_intro_btn_prev);

                //Preferences
                SharedPreferences preferences = getSharedPreferences(getString(R.string.string_splash_preferences), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(getString(R.string.string_splash_flag), true);
                editor.commit();

            } else {

                btnIntroSlideNext.setEnabled(true);
                btnIntroSlidePrev.setEnabled(true);
                btnIntroSlidePrev.setVisibility(View.VISIBLE);

                btnIntroSlideNext.setText(R.string.string_intro_btn_next);
                btnIntroSlidePrev.setText(R.string.string_intro_btn_prev);
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };



}

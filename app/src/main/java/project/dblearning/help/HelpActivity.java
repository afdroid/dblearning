package project.dblearning.help;

import android.content.Intent;
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

public class HelpActivity extends AppCompatActivity {


    private ViewPager vwSlidePager;
    private LinearLayout vwIcoSlide;

    private TextView[] mIcoSlide;

    private SlideAdapterHelp slideAdapter;

    private Button btnSlidePrev;
    private Button btnSlideNext;
    private Button btnDismiss;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        vwSlidePager = findViewById(R.id.view_pager_intro_content);
        vwIcoSlide = findViewById(R.id.view_intro_ico_slide);
        btnSlidePrev = findViewById(R.id.btn_intro_slide_prev);
        btnSlideNext = findViewById(R.id.btn_intro_slide_next);
        btnDismiss = findViewById(R.id.btn_intro_close_intro);

        slideAdapter = new SlideAdapterHelp(this);

        vwSlidePager.setAdapter(slideAdapter);

        addIcoSlideIndicator(0);

        vwSlidePager.addOnPageChangeListener(viewListener);

        //OnClickListeners

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToLogin = new Intent(HelpActivity.this, LoginActivity.class);
                startActivity(goToLogin);

                //Preferences
            }
        });

        btnSlideNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int value = mCurrentPage + 1;

                if( value ==  mIcoSlide.length){
                    Intent goToLogin = new Intent(HelpActivity.this, LoginActivity.class);
                    startActivity(goToLogin);
                }else {
                    vwSlidePager.setCurrentItem(value);
                }


            }
        });

        btnSlidePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vwSlidePager.setCurrentItem(mCurrentPage - 1);

            }
        });
    }

    public void addIcoSlideIndicator(int position){

        mIcoSlide = new TextView[12];
        vwIcoSlide.removeAllViews();

        for (int i = 0; i < mIcoSlide.length; i++){

            mIcoSlide[i] = new TextView(this);
            mIcoSlide[i].setText(Html.fromHtml("&#8226;"));
            mIcoSlide[i].setTextSize(35);
            mIcoSlide[i].setTextColor(getResources().getColor(R.color.transparentWhite));

            vwIcoSlide.addView(mIcoSlide[i]);
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

                btnSlideNext.setEnabled(true);
                btnSlidePrev.setEnabled(false);
                btnSlidePrev.setVisibility(View.INVISIBLE);

                btnSlideNext.setText(R.string.string_help_btn_next);
                btnSlidePrev.setText("");

            } else if (i == mIcoSlide.length -1) {

                btnSlideNext.setEnabled(true);
                btnSlidePrev.setEnabled(true);
                btnSlidePrev.setVisibility(View.VISIBLE);

                btnSlideNext.setText(R.string.string_help_btn_exit);
                btnSlidePrev.setText(R.string.string_help_btn_prev);

            } else {

                btnSlideNext.setEnabled(true);
                btnSlidePrev.setEnabled(true);
                btnSlidePrev.setVisibility(View.VISIBLE);

                btnSlideNext.setText(R.string.string_help_btn_next);
                btnSlidePrev.setText(R.string.string_help_btn_prev);
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}

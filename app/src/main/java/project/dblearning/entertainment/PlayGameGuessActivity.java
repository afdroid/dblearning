package project.dblearning.entertainment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import project.dblearning.R;

public class PlayGameGuessActivity extends AppCompatActivity {

    TextView lblGamePoints;
    TextView lblGameCounter;
    TextView lblGameLifes;
    TextView lblGameCorrect;
    TextView lblGameIncorrect;

    ImageView imgVwGameShow;
    EditText edtTxtGameAnswer;
    Button btnGameConfirm;

    String[] arrayGameOne = {"select","view","where","avg","distinct"};
    String[] arrayGameTwo = {"select","view","where","avg","distinct"};
    String[] arrayGameImages = {"imgconsu1","imgconsu6","imgconsu3","imgconsu4","imgconsu5"};

    int startPints = 0;
    int startLifes = 3;
    int genNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game_guess);

        ActionBar barGames = getSupportActionBar();
        if(barGames!=null){
            barGames.setTitle(R.string.string_game_guess_action_bar_title);
        }

        lblGameCorrect = findViewById(R.id.lbl_game_correct);
        lblGameIncorrect = findViewById(R.id.lbl_game_incorrect);

        lblGamePoints = findViewById(R.id.lbl_game_points);
        lblGameLifes = findViewById(R.id.lbl_game_lifes);
        lblGameCounter = findViewById(R.id.lbl_game_counter);
        imgVwGameShow = findViewById(R.id.img_vw_game_show);
        edtTxtGameAnswer = findViewById(R.id.edt_txt_game_answer);
        btnGameConfirm = findViewById(R.id.btn_game_confirm);

        assign_image(genNum);

        btnGameConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtTxtConfirm = edtTxtGameAnswer.getText().toString().toLowerCase();

                if(edtTxtConfirm.equals(arrayGameOne[genNum])|edtTxtConfirm.equals(arrayGameTwo[genNum])){

                    lblGameCorrect.setVisibility(View.VISIBLE);
                    startPints = startPints + 1;
                    lblGamePoints.setText(getString(R.string.string_game_guess_lbl_points)+ startPints);
                    waitForOne();
                }
                else {
                    lblGameIncorrect.setVisibility(View.VISIBLE);
                    startLifes = startLifes -1;
                    lblGameLifes.setText(getString(R.string.string_game_guess_lbl_lifes)+ startLifes);
                    waitForTwo();
                }
                if(startLifes == 0){
                    gameOver();
                }
            }
        });
    }
    void waitForTwo() {
        new CountDownTimer(2000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnGameConfirm.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                btnGameConfirm.setVisibility(View.VISIBLE);
                lblGameIncorrect.setVisibility(View.INVISIBLE);
                edtTxtGameAnswer.setText("");
                edtTxtGameAnswer.setHint(R.string.string_game_guess_hint_text);
            }
        }.start();
    }
    void waitForOne(){
        new CountDownTimer(4000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                lblGameCounter.setText(""+(millisUntilFinished/1000 +1));
                btnGameConfirm.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                if(genNum >= 4){
                    finish();
                }else{
                    btnGameConfirm.setVisibility(View.VISIBLE);
                    genNum = genNum +1;
                    lblGameCounter.setText("");
                    assign_image(genNum);
                    lblGameCorrect.setVisibility(View.INVISIBLE);
                    edtTxtGameAnswer.setText("");
                    edtTxtGameAnswer.setHint(R.string.string_game_guess_hint_text);
                }

            }
        }.start();
    }
    void assign_image(int num){
        int id = getResources().getIdentifier(arrayGameImages[num],"mipmap",getPackageName());
        imgVwGameShow.setImageResource(id);
    }

    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlayGameGuessActivity.this);
        alertDialogBuilder
                .setMessage(getString(R.string.string_game_guess_alert_message) + startPints)
                .setCancelable(false)
                .setPositiveButton(R.string.string_game_guess_btn_confirm,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), PlayGameGuessActivity.class));
                                finish();
                            }
                        })
                .setNegativeButton(R.string.string_game_guess_btn_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();

                            }
                        });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }
}

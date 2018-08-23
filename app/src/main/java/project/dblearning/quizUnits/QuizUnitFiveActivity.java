package project.dblearning.quizUnits;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import project.dblearning.R;

import java.util.Random;

public class QuizUnitFiveActivity extends AppCompatActivity {

    Button btnQuizAnswerOne;
    Button btnQuizAnswerTwo;
    Button btnQuizAnswerThree;
    Button btnQuizAnswerFour;

    TextView lblQuizScore;
    TextView lblQuizQuestion;

    private QuestionsUnitFive mQuestions;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsLenght;
    private int numQuestion;
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_unit_five);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.string_quiz_five_action_bar_title));
        numQuestion = 0;
        mQuestions = new QuestionsUnitFive();
        mQuestionsLenght = mQuestions.mQuestions.length;

        r = new Random();
        btnQuizAnswerOne = findViewById(R.id.btn_quiz_answer_one);
        btnQuizAnswerTwo = findViewById(R.id.btn_quiz_answer_two);
        btnQuizAnswerThree = findViewById(R.id.btn_quiz_answer_three);
        btnQuizAnswerFour = findViewById(R.id.btn_quiz_answer_four);

        lblQuizScore = findViewById(R.id.lbl_quiz_score);
        lblQuizQuestion = findViewById(R.id.lbl_quiz_question);

        lblQuizScore.setText(getString(R.string.string_quiz_lbl_punctuation) +mScore);

        updateQuestion(r.nextInt(mQuestionsLenght));

        btnQuizAnswerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnQuizAnswerOne.getText() == mAnswer){
                    mScore++;
                    lblQuizScore.setText(getString(R.string.string_quiz_lbl_punctuation) +mScore);
                    updateQuestion(numQuestion);
                    Toast.makeText(QuizUnitFiveActivity.this, R.string.string_quiz_lbl_correct, Toast.LENGTH_SHORT).show();
                } else {
                    updateQuestion(numQuestion);
                    Toast.makeText(QuizUnitFiveActivity.this, R.string.string_quiz_lbl_incorrect, Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnQuizAnswerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnQuizAnswerTwo.getText() == mAnswer){
                    mScore++;
                    lblQuizScore.setText(getString(R.string.string_quiz_lbl_punctuation) +mScore);
                    updateQuestion(numQuestion);
                    Toast.makeText(QuizUnitFiveActivity.this, R.string.string_quiz_lbl_correct, Toast.LENGTH_SHORT).show();

                } else {
                    updateQuestion(numQuestion);
                    Toast.makeText(QuizUnitFiveActivity.this, R.string.string_quiz_lbl_incorrect, Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnQuizAnswerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnQuizAnswerThree.getText() == mAnswer){
                    mScore++;
                    lblQuizScore.setText(getString(R.string.string_quiz_lbl_punctuation) +mScore);
                    updateQuestion(numQuestion);
                    Toast.makeText(QuizUnitFiveActivity.this, R.string.string_quiz_lbl_correct, Toast.LENGTH_SHORT).show();

                } else {
                    updateQuestion(numQuestion);
                    Toast.makeText(QuizUnitFiveActivity.this, R.string.string_quiz_lbl_incorrect, Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnQuizAnswerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnQuizAnswerFour.getText() == mAnswer){
                    mScore++;
                    lblQuizScore.setText(getString(R.string.string_quiz_lbl_punctuation) +mScore);
                    updateQuestion(numQuestion);
                    Toast.makeText(QuizUnitFiveActivity.this, R.string.string_quiz_lbl_correct, Toast.LENGTH_SHORT).show();

                } else {
                    updateQuestion(numQuestion);
                    Toast.makeText(QuizUnitFiveActivity.this, R.string.string_quiz_lbl_incorrect, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void updateQuestion(int num){
        if(num<=8){
            lblQuizQuestion.setText(mQuestions.getQuestion(num));
            btnQuizAnswerOne.setText(mQuestions.getChoiceOne(num));
            btnQuizAnswerTwo.setText(mQuestions.getChoiceTwo(num));
            btnQuizAnswerThree.setText(mQuestions.getChoiceThree(num));
            btnQuizAnswerFour.setText(mQuestions.getChoiceFour(num));

            mAnswer = mQuestions.getCorrectAnswer(num);
            numQuestion++;
        }else {
            gameOver();
        }



    }
    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizUnitFiveActivity.this);
        alertDialogBuilder
                .setMessage(getString(R.string.string_quiz_alert_message_one) + mScore + getString(R.string.string_quiz_alert_message_two))
                .setCancelable(false)
                .setPositiveButton(R.string.string_quiz_alert_btn_accept,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), QuizUnitOneActivity.class));
                                finish();
                            }
                        })
                .setNegativeButton(R.string.string_quiz_alert_btn_cancel,
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

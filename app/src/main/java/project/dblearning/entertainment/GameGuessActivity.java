package project.dblearning.entertainment;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import project.dblearning.R;

public class GameGuessActivity extends AppCompatActivity {

    Button btnPlayGameGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_guess);

        btnPlayGameGuess = findViewById(R.id.btn_play_game_guess);

        ActionBar barGames = getSupportActionBar();
        if(barGames!=null){
            barGames.setTitle(R.string.string_entertainment_action_bar_title);
        }



        btnPlayGameGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToPlayGame= new Intent(GameGuessActivity.this,PlayGameGuessActivity.class);
                startActivity(goToPlayGame);
            }
        });
    }
}

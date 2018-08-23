package project.dblearning.content;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import project.dblearning.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getTitle());
        }

        Toast.makeText(this, getIntent().getExtras().getString("key"),Toast.LENGTH_SHORT).show();

        String key = getIntent().getExtras().getString("key");

        if(key!=null){

        switch (key){

            case "1":
                Intent newIntent = new Intent(ContentActivity.this, UnitsActivity.class);
                startActivity(newIntent);
                break;
            }

        }
    }

}

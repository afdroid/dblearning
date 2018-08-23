package project.dblearning.glossary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import project.dblearning.R;

public class DetailsGlossary extends AppCompatActivity {

    private TextView lblGlossaryConceptsName;
    private TextView lblVwGlossaryInitialLetter;
    private TextView lblVwGlossaryConceptsDesc;
    private Bundle intentGlossary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_glossary);

        lblGlossaryConceptsName = findViewById(R.id.lbl_glossary_concepts_name);
        lblVwGlossaryInitialLetter = findViewById(R.id.lbl_glossary_initial_letter);
        lblVwGlossaryConceptsDesc = findViewById(R.id.txt_vw_glossary_concepts_desc);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intentGlossary = getIntent().getExtras();

        String nameConcept = intentGlossary.getString("name");
        String descConcept = intentGlossary.getString("description");
        char initialLetter = intentGlossary.getChar("initial");
        lblGlossaryConceptsName.setText(nameConcept);
        lblVwGlossaryConceptsDesc.setText(descConcept);
        lblVwGlossaryInitialLetter.setText(String.valueOf(initialLetter));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

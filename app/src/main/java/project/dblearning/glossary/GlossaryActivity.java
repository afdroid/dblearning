package project.dblearning.glossary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import project.dblearning.adapters.AdapterGlossary;
import project.dblearning.objects.CardGlossary;

import project.dblearning.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GlossaryActivity extends AppCompatActivity {

    private RecyclerView recVwGlossary;
    private AdapterGlossary adapterGlossary;
    private ArrayList<CardGlossary> arrayCardsListGlossary = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);

        ActionBar bar = getSupportActionBar();

        if(bar!=null){
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(R.string.string_glossary_action_bar_title);
        }

        if (adapterGlossary == null){
            adapterGlossary = new AdapterGlossary(this, arrayCardsListGlossary);
        }
        recVwGlossary = findViewById(R.id.recycler_view_glossary);
        recVwGlossary.setAdapter(adapterGlossary);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recVwGlossary.setLayoutManager(linearLayoutManager);

        FirebaseDatabase databaseGlossary = FirebaseDatabase.getInstance();
        databaseGlossary.getReference().child("glossary").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayCardsListGlossary.removeAll(arrayCardsListGlossary);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()
                     ) {
                    CardGlossary cardGlossary = snapshot.getValue(CardGlossary.class);
                    arrayCardsListGlossary.add(cardGlossary);
                    
                }
                adapterGlossary.notifyDataSetChanged();

                adapterGlossary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = recVwGlossary.getChildAdapterPosition(v);
                        String name = arrayCardsListGlossary.get(position).getName();
                        String description = arrayCardsListGlossary.get(position).getDescription();
                        char initialLetter = arrayCardsListGlossary.get(position).getName().charAt(0);
                        Intent intent = new Intent(GlossaryActivity.this, DetailsGlossary.class);
                        intent.putExtra("name", name);
                        intent.putExtra("description", description);
                        intent.putExtra("initial", initialLetter);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

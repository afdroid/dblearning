package project.dblearning.content;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import project.dblearning.R;
import project.dblearning.adapters.AdapterContentFragments;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UnitsActivity extends AppCompatActivity implements UnitsFragment.OnFragmentInteractionListener {

    private TabLayout tabLayContent;
    private ViewPager vwPagContent;
    private ArrayList<ContentClass> arrayListContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        initViews();

    }

    private void getDataFromFirebase(){
        arrayListContent = new ArrayList<>();
        Bundle intent = getIntent().getExtras();
        String key = "1";
        if (intent != null){
            key = intent.getString("key");

        }

        FirebaseDatabase.getInstance().getReference().child("content/"+key+"/content").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    ContentClass content = snapshot.getValue(ContentClass.class);
                    arrayListContent.add(content);
                }
                for (int i = 0; i< arrayListContent.size(); i++){
                    tabLayContent.addTab(tabLayContent.newTab());
                }
                setDynamicFragmentToLayout(arrayListContent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setDynamicFragmentToLayout(ArrayList<ContentClass> list) {
        AdapterContentFragments adapterViewPager = new AdapterContentFragments(getSupportFragmentManager(), tabLayContent.getTabCount(),list);
        vwPagContent.setAdapter(adapterViewPager);
        vwPagContent.setCurrentItem(0);
    }

    private void initViews(){
        vwPagContent = findViewById(R.id.vw_pag_content);
        tabLayContent = findViewById(R.id.tab_lay_content);
        vwPagContent.setOffscreenPageLimit(5);
        vwPagContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayContent));
        tabLayContent.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vwPagContent.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        getDataFromFirebase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

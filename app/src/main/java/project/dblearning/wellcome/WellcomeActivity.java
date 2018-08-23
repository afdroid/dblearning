package project.dblearning.wellcome;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import project.dblearning.glossary.GlossaryActivity;
import project.dblearning.R;
import project.dblearning.help.HelpActivity;
import project.dblearning.login.LoginActivity;
import project.dblearning.videos.VideosFragment;
import project.dblearning.fragments.AboutOfFragment;
import project.dblearning.entertainment.EntertainmentFragment;
import project.dblearning.fragments.ExamsFragment;
import project.dblearning.profile.ProfileActivity;
import project.dblearning.fragments.StartFragment;
import project.dblearning.objects.AddUser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WellcomeActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        StartFragment.OnFragmentInteractionListener {

    private TextView lblHeaderUserName;
    private TextView lblHeaderUserEmail;
    private ImageView imgVwHeaderUserPhoto;

    private GoogleApiClient googleApiClient;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private FirebaseUser firebaseUser;

    private NavigationView navVwMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user !=  null){
                    setUserData(user);
                }else{
                    goToLogin();
                }
            }
        };

        firebaseUser = firebaseAuth.getCurrentUser();


         mDrawerLayout =findViewById(R.id.drawer_wellcome_content);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);

        navVwMainMenu = findViewById(R.id.nav_vw_lateral_menu);
        View header = navVwMainMenu.getHeaderView(0);
        lblHeaderUserName = header.findViewById(R.id.lbl_header_user_name);
        lblHeaderUserEmail = header.findViewById(R.id.lbl_header_user_email);
        imgVwHeaderUserPhoto = header.findViewById(R.id.img_vw_header_user_photo);

        getDataUser();

        mToggle.syncState();
        ActionBar bar = getSupportActionBar();
        if(bar!=null){
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(getString(R.string.wellcome_title));
        }

        setupDrawerContent(navVwMainMenu);


        StartFragment startFragment = new StartFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_wellcome_content,startFragment)
                .commit();

    }


    private void setUserData(FirebaseUser user) {

        if (user.getDisplayName()!= null){
            lblHeaderUserName.setText(user.getDisplayName());
        }

        if(user.getPhotoUrl()!=null){
            Glide.with(getBaseContext()).load(user.getPhotoUrl()).into(imgVwHeaderUserPhoto);
        }

        lblHeaderUserEmail.setText(user.getEmail());

    }

    private void getDataUser(){

        FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AddUser addUser = dataSnapshot.getValue(AddUser.class);
                lblHeaderUserName.setText(addUser.getName());
                lblHeaderUserEmail.setText(addUser.getEmail());
                Glide.with(getBaseContext()).load(addUser.getImageUrl()).into(imgVwHeaderUserPhoto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override

    protected void onStart(){
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem){

        Fragment fragmentMainMenu;
        Class fragmentClass = StartFragment.class;

        switch (menuItem.getItemId()){

            case R.id.start:
                fragmentClass = StartFragment.class;
                break;

            case R.id.profile:
                Intent goProfileActivity = new Intent(WellcomeActivity.this, ProfileActivity.class);
                startActivity(goProfileActivity);
                break;

            case R.id.games:
                fragmentClass  = EntertainmentFragment.class;
                ActionBar barGames = getSupportActionBar();
                if(barGames!=null){
                    barGames.setDisplayHomeAsUpEnabled(true);
                    barGames.setTitle(R.string.string_main_menu_entertainment);
                }
                break;

            case R.id.glosary:
                Intent goGlossaryActivity = new Intent(WellcomeActivity.this, GlossaryActivity.class);
                startActivity(goGlossaryActivity);
                break;

            case R.id.exams:
                fragmentClass = ExamsFragment.class;
                ActionBar barExams = getSupportActionBar();
                if(barExams!=null){
                    barExams.setDisplayHomeAsUpEnabled(true);
                    barExams.setTitle(R.string.string_main_menu_exams);
                }
                break;

            case R.id.videos:
                fragmentClass = VideosFragment.class;
                ActionBar barVideos = getSupportActionBar();
                if(barVideos!=null){
                    barVideos.setDisplayHomeAsUpEnabled(true);
                    barVideos.setTitle(R.string.string_main_menu_videos);
                }
                break;

            case R.id.help:
                Intent goToHelp = new Intent(WellcomeActivity.this, HelpActivity.class);
                startActivity(goToHelp);
                break;

            case R.id.about:
                fragmentClass = AboutOfFragment.class;
                ActionBar barAboutOf = getSupportActionBar();
                if(barAboutOf!=null){
                    barAboutOf.setDisplayHomeAsUpEnabled(true);
                    barAboutOf.setTitle(R.string.string_main_menu_about_of);
                }
                break;

            case R.id.logOut:
                logOut();
                default:
                    fragmentClass = null;
        }

        try{
            fragmentMainMenu = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_wellcome_content, fragmentMainMenu).commit();
            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
        }

        catch (Exception e){
            e.printStackTrace();
        }

        mDrawerLayout.closeDrawers();
    }

    public void logOut() {
        firebaseAuth.signOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goToLogin();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.string_alert_can_not_close_session, Toast.LENGTH_SHORT).show();
                }
            }
        });

        finish();
    }

    private void goToLogin(){

        Intent intentLoginActivity = new Intent(WellcomeActivity.this, LoginActivity.class);
        intentLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentLoginActivity);
    }

    private void setupDrawerContent (NavigationView navigationView){

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                selectItemDrawer(item);
                return true;
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
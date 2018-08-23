package project.dblearning.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import project.dblearning.R;
import project.dblearning.wellcome.WellcomeActivity;
import project.dblearning.objects.AddUser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

        private GoogleApiClient googleApiClient;
        private SignInButton btnSignInGoogle;

        private FirebaseAuth firebaseAuth;
        private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

        private EditText edtTxtLoginEmail;
        private EditText edtTxtLoginPass;
        private Button btnNewRegister;
        private Button btnSignIn;
        private ProgressDialog progressDialogSignIn;
        private ProgressBar progressBarSignInGoogle;

        private static final String TAG = "SignInActivity";
        private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();


        btnSignInGoogle = findViewById(R.id.btn_sign_in_google);
        btnSignInGoogle.setSize(SignInButton.SIZE_STANDARD);

        btnSignInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInGoogle = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInGoogle, RC_SIGN_IN);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        edtTxtLoginEmail = findViewById(R.id.edt_txt_login_email);
        edtTxtLoginPass = findViewById(R.id.edt_txt_login_pass);

        btnSignIn = findViewById(R.id.btn_sign_in);
        btnNewRegister = findViewById(R.id.btn_new_register);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTxtLoginEmail.setError(null);
                edtTxtLoginPass.setError(null);

                String email = edtTxtLoginEmail.getText().toString().trim();
                String password  = edtTxtLoginPass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    edtTxtLoginEmail.setError(getString(R.string.string_restriction_message));
                    edtTxtLoginEmail.requestFocus();
                    return;

                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edtTxtLoginEmail.setError(getString(R.string.string_restriction_email));
                    edtTxtLoginEmail.requestFocus();
                    return;

                }else if(TextUtils.isEmpty(password)){
                    edtTxtLoginPass.setError(getString(R.string.string_restriction_message));
                    edtTxtLoginPass.requestFocus();
                    return;


                }else if(password.length() < 7){
                    edtTxtLoginPass.setError(getString(R.string.string_restriction_pass));
                    edtTxtLoginPass.requestFocus();
                    return;

                }else{

                    progressDialogSignIn.setMessage(getString(R.string.string_login_progress_sign_in));
                    progressDialogSignIn.show();

                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){

                                        Toast.makeText(LoginActivity.this,getString(R.string.string_login_wellcome_message),Toast.LENGTH_LONG).show();
                                        Intent isSuccess = new Intent(getApplication(),WellcomeActivity.class);
                                        startActivity(isSuccess);

                                    }else{
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                            Toast.makeText(LoginActivity.this, R.string.string_alert_users_is_not_exist, Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(LoginActivity.this,R.string.string_alert_fatal_error,Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    progressDialogSignIn.dismiss();
                                }
                            });

                }
            }
        });

        btnNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder addNewUser = new AlertDialog.Builder(LoginActivity.this);
                View registerUser = getLayoutInflater().inflate(R.layout.activity_new_register, null);

                final EditText edtTxtRegisterName = registerUser.findViewById(R.id.edt_txt_register_name);
                final EditText edtTxtRegisterEmail = registerUser.findViewById(R.id.edt_txt_register_email);
                final EditText edtTxtRegisterPass = registerUser.findViewById(R.id.edt_txt_register_pass);

                final Button btnRegister = registerUser.findViewById(R.id.btn_register_accept);
                final Button btnCancel = registerUser.findViewById(R.id.btn_register_cancel);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtTxtRegisterName.setError(null);
                        edtTxtRegisterEmail.setError(null);
                        edtTxtRegisterPass.setError(null);

                        String nameNewUser = edtTxtRegisterName.getText().toString().trim();
                        String emailNewUser = edtTxtRegisterEmail.getText().toString().trim();
                        String passwordNewUser  =edtTxtRegisterPass.getText().toString().trim();

                        if(TextUtils.isEmpty(nameNewUser)){
                            edtTxtRegisterName.setError(getString(R.string.string_restriction_message));
                            edtTxtRegisterName.requestFocus();
                            return;

                        }else if(TextUtils.isEmpty(emailNewUser)){
                            edtTxtRegisterEmail.setError(getString(R.string.string_restriction_message));
                            edtTxtRegisterEmail.requestFocus();
                            return;

                        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailNewUser).matches()){
                            edtTxtRegisterEmail.setError(getString(R.string.string_restriction_email));
                            edtTxtRegisterEmail.requestFocus();
                            return;

                        }else if(TextUtils.isEmpty(passwordNewUser)){
                            edtTxtRegisterPass.setError(getString(R.string.string_restriction_message));
                            edtTxtRegisterPass.requestFocus();
                            return;

                        }else if(passwordNewUser.length() < 7){
                            edtTxtRegisterPass.setError(getString(R.string.string_restriction_pass));
                            edtTxtRegisterPass.requestFocus();
                            return;
                        }
                        else{

                            final String name = edtTxtRegisterName.getText().toString().trim();
                            String email = edtTxtRegisterEmail.getText().toString().trim();
                            String password  = edtTxtRegisterPass.getText().toString().trim();

                            progressDialogSignIn.setMessage(getString(R.string.string_alert_process_message));
                            progressDialogSignIn.show();

                            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){

                                                Toast.makeText(LoginActivity.this, R.string.string_alert_successful_message, Toast.LENGTH_LONG).show();
                                                FirebaseUser addUserDB = FirebaseAuth.getInstance().getCurrentUser();
                                                FirebaseDatabase connectionDataBase = FirebaseDatabase.getInstance();
                                                DatabaseReference newReference = connectionDataBase.getReference().child("users").child(addUserDB.getUid());
                                                AddUser addUser = new AddUser();
                                                addUser.setEmail(addUserDB.getEmail());
                                                addUser.setName(name);

                                                newReference.setValue(addUser);
                                                goToMainActivity();

                                            }else{
                                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                                    Toast.makeText(LoginActivity.this, R.string.string_alert_user_already_exists, Toast.LENGTH_LONG).show();
                                                }else{

                                                    Toast.makeText(LoginActivity.this, R.string.string_alert_no_internet_connection,Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            progressDialogSignIn.dismiss();
                                        }
                                    });

                        }
                    }
                });

                addNewUser.setView(registerUser);
                final Dialog dialog = addNewUser.create();
                dialog.show();

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        progressDialogSignIn = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){

                    FirebaseUser addUserDB = FirebaseAuth.getInstance().getCurrentUser();
                    FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
                    DatabaseReference newReference = dataBase.getReference().child("users").child(addUserDB.getUid());
                    AddUser addUser = new AddUser();
                    if(user.getPhotoUrl() != null){
                        addUser.setImageUrl(addUserDB.getPhotoUrl().toString());
                    }
                    addUser.setName(addUserDB.getDisplayName());
                    addUser.setEmail(addUserDB.getEmail());

                    newReference.setValue(addUser);

                    goToMainActivity();
                }
            }
        };
            progressBarSignInGoogle = (ProgressBar) findViewById(R.id.progressBarSignInGoogle);

    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseAuthStateListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthStateListener);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result){

        if(result.isSuccess()){
            firebaseAuthWithGoogle(result.getSignInAccount());

            }else{
            Toast.makeText(this,R.string.string_alert_can_not_sign_in,Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount signInAccount) {

        progressBarSignInGoogle.setVisibility(View.VISIBLE);
        btnSignInGoogle.setVisibility(View.GONE);

        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarSignInGoogle.setVisibility(View.GONE);
                btnSignInGoogle.setVisibility(View.VISIBLE);

                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.string_alert_can_not_fi_b_auth), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToMainActivity() {

        Intent goWellcomeActivity = new Intent(LoginActivity.this, WellcomeActivity.class);
        goWellcomeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(goWellcomeActivity);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed: " + connectionResult);

    }


}
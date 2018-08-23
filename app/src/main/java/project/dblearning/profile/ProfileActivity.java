package project.dblearning.profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import project.dblearning.R;
import project.dblearning.objects.AddUser;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imgVwProfileUserPhoto;
    private TextView lblProfileUserName;
    private TextView lblProfileUserEmail;
    private Button btnProfileEditUserName;
    private ProgressDialog pdUploadingImageProfile;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users");
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    private StorageReference storageImgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupActionBar();

        imgVwProfileUserPhoto = findViewById(R.id.img_vw_profile_user_photo);
        lblProfileUserName = findViewById(R.id.lbl_profile_user_name);
        lblProfileUserEmail = findViewById(R.id.lbl_profile_user_email);
        btnProfileEditUserName = findViewById(R.id.btn_profile_edit_user_name);

        pdUploadingImageProfile = new ProgressDialog(this);

        storageImgProfile = FirebaseStorage.getInstance().getReference();

        getDataUser();

        btnProfileEditUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder editUserName = new AlertDialog.Builder(ProfileActivity.this);
                editUserName.setTitle(R.string.string_profile_alert_title);
                View changeName = getLayoutInflater().inflate(R.layout.item_edit_profile_name, null);

                final EditText editTxtUserName = changeName.findViewById(R.id.edt_txt_profile_user_name);

                final Button btnConfirm = changeName.findViewById(R.id.btn_profile_confirm_changes);
                final Button btnCancel = changeName.findViewById(R.id.btn_profile_cancel_changes);

                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editTxtUserName.setError(null);

                        String newName = editTxtUserName.getText().toString().trim();

                        if(TextUtils.isEmpty(newName)){
                            editTxtUserName.setError(getString(R.string.string_profile_alert_dialog));
                            editTxtUserName.requestFocus();
                            return;
                        }else {

                            Map<String, Object> newUser = new HashMap<>();
                            newUser.put("name", newName);

                            dbRef.child(firebaseUser.getUid()).updateChildren(newUser);

                        }

                    }
                });

                editUserName.setView(changeName);
                final Dialog dialog = editUserName.create();
                dialog.show();

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

        });

    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.string_profile_activity_name);
        }
    }

    private void getDataUser(){

        FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AddUser addUser = dataSnapshot.getValue(AddUser.class);
                lblProfileUserName.setText(addUser.getName());
                lblProfileUserEmail.setText(addUser.getEmail());
                Glide.with(getBaseContext()).load(addUser.getImageUrl()).into(imgVwProfileUserPhoto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void changeImageProfile(View view) {

        updateProfileImage();
    }

    private void updateProfileImage() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i.createChooser(i, getString(R.string.string_profile_select_app)), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){

            pdUploadingImageProfile.setTitle(getString(R.string.string_profile_alert_title_progress));
            pdUploadingImageProfile.setMessage(getString(R.string.string_profile_alert_message));
            pdUploadingImageProfile.setCancelable(false);
            pdUploadingImageProfile.show();

            final Uri path = data.getData();

            StorageReference fileStoragePath = storageImgProfile.child("profileImages").child(firebaseUser.getUid());
            fileStoragePath.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                    pdUploadingImageProfile.dismiss();

                    storageImgProfile.child("profileImages").child(firebaseUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            Glide.with(ProfileActivity.this)
                                    .load(uri.toString())
                                    .into(imgVwProfileUserPhoto);

                            Map<String, Object> newUser = new HashMap<>();
                            newUser.put("imageUrl", uri.toString());

                            dbRef.child(firebaseUser.getUid()).updateChildren(newUser);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            Toast.makeText(ProfileActivity.this, R.string.string_profile_error_message, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Toast.makeText(ProfileActivity.this, R.string.string_profile_success_message, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}

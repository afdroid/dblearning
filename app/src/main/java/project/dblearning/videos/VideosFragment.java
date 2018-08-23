package project.dblearning.videos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import project.dblearning.R;
import project.dblearning.adapters.AdapterVideos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class VideosFragment extends Fragment {

    private RecyclerView recyclerViewVideos;
    private AdapterVideos adapterVideos;
    private ArrayList<VideoClass> listVideos;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("videos");

    public VideosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewVideos = inflater.inflate(R.layout.fragment_videos, container, false);
        recyclerViewVideos = viewVideos.findViewById(R.id.rec_vw_videos);
        listVideos = new ArrayList<>();
        adapterVideos = new AdapterVideos(listVideos);

        recyclerViewVideos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewVideos.setAdapter(adapterVideos);


        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listVideos.removeAll(listVideos);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    VideoClass video = snapshot.getValue(VideoClass.class);
                    listVideos.add(video);
                }

                adapterVideos.notifyDataSetChanged();
                adapterVideos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = recyclerViewVideos.getChildAdapterPosition(v);
                        String urlVideo = listVideos.get(position).getUrlVideo();
                        AlertDialog alertDialog = createMessage(urlVideo);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.setCancelable(true);
                        alertDialog.show();

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return viewVideos;
    }

    private AlertDialog createMessage(String urlVideo){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.item_show_video, null);
        VideoView videoView = v.findViewById(R.id.vid_vw_show_video);
        alertDialog.setView(v);

        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse(urlVideo);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        alertDialog.setPositiveButton(R.string.string_video_alert_positive, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return alertDialog.create();
    }
}

package project.dblearning.entertainment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import project.dblearning.R;



public class EntertainmentFragment extends Fragment {

    public EntertainmentFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_entertainment, container, false);
        Button btnStarGameGuess = view.findViewById(R.id.btn_entertainment_guess_game);
        btnStarGameGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameGuessActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

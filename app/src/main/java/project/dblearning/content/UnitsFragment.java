package project.dblearning.content;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import project.dblearning.R;

public class UnitsFragment extends Fragment {

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_units, container, false);
        initializeView(view);
        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initializeView(View v){
        TextView lblThemesTitle;
        TextView lblThemesContent;
        ImageView imgVwShow;
        lblThemesTitle = v.findViewById(R.id.lbl_themes_title);
        lblThemesContent = v.findViewById(R.id.lbl_themes_content);
        imgVwShow = v.findViewById(R.id.img_vw_themes_show);
        lblThemesTitle.setText(getArguments().getString("title"));
        lblThemesContent.setText(getArguments().getString("content"));
        Glide.with(getActivity()).load(getArguments().getString("urlImg")).into(imgVwShow);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



}

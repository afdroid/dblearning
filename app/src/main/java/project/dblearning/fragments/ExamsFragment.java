package project.dblearning.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import project.dblearning.quizUnits.QuizUnitFiveActivity;
import project.dblearning.quizUnits.QuizUnitFourActivity;
import project.dblearning.quizUnits.QuizUnitOneActivity;
import project.dblearning.R;
import project.dblearning.quizUnits.QuizUnitThreeActivity;
import project.dblearning.quizUnits.QuizUnitTwoActivity;


public class ExamsFragment extends Fragment {

    private Button btnUnitOne;
    private Button btnUnitTwo;
    private Button btnUnitThree;
    private Button btnUnitFour;
    private Button btnUnitFive;

    public ExamsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exams, container, false);
        btnUnitOne = view.findViewById(R.id.btn_quiz_unit_one);
        btnUnitTwo = view.findViewById(R.id.btn_quiz_unit_two);
        btnUnitThree = view.findViewById(R.id.btn_quiz_unit_three);
        btnUnitFour = view.findViewById(R.id.btn_quiz_unit_four);
        btnUnitFive = view.findViewById(R.id.btn_quiz_unit_five);



        btnUnitOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizUnitOneActivity.class);
                getActivity().startActivity(intent);
            }
        });

        btnUnitTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizUnitTwoActivity.class );
                getActivity().startActivity(intent);
            }
        });

        btnUnitThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizUnitThreeActivity.class );
                getActivity().startActivity(intent);
            }
        });

        btnUnitFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizUnitFourActivity.class );
                getActivity().startActivity(intent);
            }
        });

        btnUnitFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizUnitFiveActivity.class );
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

}

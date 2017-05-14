package com.example.ashtech.kilbil;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExtendedFragment extends Fragment {

    private View view;
    private TextView txtContent;


    public ExtendedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_extended, container, false);
        txtContent= (TextView) view.findViewById(R.id.txt_diary_content_detail);
        int id = getArguments().getInt("resId");
        txtContent.setText(getActivity().getResources().getString(id));



        return view;

    }

}

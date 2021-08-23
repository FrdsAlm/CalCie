package com.mfacorp.calcie;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alespero.expandablecardview.ExpandableCardView;


/**
 * A simple {@link Fragment} subclass.
 */
public class help extends Fragment {

    ExpandableCardView card;


    public help() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_help, container, false);


        // Inflate the layout for this fragment
        card=v.findViewById(R.id.basicDetails);






        return v;
    }

}

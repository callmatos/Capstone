package com.callmatos.udacity.capstone.fitness.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callmatos.udacity.capstone.fitness.R;


public class FragmentShowClientInformation extends Fragment {

    public FragmentShowClientInformation() {
    }

    public static FragmentShowClientInformation newInstance() {
        FragmentShowClientInformation fragment = new FragmentShowClientInformation();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_show_client_information, container, false);
    }

}

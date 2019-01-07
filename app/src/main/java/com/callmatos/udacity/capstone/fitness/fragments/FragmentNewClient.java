package com.callmatos.udacity.capstone.fitness.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callmatos.udacity.capstone.fitness.R;

public class FragmentNewClient extends Fragment {

    public FragmentNewClient() {
    }

    public static FragmentNewClient newInstance() {
        FragmentNewClient fragment = new FragmentNewClient();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_new_client, container, false);
    }

}

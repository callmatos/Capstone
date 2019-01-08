package com.callmatos.udacity.capstone.fitness.fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.callmatos.udacity.capstone.fitness.R;
import com.callmatos.udacity.capstone.fitness.Util;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.persistence.ClientViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FragmentShowClientInformation extends Fragment {

    //Main
    private Unbinder unbinder;

    //Static object to recover data by ViewModel
    private ClientViewModel mViewModel;

    //Client selected to show information
    private LiveData<ClientPersonal> clientPersonalSelected;

    @BindView(R.id.detalheUserName)
    public TextView labelUserName;

    @BindView(R.id.detalheGoal)
    public TextView detalheGoal;

    @BindView(R.id.detalheGym)
    public TextView detalheGym;

    @BindView(R.id.detalheTime)
    public TextView detalheTime;

    @BindView(R.id.detalhetotalworkout)
    public TextView detalhetotalworkout;

    public static FragmentShowClientInformation newInstance() {
        return new FragmentShowClientInformation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View viewInflater = inflater.inflate(R.layout.fragment_fragment_show_client_information, container, false);
        unbinder = ButterKnife.bind(this, viewInflater);

        //Get the client selected
        this.clientPersonalSelected = this.mViewModel.getSelected();

        //Insert the information for TextView
        this.labelUserName.setText(this.clientPersonalSelected.getValue().getName());
        this.detalheGoal.setText(this.clientPersonalSelected.getValue().getDetalheGoal());
        this.detalheGym.setText(this.clientPersonalSelected.getValue().getDetalheGym());
        this.detalheTime.setText(Util.getTime(this.clientPersonalSelected.getValue().getHour(),this.clientPersonalSelected.getValue().getMinute()));

        //Implementar AssincTask to get of FireBase
        this.detalhetotalworkout.setText(this.clientPersonalSelected.getValue().getTotalWorkout());

        return viewInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        this.mViewModel = ViewModelProviders.of(getActivity()).get(ClientViewModel.class);
    }
}

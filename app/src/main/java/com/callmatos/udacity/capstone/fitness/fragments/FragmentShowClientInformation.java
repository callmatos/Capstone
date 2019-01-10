package com.callmatos.udacity.capstone.fitness.fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.callmatos.udacity.capstone.fitness.Firebase.FbaseConnection;
import com.callmatos.udacity.capstone.fitness.Firebase.FbaseRepository;
import com.callmatos.udacity.capstone.fitness.Firebase.FbaseViewModel;
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

        this.mViewModel = ViewModelProviders.of(getActivity()).get(ClientViewModel.class);

        //Get the client selected
        this.clientPersonalSelected = this.mViewModel.getSelected();

//        //Insert the information for TextView
        this.labelUserName.setText(this.clientPersonalSelected.getValue().getName());
        this.detalheGoal.setText(this.clientPersonalSelected.getValue().getDetalheGoal());
        this.detalheGym.setText(this.clientPersonalSelected.getValue().getDetalheGym());
        this.detalheTime.setText(Util.getTime(this.clientPersonalSelected.getValue().getHour(),this.clientPersonalSelected.getValue().getMinute()));

        //Implementar AssincTask to get of FireBase
//      this.detalhetotalworkout.setText(FbaseViewModel.);
        FbaseRepository.findIdTest(this.detalhetotalworkout,this.clientPersonalSelected.getValue().getId());



        this.detalhetotalworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int increse = Integer.valueOf(detalhetotalworkout.getText().toString());
                increse++;

                detalhetotalworkout.setText(String.valueOf(increse));
                FbaseRepository.registerClient(clientPersonalSelected.getValue().getId(), increse);

            }
        });

        return viewInflater;
    }
}

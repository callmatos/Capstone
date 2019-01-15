package com.callmatos.udacity.capstone.fitness;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.callmatos.udacity.capstone.fitness.fragments.FragmentShowClientInformation;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.persistence.ClientViewModel;
import com.google.android.gms.common.api.Api;

public class ActivityFitnessDetalhe extends AppCompatActivity {


    private FragmentShowClientInformation fragmentInformationClient;
    private ClientViewModel clientViewModelMainActivity;
    private ClientPersonal selected;

    private static String ITEM_ID = "selected";
    private static String INFClient= "INFClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_detalhe);

        selected = (ClientPersonal) getIntent().getSerializableExtra(ITEM_ID);

        clientViewModelMainActivity = ViewModelProviders.of(this).get(ClientViewModel.class);
        clientViewModelMainActivity.select(selected);

        this.fragmentInformationClient = FragmentShowClientInformation.newInstance();

        //Commit the fragment
        getSupportFragmentManager().beginTransaction().add(R.id.detalheclientFragment,this.fragmentInformationClient,INFClient).commit();
    }

}

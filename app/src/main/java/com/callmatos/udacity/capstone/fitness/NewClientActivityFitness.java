package com.callmatos.udacity.capstone.fitness;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.callmatos.udacity.capstone.fitness.fragments.FragmentNewClient;
import com.callmatos.udacity.capstone.fitness.fragments.FragmentShowClientInformation;

public class NewClientActivityFitness extends AppCompatActivity {

    //Fragment to show
    private FragmentNewClient fragmentNewClient;
    private FragmentShowClientInformation fragmentShowClientInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client_fitness);


        //Before to show the fragment recover the Extra Information
        Bundle bundleAction = getIntent().getExtras();


        if(bundleAction.getBoolean("add")){

            //Fragment
            this.fragmentNewClient = FragmentNewClient.newInstance();

            //Start the fragment inside the Layout.
            getSupportFragmentManager().beginTransaction().add(R.id.inflateFragment,this.fragmentNewClient).commit();

        }else{

            //Fragment Show information user
            this.fragmentShowClientInformation = FragmentShowClientInformation.newInstance();

            //Start the fragment inside the Layout.
            getSupportFragmentManager().beginTransaction().add(R.id.inflateFragment,this.fragmentShowClientInformation).commit();

        }






    }

}

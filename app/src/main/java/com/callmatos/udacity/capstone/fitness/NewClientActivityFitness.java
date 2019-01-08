package com.callmatos.udacity.capstone.fitness;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.callmatos.udacity.capstone.fitness.fragments.ClientListFragment;
import com.callmatos.udacity.capstone.fitness.fragments.FragmentNewClient;
import com.callmatos.udacity.capstone.fitness.fragments.FragmentShowClientInformation;

public class NewClientActivityFitness extends AppCompatActivity implements FragmentNewClient.OnFramentNewClientListener {

    //Fragment to show
    private FragmentNewClient fragmentNewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client_fitness);

        this.fragmentNewClient = (FragmentNewClient) getSupportFragmentManager().findFragmentById(R.id.clientnewFragment);

    }

    /**
     * This method inform to activity thad a new client was created.
     */
    @Override
    public void onClickNew() {
        finish();
    }
}

package com.callmatos.udacity.capstone.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.callmatos.udacity.capstone.fitness.fragments.ClientListFragment;
import com.callmatos.udacity.capstone.fitness.fragments.FragmentNewClient;
import com.callmatos.udacity.capstone.fitness.fragments.FragmentShowClientInformation;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

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

    @Override
    public void onSearchPlace(){

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {

            Intent intent = builder.build(this);
            startActivityForResult(intent, 1);

        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(this, data);

                if (place != null) {

                    this.fragmentNewClient.placeSelect = place;
                    this.fragmentNewClient.gymClinet.setText(place.getName().toString());
                }
            }
        }
    }

}

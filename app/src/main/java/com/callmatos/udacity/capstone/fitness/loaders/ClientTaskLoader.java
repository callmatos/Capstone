package com.callmatos.udacity.capstone.fitness.loaders;

import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.view.View;

import com.callmatos.udacity.capstone.fitness.MainActivityFitness;
import com.callmatos.udacity.capstone.fitness.Util;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.persistence.PersonalDataBase;

import java.io.IOException;
import java.util.List;

public class ClientTaskLoader extends AsyncTaskLoader<List<ClientPersonal>> {

    private static final String TAG = ClientTaskLoader.class.getSimpleName();

    //Data of Recipe
    private List<ClientPersonal> data;

    //Object to reconver data
    PersonalDataBase dataBase;

    // MainActivity.
    private MainActivityFitness context;

    //Constructor with context
    public ClientTaskLoader(MainActivityFitness mainActivity) {

        super(mainActivity);
        this.context = mainActivity;
        this.dataBase = PersonalDataBase.getInstance(this.context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if(data != null){
            deliverResult(data);
        }
    }

    @Nullable
    @Override
    public List<ClientPersonal> loadInBackground() {

        return this.dataBase.clientDAO().getAllClientPersonal();
    }

    @Override
    public void deliverResult(@Nullable List<ClientPersonal> data) {
        this.data = data;
        super.deliverResult(data);
    }
}

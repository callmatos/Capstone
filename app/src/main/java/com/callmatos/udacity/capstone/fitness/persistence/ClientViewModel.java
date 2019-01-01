package com.callmatos.udacity.capstone.fitness.persistence;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;

import java.util.List;


public class ClientViewModel extends AndroidViewModel {

    private static final String TAG = ClientViewModel.class.getSimpleName();

    private LiveData<List<ClientPersonal>> clients;

    public ClientViewModel(@NonNull Application application) {
        super(application);

        PersonalDataBase db = PersonalDataBase.getInstance(this.getApplication());

        Log.v(TAG,"Actively retrieving the clients from the Database");
        clients = db.clientDAO().getAllClientLiveData();
    }

    public LiveData<List<ClientPersonal>> getClients() {
        return clients;
    }
}

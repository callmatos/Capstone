package com.callmatos.udacity.capstone.fitness.persistence;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;

import java.util.List;


public class ClientViewModel extends AndroidViewModel {

    private static final String TAG = ClientViewModel.class.getSimpleName();

    private LiveData<List<ClientPersonal>> clients;

    private final MutableLiveData<ClientPersonal> selected = new MutableLiveData<ClientPersonal>();

    public ClientViewModel(@NonNull Application application) {
        super(application);

        PersonalDataBase db = PersonalDataBase.getInstance(this.getApplication());

        Log.v(TAG,"Actively retrieving the clients from the Database");
        clients = db.clientDAO().getAllClientLiveData();
    }

    public LiveData<List<ClientPersonal>> getClients() {
        return clients;
    }

    public void select(ClientPersonal item) {
        selected.setValue(item);
    }

    public LiveData<ClientPersonal> getSelected() {
        return selected;
    }
}

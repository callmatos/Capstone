package com.callmatos.udacity.capstone.fitness.persistence;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.model.UserGoogle;

import java.util.List;


public class ClientViewModel extends AndroidViewModel {

    private static final String TAG = ClientViewModel.class.getSimpleName();

    private LiveData<List<ClientPersonal>> clients;

    private final MutableLiveData<ClientPersonal> selected = new MutableLiveData<ClientPersonal>();

    private final MutableLiveData<UserGoogle> usergoogleLoggetd = new MutableLiveData<UserGoogle>();

    public ClientViewModel(@NonNull Application application) {
        super(application);

        PersonalDataBase db = PersonalDataBase.getInstance(this.getApplication());
        clients = db.clientDAO().getAllClientLiveData();
    }

    public LiveData<List<ClientPersonal>> getClients() {
        return clients;
    }

    public void select(ClientPersonal item) {
        selected.setValue(item);
    }

    public LiveData<ClientPersonal> getSelected() {
        return this.selected;
    }

    public void usergoogle(UserGoogle user){usergoogleLoggetd.setValue(user);}

    public LiveData<UserGoogle> getUserGoogle(){
        return this.usergoogleLoggetd;
    }
}

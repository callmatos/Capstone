package com.callmatos.udacity.capstone.fitness.Firebase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;

import java.util.List;

public class FbaseViewModel extends AndroidViewModel{

    private FbaseRepository fbaseRepository;

    public FbaseViewModel(@NonNull Application application) {
        super(application);
        fbaseRepository = new FbaseRepository();
    }

    public LiveData<List<ClientPersonal>> getListPersonal(){
        return fbaseRepository.getListClients();
    }

    public LiveData<ClientPersonal> findById(String id){
        return fbaseRepository.findClientById(id);
    }

    public boolean removeClient(String id){
        return fbaseRepository.removeById(id);
    }

    public boolean saveClient(ClientPersonal client){
        return fbaseRepository.saveClientPersonal(client);
    }

}

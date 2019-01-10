package com.callmatos.udacity.capstone.fitness.Firebase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;

import java.util.List;
import java.util.Map;

public class FbaseViewModel extends AndroidViewModel{

    private FbaseRepository fbaseRepository;

    public FbaseViewModel(@NonNull Application application) {
        super(application);
        fbaseRepository = new FbaseRepository();
    }

    public LiveData<Map<Integer,Integer>> getMapTotalWorkoutClient(){
        return fbaseRepository.getListClients();
    }

    public LiveData<Integer> findById(Integer id){
        return fbaseRepository.findClientById(id);
    }


    public boolean saveClient(Integer client,Integer totalworkout){
        return fbaseRepository.registerClient(client,totalworkout);
    }

}

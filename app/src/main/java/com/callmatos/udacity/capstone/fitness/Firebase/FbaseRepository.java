package com.callmatos.udacity.capstone.fitness.Firebase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbaseRepository {

    public boolean saveClientPersonal(ClientPersonal obje){

        Task<Void> result = FbaseConnection.getInstance().child("clients").child(obje.getId().toString()).setValue(obje);
        return result.isSuccessful();
    }

    public LiveData<List<ClientPersonal>> getListClients(){

        final MutableLiveData<List<ClientPersonal>> unitList = new MutableLiveData<>();

        FbaseConnection.getInstance().child("clients").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ClientPersonal> partialUnitList = new ArrayList<>();

                for (DataSnapshot unitChildren : dataSnapshot.getChildren()){

                    if (unitChildren != null)
                        partialUnitList.add((ClientPersonal)unitChildren.getValue());
                }

                unitList.setValue(partialUnitList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return unitList;
    }

    public LiveData<ClientPersonal> findClientById(String id){

        final MutableLiveData<ClientPersonal> unitList = new MutableLiveData<>();

        FbaseConnection.getInstance().child("clients").child(id).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ClientPersonal partialUnitList = null;

                for (DataSnapshot unitChildren : dataSnapshot.getChildren()){

                    if (unitChildren != null)
                        partialUnitList = ((ClientPersonal)unitChildren.getValue());
                }

                unitList.setValue(partialUnitList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return unitList;

    }

    public boolean removeById(String id){

        Task<Void> resuts = FbaseConnection.getInstance().child("clients").child(id).removeValue();
        return resuts.isSuccessful();
    }

}

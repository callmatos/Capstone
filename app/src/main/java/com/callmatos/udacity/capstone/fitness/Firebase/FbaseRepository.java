package com.callmatos.udacity.capstone.fitness.Firebase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FbaseRepository {

    private static String CLIENT="clients";

    public static boolean registerClient(Integer idClient,Integer totalworkout){

        Task<Void> result = FbaseConnection.getInstance().child(CLIENT).child(String.valueOf(idClient)).setValue(totalworkout);
        return result.isSuccessful();
    }

    public LiveData<Map<Integer,Integer>> getListClients(){

        final MutableLiveData<Map<Integer,Integer>> unitList = new MutableLiveData<>();

        FbaseConnection.getInstance().child(CLIENT).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<Integer,Integer> partialUnitList = new HashMap<>();

                for (DataSnapshot unitChildren : dataSnapshot.getChildren()){

                    if (unitChildren != null)
                        partialUnitList.put(Integer.valueOf(unitChildren.getKey()),Integer.valueOf(unitChildren.getValue().toString()));
                }

                unitList.setValue(partialUnitList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return unitList;
    }

    public static LiveData<Integer> findClientById(Integer id){

        final MutableLiveData<Integer> unitList = new MutableLiveData<>();

        FbaseConnection.getInstance().child(CLIENT).child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Integer partialUnitList = dataSnapshot.getValue(Integer.class);

                unitList.setValue(partialUnitList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return unitList;

    }

    public static void findIdTest(final TextView obj, Integer id){

        FbaseConnection.getInstance().child(CLIENT).child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                obj.setText(String.valueOf(dataSnapshot.getValue(Integer.class)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

package com.callmatos.udacity.capstone.fitness.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.google.android.gms.common.api.Api;

import java.util.List;

@Dao
public interface ClientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertClient(ClientPersonal mv);

    @Delete
    public void deleteClient(ClientPersonal mv);

    @Query("DELETE FROM ClientPersonal")
    public void deleteAll();

    @Query("SELECT * from clientpersonal ORDER BY name ASC")
    List<ClientPersonal> getAllClientPersonal();

    @Query("SELECT * from clientpersonal ORDER BY id ASC")
    LiveData<List<ClientPersonal>> getAllClientLiveData();

    @Query("SELECT * from clientpersonal where id = :id")
    ClientPersonal findClientPersonalById(long id);

    @Query("SELECT * from clientpersonal where id = :id")
    LiveData<ClientPersonal> findClientPersonalByIdLiveData(int id);

}

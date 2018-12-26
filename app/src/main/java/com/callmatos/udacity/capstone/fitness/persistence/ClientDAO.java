package com.callmatos.udacity.capstone.fitness.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;

import java.util.List;

@Dao
public interface ClientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertClient(ClientPersonal mv);

    @Delete
    public void deleteClient(ClientPersonal mv);

    @Query("DELETE FROM ClientPersonal")
    public void deleteAll();

//    @Query("SELECT * from movie ORDER BY voteCount ASC")
//    List<Movie> getAllMovies();
//
    @Query("SELECT * from clientpersonal ORDER BY id ASC")
    LiveData<List<ClientPersonal>> getAllClientLiveData();
//
//    @Query("SELECT * from movie where id = :id")
//    Movie findMovieByIdRegistered(int id);
//
//    @Query("SELECT * from movie where uid = :id")
//    Movie findMovieById(int id);
//    @Query("SELECT * from movie where uid = :id")
//    LiveData<Movie> findMovieByIdLiveData(int id);

}

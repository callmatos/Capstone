package com.callmatos.udacity.capstone.fitness.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;


@Database(entities = {ClientPersonal.class}, version = 1,exportSchema = false)
public abstract class PersonalDataBase extends RoomDatabase {

    private static final String LOG_TAG = PersonalDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();

    //Variable with name of database.
    public static final String DATABASE_NAME = "personalfitness";

    //Single object
    private static PersonalDataBase INSTANCE;

    //Object to use the personal
    public abstract ClientDAO clientDAO();

    //AppDataBase with single class to return the reference.
    public static PersonalDataBase getInstance(final Context ct) {

        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(ct.getApplicationContext(),
                        PersonalDataBase.class, DATABASE_NAME)
                        .build();
            }
        }
        return INSTANCE;
    }
}

package com.callmatos.udacity.capstone.fitness.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.persistence.PersonalDataBase;

public class PersonalWidgetService extends IntentService{

    public static final String RECIPE = "recipe";

    //The instent will recive the Recipe to send to widget.
    /**
     * 1. Rule, if it was the first time loader, the first Recipe will show.
     * 2. If the user select one, will start the intent and pass the Recipe selected.
     * 3. Verify if is necessary user a Data base to save the recive, or use a preference to salve and recover last.
     */
    private ClientPersonal clientPersonal;

    //DB to save the new client
    private PersonalDataBase db;

    @Override
    public void onCreate() {
        super.onCreate();

        // Get the instance of DB
        this.db = PersonalDataBase.getInstance(this);
    }

    public PersonalWidgetService() {
        super("PersonalWidgetService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //Recover the Recipe selected by Use or when load the first time.
        Integer idClient = (Integer) intent.getExtras().getSerializable(RECIPE);

        this.clientPersonal = this.db.clientDAO().findClientPersonalById(idClient);

        // Object of Wuidget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, PersonalWidget.class));

        //Now update all widgets
        PersonalWidget.updateRecipeWidgets(getApplicationContext(), appWidgetManager, appWidgetIds, this.clientPersonal);

    }

}

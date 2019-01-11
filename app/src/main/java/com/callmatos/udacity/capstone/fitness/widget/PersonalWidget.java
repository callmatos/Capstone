package com.callmatos.udacity.capstone.fitness.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.callmatos.udacity.capstone.fitness.ActivityFitnessDetalhe;
import com.callmatos.udacity.capstone.fitness.R;
import com.callmatos.udacity.capstone.fitness.Util;
import com.callmatos.udacity.capstone.fitness.fragments.FragmentShowClientInformation;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.persistence.PersonalDataBase;

import java.util.ArrayList;
import java.util.List;


public class PersonalWidget extends AppWidgetProvider {

    public static ClientPersonal clientPersonal;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId,ClientPersonal cl) {

        RemoteViews views = null;
        if (clientPersonal != null) {
            views = new RemoteViews(context.getPackageName(), R.layout.personal_widget);
            views.setTextViewText(R.id.client_name_widget, clientPersonal.getName());
            views.setTextViewText(R.id.client_gym_widget, clientPersonal.getDetalheGym());
            views.setTextViewText(R.id.client_time_widget, Util.getTime(clientPersonal.getHour(),clientPersonal.getMinute()));


        } else {
            views = new RemoteViews(context.getPackageName(), R.layout.personal_widget_empty);
        }

        // Intent to call the Activity with FragmentDetalhe
        Intent it = new Intent(context,ActivityFitnessDetalhe.class);
        it.putExtra(FragmentShowClientInformation.ID_WIDGET,clientPersonal);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, it, 0);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    //Method called to mount the Views.
    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
                                           int[] appWidgetIds, ClientPersonal cl) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, cl);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

//
//    public static void updateWidget(Context context) {
//
//        List<ClientPersonal> clientList = new ArrayList<>();
//
//        clientList = PersonalDataBase.getInstance(context).clientDAO().getAllClientPersonal();
//
//        if (clientList != null && clientList.size() > 0)
//            clientPersonal = clientList.get(0);
//        else
//            clientPersonal = null;
//
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//
//        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, PersonalWidget.class));
//
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
//
//    }

}



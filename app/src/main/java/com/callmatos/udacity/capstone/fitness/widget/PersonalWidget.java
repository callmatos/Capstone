package com.callmatos.udacity.capstone.fitness.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import com.callmatos.udacity.capstone.fitness.R;
import com.callmatos.udacity.capstone.fitness.Util;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.persistence.PersonalDataBase;

import java.util.ArrayList;
import java.util.List;


public class PersonalWidget extends AppWidgetProvider {

    public static ClientPersonal clientPersonal;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {

        RemoteViews views = null;
        if (clientPersonal != null) {
            views = new RemoteViews(context.getPackageName(), R.layout.personal_widget);
            views.setTextViewText(R.id.client_name_widget, clientPersonal.getName());
            views.setTextViewText(R.id.client_gym_widget, clientPersonal.getDetalheGym());
            views.setTextViewText(R.id.client_time_widget, Util.getTime(clientPersonal.getHour(),clientPersonal.getMinute()));


        } else {
            views = new RemoteViews(context.getPackageName(), R.layout.personal_widget_empty);
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    public static void updateWidget(Context context) {

        List<ClientPersonal> clientList = new ArrayList<>();

        clientList = PersonalDataBase.getInstance(context).clientDAO().getAllClientPersonal();

        if (clientList != null && clientList.size() > 0)
            clientPersonal = clientList.get(0);
        else
            clientPersonal = null;

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, PersonalWidget.class));

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

}



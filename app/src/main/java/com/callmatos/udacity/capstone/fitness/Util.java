package com.callmatos.udacity.capstone.fitness;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.callmatos.udacity.capstone.fitness.model.UserGoogle;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;


public class Util {

    private static String SHARED_PREF_KEY = "ownPersonalFitness";
    private static String SHARED_PREF_EMAIL = "ownEmail";
    private static String SHARED_PREF_NAME = "ownName";

    /**
     * Method that checks if there is a valid network connection
     */
    public static boolean checkConnection(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Load image with original resize
     * @param context
     * @param pathImage
     * @param imageView
     */
    public static void loadImageProfile(Context context, String pathImage, ImageView imageView){
        Picasso.get().load(pathImage).into(imageView);
    }

    /**
     * Load image with resize informatioin.
     *
     * @param context
     * @param pathImage
     * @param width
     * @param heiht
     * @param imageView
     */
    public static void loadImageReside(Context context, String pathImage,int width, int heiht, ImageView imageView){
        Picasso.get().load(pathImage).resize(width,heiht).into(imageView);
    }

    /**
     * Method using SharePreferences to save information of OwnPersonal
     * @param ct
     * @param own
     */
    public static void saveOwnPersonalOnSharedPreference(Context ct, UserGoogle own){

        //Get instance of SharePreference by ct
        SharedPreferences sharedPreferences = getSharedPreferences(ct);

        SharedPreferences.Editor edt = sharedPreferences.edit();
        edt.putString(SHARED_PREF_EMAIL,own.getEmail());
        edt.putString(SHARED_PREF_NAME,own.getUsername());

        edt.apply();

    }

    private static SharedPreferences getSharedPreferences(Context ct) {
        return ct.getSharedPreferences(SHARED_PREF_KEY,ct.MODE_PRIVATE);
    }

    /**
     * Method that return TRUE if the user was saved on time.
     *
     * @param ct
     * @return
     */
    public static boolean isOwnPersonalLoaderdFirst(Context ct) {

        SharedPreferences sharedPreferences = getSharedPreferences(ct);
        return sharedPreferences.contains(SHARED_PREF_KEY);
    }

    public static String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(tme);
    }

//    public static Recipe loadRecipeSaved(Context applicationContext) {
//
//        // Recipe to return
//        final Recipe auxRecipe = new Recipe();
//
//        //Object share
//        SharedPreferet nces sh = getSharedPreferences(applicationContext);
//
//        final Set<String> datas = sh.getStringSet("recipe",null);
//
//        //Verifica se ja foi salvo
//        if(datas != null){
//
//
//        }
//
//        return null;
//
//    }
}

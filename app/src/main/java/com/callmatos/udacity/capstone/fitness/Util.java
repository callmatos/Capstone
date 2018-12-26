package com.callmatos.udacity.capstone.fitness;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Util {

    /**
     * Method that checks if there is a valid network connection
     */
    public static boolean checkConnection(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

//    public static void saveRecipeOnSharedPreference(Context ct, Recipe recipeSelect){
//
//        //Get instance of SharePreference by ct
//        SharedPreferences sharedPreferences = getSharedPreferences(ct);
//
//        SharedPreferences.Editor edt = sharedPreferences.edit();
//        edt.putStringSet("recipe",recipeDataToSave(recipeSelect));
//
//        edt.apply();
//
//    }

//    private static SharedPreferences getSharedPreferences(Context ct) {
//        return ct.getSharedPreferences("recipe",ct.MODE_PRIVATE);
//    }
//
//    private static Set<String> recipeDataToSave(Recipe recipeSelect) {
//
//        //String with data to save
//        final Set<String> data = new HashSet<>();
//
//        //Save the Name of Recipe selected on the first possition
//        data.add(recipeSelect.getName());
//
//        for (Ingredient id : recipeSelect.getIngredients()) {
//            data.add(id.getIngredient());
//        }
//        return data;
//    }
//
//    public static boolean isRecipeLoaderdFirst(Context ct) {
//
//        SharedPreferences sharedPreferences = getSharedPreferences(ct);
//
//        return sharedPreferences.contains("recipe");
//    }
//
//    public static Recipe loadRecipeSaved(Context applicationContext) {
//
//        // Recipe to return
//        final Recipe auxRecipe = new Recipe();
//
//        //Object share
//        SharedPreferences sh = getSharedPreferences(applicationContext);
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

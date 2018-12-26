package com.callmatos.udacity.capstone.fitness.loaders;

import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.view.View;

import com.callmatos.udacity.capstone.fitness.MainActivityFitness;
import com.callmatos.udacity.capstone.fitness.Util;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;

import java.io.IOException;
import java.util.List;

public class ClientTaskLoader extends AsyncTaskLoader<List<ClientPersonal>> {

    //Data of Recipe
    private List<ClientPersonal> data;

    // MainActivity.
    private MainActivityFitness context;

    //Constructor with context
    public ClientTaskLoader(MainActivityFitness mainActivity) {
        super(mainActivity);
        this.context = mainActivity;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if(data != null){
            deliverResult(data);
        }else{
//            this.context.recipeFragment.infLoad.setVisibility(View.VISIBLE);
            forceLoad();
        }
    }

    @Nullable
    @Override
    public List<ClientPersonal> loadInBackground() {

//        BakingService service = BakingHttpClient.getClient().create(BakingService.class);
//        Call<List<Recipe>> call = service.getListRecipes();
//
//        //Execute the request
//        try {
//
//            if(Util.checkConnection(context)){
//                this.data = call.execute().body();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return this.data;
    }

    @Override
    public void deliverResult(@Nullable List<ClientPersonal> data) {
        this.data = data;
        super.deliverResult(data);
    }
}

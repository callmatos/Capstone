package com.callmatos.udacity.capstone.fitness.adapter;

import android.arch.persistence.room.util.StringUtil;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.callmatos.udacity.capstone.fitness.Firebase.FbaseRepository;
import com.callmatos.udacity.capstone.fitness.Firebase.FbaseViewModel;
import com.callmatos.udacity.capstone.fitness.R;
import com.callmatos.udacity.capstone.fitness.Util;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.viewholders.ClientViewHolders;

import java.util.List;


public class ClientAdapter extends RecyclerView.Adapter<ClientViewHolders> {

    //List of Recipe
    private List<ClientPersonal> clientPersonalData;

    //Context of application
    private Context context;

    //Listener to recicleview.
    private OnItemClickListener localOnItemClickListener;

    //Default constructor
    public ClientAdapter(Context ct, OnItemClickListener localOnItemClickListener){
        this.context = ct;
        this.localOnItemClickListener = localOnItemClickListener;
    }

    @NonNull
    @Override
    public ClientViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int possition) {

        Context ct = viewGroup.getContext();
        LayoutInflater lInflate = LayoutInflater.from(ct);

        View view = lInflate.inflate(R.layout.client_item_recycleview, viewGroup, false);
        return new ClientViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolders recipeViewHolders, final int possition) {

        //Recipe current
        final ClientPersonal currentClientPersonal = this.clientPersonalData.get(possition);

        //mount the cardview with information of client
        recipeViewHolders.clientName.setText(currentClientPersonal.getName());
        recipeViewHolders.locationInformation.setText(currentClientPersonal.getDetalheGym());
        recipeViewHolders.timeWorkout.setText(Util.getTime(currentClientPersonal.getHour(),currentClientPersonal.getHour()));

        FbaseRepository.findIdTest(recipeViewHolders.totalWorkout,currentClientPersonal.getId());


        //Set the Listener
        recipeViewHolders.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(localOnItemClickListener != null)
                    localOnItemClickListener.onClick(possition,currentClientPersonal);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (this.clientPersonalData == null) return 0;
        return clientPersonalData.size();
    }

    /**
     * This method is used to set the weather forecast on a ForecastAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new ForecastAdapter to display it.
     *
     * @param data The new weather data to be displayed.
     */
    public void updateClientPersonalData(List<ClientPersonal> data) {
        this.clientPersonalData = data;
        notifyDataSetChanged();
    }

    // Listener when the user select on recipe
    public interface OnItemClickListener {
        void onClick(Integer position, ClientPersonal selected);
    }
}

package com.callmatos.udacity.capstone.fitness.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewHolder class to show the LIst of Recipe inside the Fragment.
 *
 */
public class ClientViewHolders extends RecyclerView.ViewHolder {

//    @BindView(R.id.card_baking_item)
//    public CardView cardView;
//
//    @BindView(R.id.card_baking_image)
//    public ImageView cardViewImage;
//
//    @BindView(R.id.card_baking_title)
//    public TextView cardTitle;
//
//    @BindView(R.id.card_baking_servis)
//    public TextView cardServices;

    //Default Constructor
    public ClientViewHolders(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
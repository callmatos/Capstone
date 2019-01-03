package com.callmatos.udacity.capstone.fitness.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callmatos.udacity.capstone.fitness.R;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.model.UserGoogle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ClientListFragment extends Fragment {

    //Main
    private Unbinder unbinder;

    //Static object to single
    private ClientListViewModel mViewModel;

    @BindView(R.id.fab)
    public FloatingActionButton fab;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerViewClient;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    //Listener to MainActivity Fitness
    private OnFragmentInteractionListener mListener;

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.app_bar_main_activity_fitness, container, false);
        unbinder = ButterKnife.bind(this, viewInflater);

        //Configure the RecycleView
        this.recyclerViewClient.setHasFixedSize(true);

        return viewInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ClientListViewModel.class);
        // TODO: Use the ViewModel
    }

    //Method to config the fragment.
    public void configFragmentWithData(UserGoogle googleUser){

        collapsingToolbarLayout.setTitle(googleUser.getUsername());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onClientSelected(ClientPersonal selectClient);
    }

}

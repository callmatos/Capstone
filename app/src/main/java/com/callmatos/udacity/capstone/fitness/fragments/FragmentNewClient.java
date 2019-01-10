package com.callmatos.udacity.capstone.fitness.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.callmatos.udacity.capstone.fitness.Firebase.FbaseViewModel;
import com.callmatos.udacity.capstone.fitness.R;
import com.callmatos.udacity.capstone.fitness.Utils.ThreadExecutors;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.persistence.PersonalDataBase;
import com.google.android.gms.common.api.Api;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentNewClient extends Fragment {

    //Main
    private Unbinder unbinder;

    @BindView(R.id.nameNewClient)
    public EditText clientName;

    @BindView(R.id.infGoal)
    public EditText clientGoal;

    @BindView(R.id.editText2)
    public EditText gymClinet;

//    @BindView(R.id.detalheButtonMap)
//    public ImageView detalheButtonMap;

    @BindView(R.id.timePicker1)
    public TimePicker clientTime;

    @BindView(R.id.fabSave)
    public FloatingActionButton btSave;

    //Interface to report to activity the new client was created.
    private OnFramentNewClientListener mListener;

    //DB to save the new client
    private PersonalDataBase db;

    private FbaseViewModel fbaseData;

    public static FragmentNewClient newInstance() {
        FragmentNewClient fragment = new FragmentNewClient();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View viewInflater = inflater.inflate(R.layout.fragment_fragment_new_client, container, false); 
        unbinder = ButterKnife.bind(this,viewInflater);

        // Get the instance of DB
        this.db = PersonalDataBase.getInstance(getActivity());

        this.fbaseData = ViewModelProviders.of(getActivity()).get(FbaseViewModel.class);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Save new client", Toast.LENGTH_SHORT).show();

                //Save the data on database and inform to activity to close the activity
                ThreadExecutors.getInstance().getDisIO().execute(new Runnable() {

                    @Override
                    public void run() {

                        ClientPersonal test = new ClientPersonal();
                        test.setDetalheGoal(clientGoal.getText().toString());
                        test.setDetalheGym(gymClinet.getText().toString());
                        test.setLocationName("Four Fitness");
                        test.setName(clientName.getText().toString());
                        test.setHour(clientTime.getHour());
                        test.setMinute(clientTime.getMinute());

                        long id = db.clientDAO().insertClient(test);
                        test = db.clientDAO().findClientPersonalById(id);

                        //Save on Firebase the workout of client
                        fbaseData.saveClient(test.getId(),0);

                    }
                });

                //Close the activity
                mListener.onClickNew();
            }
        });
        
        return viewInflater;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFramentNewClientListener){
            mListener = (OnFramentNewClientListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFramentNewClientListener");
        }
    }

    public interface OnFramentNewClientListener {
        void onClickNew();
    }
}

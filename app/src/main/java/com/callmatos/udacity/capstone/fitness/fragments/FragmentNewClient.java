package com.callmatos.udacity.capstone.fitness.fragments;


import android.app.PendingIntent;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.callmatos.udacity.capstone.fitness.widget.PersonalWidgetService;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

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

    //Button to add a new register
    private FbaseViewModel fbaseData;

    // Start the Place to find the place.
    private static final int PLACE_PICKER_REQUEST_TO = 1;

    //Place maps seach
    public Place placeSelect = null;

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

                        if(areFieldsFilled()){

                            ClientPersonal test = new ClientPersonal();

                            test.setDetalheGoal(clientGoal.getText().toString());
                            test.setDetalheGym(placeSelect.getName().toString());
                            test.setLocationName(placeSelect.getAddress().toString());
                            test.setLatitude(placeSelect.getLatLng().latitude);
                            test.setLongitude(placeSelect.getLatLng().longitude);
                            test.setName(clientName.getText().toString());
                            test.setHour(clientTime.getHour());
                            test.setMinute(clientTime.getMinute());

                            long id = db.clientDAO().insertClient(test);
                            test = db.clientDAO().findClientPersonalById(id);

                            //Save on Firebase the workout of client
                            fbaseData.saveClient(test.getId(),0);

                        }
                    }
                });

                //Close the activity
                mListener.onClickNew();
            }
        });
        
        return viewInflater;
    }

    //Call the Widget to update
    public void startIntentServiceWidgetUpdate(ClientPersonal clientPerjsonal){

        // Add the wateringservice click handler
        Intent wateringIntent = new Intent(getActivity(), PersonalWidgetService.class);
        Bundle data = new Bundle();

        data.putSerializable(PersonalWidgetService.RECIPE,clientPerjsonal);

        wateringIntent.putExtras(data);

        PendingIntent.getService(getActivity(), 0, wateringIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        getActivity().startService(wateringIntent);
    }

    /**
     * Open the Maps API to provide to the user an interface to get information regarding a place
     */
    @OnClick({R.id.editText2})
    public void getLocalScreen(View view) {

        this.mListener.onSearchPlace();
    }

    /**
     * Ensure that all fields are filled correctly
     */
    private boolean areFieldsFilled() {
        return (!clientName.getText().toString().equals("") &&
                !clientGoal.getText().toString().equals("")
//                &&
                //!gymClinet.getText().toString().equals("") &&
//                placeSelect != null
        );
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
        void onSearchPlace();
    }

    /**
     * Open the Maps API to provide to the user an interface to get information regarding a place
     */
//    @OnClick({R.id.et_trip_to_where, R.id.et_trip_from_where})
//    public void getLocalScreen(View view) {
//        int requestId = -1;
//
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//        try {
//            Intent intent = builder.build(get);
//            startActivityForResult(intent, requestId);
//        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }
//    }

//    https://github.com/ajmvianna

    /**
     * Ensure that all fields are filled correctly
     */
//    private boolean areFieldsFilled() {
//        return (!edtToWhere.getText().toString().equals("") &&
//                !edtFromWhere.getText().toString().equals("") &&
//                !edtInitialDate.getText().toString().equals("") &&
//                !edtEndDate.getText().toString().equals("") &&
//                !edtBudget.getText().toString().equals("") &&
//                !edtGeneralNotes.getText().toString().equals("") &&
//                placeTo != null &&
//                placeFrom != null);
//    }
}

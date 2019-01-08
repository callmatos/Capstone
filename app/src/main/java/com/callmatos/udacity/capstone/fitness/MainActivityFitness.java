package com.callmatos.udacity.capstone.fitness;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.callmatos.udacity.capstone.fitness.fragments.ClientListFragment;
import com.callmatos.udacity.capstone.fitness.fragments.FragmentShowClientInformation;
import com.callmatos.udacity.capstone.fitness.loaders.ClientTaskLoader;
import com.callmatos.udacity.capstone.fitness.model.ClientPersonal;
import com.callmatos.udacity.capstone.fitness.model.UserGoogle;
import com.callmatos.udacity.capstone.fitness.persistence.ClientViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivityFitness extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ClientListFragment.OnFragmentInteractionListener,
        LoaderManager.LoaderCallbacks<List<ClientPersonal>>{

    private static final String TAG = MainActivityFitness.class.getSimpleName();

    //Used to loader data.
    private static final int CLIENTCAST_LOADER_ID = 0;

    //Main
    private Unbinder unbinder;

    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer;

    @BindView(R.id.nav_view)
    public NavigationView navigationView;

    // The data of user Loggin
    private UserGoogle googleCurrentUser;

    //Image tumber
    public ImageView tumbernairPersonal;
    private TextView namePersonal;
    private TextView emailPersonal;

    //Reference to Fragment with list of client.
    private ClientListFragment clientListFragment;
    private FragmentShowClientInformation showInformationClientFragment;

    //Information saved.
    private Bundle bundleSaved = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fitness);
        ButterKnife.bind(this);

        //ImagePersonalGoogleID
        View header = navigationView.getHeaderView(0);
        this.tumbernairPersonal = (ImageView) header.findViewById(R.id.userTumberImage);
        this.namePersonal = (TextView) header.findViewById(R.id.personalName);
        this.emailPersonal = (TextView) header.findViewById(R.id.emailPersonal);

        //Recover the login done.
        if(getIntent() != null){
            //verify if has the user on Bandle
            if(getIntent().hasExtra(LoginActivityFitness.USERKEY)){
                this.googleCurrentUser = (UserGoogle) getIntent().getParcelableExtra(LoginActivityFitness.USERKEY);
            }else{
                //Recover the UserLogin session
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

                if(account != null){
                    this.googleCurrentUser = new UserGoogle();
                    this.googleCurrentUser.setEmail(account.getEmail());
                    this.googleCurrentUser.setPhotoURL(String.valueOf(account.getPhotoUrl()));
                    this.googleCurrentUser.setUsername(account.getGivenName());
                }else{
                    Intent it = new Intent(this,LoginActivityFitness.class);
                    startActivity(it);
                    this.finish();
                }

            }
        }
        
        //Mount the user data logged.
        mountUserGoogleInformation();

        //Get the Fragment to pass the data to it.
        this.clientListFragment = (ClientListFragment) getSupportFragmentManager().findFragmentById(R.id.clientListFragment);

        //Init the loader to loader the information of client
        getSupportLoaderManager().initLoader(CLIENTCAST_LOADER_ID,bundleSaved, this);

        // View model Providers to called when the database updated.
        ClientViewModel mvm = ViewModelProviders.of(this).get(ClientViewModel.class);
        mvm.getClients().observe(this, new Observer<List<ClientPersonal>>() {

            @Override
            public void onChanged(@Nullable List<ClientPersonal> taskEntries) {
                Log.d(TAG, "Receiving database update from LiveData");
                clientListFragment.updateDataClient(taskEntries);
            }
        });

        //Support to change
        setSupportActionBar(this.clientListFragment.toolbar);

        //Toggle User
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, this.clientListFragment.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Mount Navigation
        navigationView.setNavigationItemSelectedListener(this);

        //Start drawer open.
        drawer.openDrawer(GravityCompat.START);


        // Method is call when the user click the button ADD.
        this.clientListFragment.fButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewClientActivityFitness.class);
                intent.putExtra("user", googleCurrentUser);
                intent.putExtra("add",true);
                startActivity(intent);
            }
        });


    }

    private void mountUserGoogleInformation() {
        Util.loadImageProfile(this, String.valueOf(this.googleCurrentUser.getPhotoURL()), this.tumbernairPersonal);
        this.emailPersonal.setText(this.googleCurrentUser.getEmail());
        this.namePersonal.setText(this.googleCurrentUser.getUsername());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_fitness, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }else if (id == R.id.nav_logout) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut(){
        LoginActivityFitness.getGoogleSignInClient().signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
            }
        });
    }

    @Override
    public void onClientSelected(ClientPersonal selectClient) {



        Snackbar.make(this.clientListFragment.getView(), "Client was selected - Call the fragment FragmentShowClientFormatioin", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();



    }

    @NonNull
    @Override
    public Loader<List<ClientPersonal>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new ClientTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<ClientPersonal>> loader, List<ClientPersonal> clientPersonals) {

        if(clientPersonals != null){
            this.clientListFragment.updateDataClient(clientPersonals);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<ClientPersonal>> loader) {

    }
}

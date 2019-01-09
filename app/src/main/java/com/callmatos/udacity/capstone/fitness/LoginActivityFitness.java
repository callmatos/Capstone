package com.callmatos.udacity.capstone.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.callmatos.udacity.capstone.fitness.model.UserGoogle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivityFitness extends AppCompatActivity {

    //Google client validation
    private static GoogleSignInClient googleClient;

    //Used to callback of function
    private static final int GS_ID = 900;

    //Key
    public static final String USERKEY = "userkey";

    // data
    private static final String UserSignCLIENT = "userSingInClient";

    @BindView(R.id.btn_signin_google)
    public SignInButton singButton;

    @BindString(R.string.usernotfound)
    public String userNotFound;

    @OnClick(R.id.btn_signin_google)
    public void onClick(View view) {
        signIn();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fitness);

        ButterKnife.bind(this);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        googleClient = GoogleSignIn.getClient(this, googleSignInOptions);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GS_ID){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                updateUIGoogle(account);
            } catch (ApiException e) {

                Log.e("teste", String.valueOf(e.getStatusCode()));
                Snackbar.make(singButton, userNotFound, Snackbar.LENGTH_LONG).show();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        updateUIGoogle(account);

    }

    private void signIn() {
        Intent intent = googleClient.getSignInIntent();
        startActivityForResult(intent, GS_ID);
    }

    private void updateUIGoogle(GoogleSignInAccount account) {

        if (account == null) {
            Snackbar.make(singButton, userNotFound, Snackbar.LENGTH_LONG).show();
        } else {
            UserGoogle user = new UserGoogle();
            user.setUsername(account.getGivenName());
            user.setEmail(account.getEmail());
            user.setPhotoURL(String.valueOf(account.getPhotoUrl()));

            //Save the user on SharePreference
            Util.saveOwnPersonalOnSharedPreference(this,user);

            Intent intent = new Intent(this, MainActivityFitness.class);
            intent.putExtra(USERKEY, user);
            startActivity(intent);

            this.finish();
        }
    }

    public static GoogleSignInClient getGoogleSignInClient() {
        return googleClient;
    }


}

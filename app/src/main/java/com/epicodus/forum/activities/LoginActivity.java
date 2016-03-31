package com.epicodus.forum.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.epicodus.forum.ForumApplication;
import com.epicodus.forum.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends FirebaseLoginBaseActivity implements View.OnClickListener{
    @Bind(R.id.button) Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        button.setOnClickListener(this);
        AuthData authData = getFirebaseRef().getAuth();
        if (authData != null) {
            Log.d("Message", authData.getProviderData().get("email").toString());
            Log.d("Message", authData.getProviderData().get("profileImageURL").toString());
        } else {
            Log.d("Message", "logged out");
        }
    }

    @Override
    protected Firebase getFirebaseRef() {
        return ForumApplication.getAppInstance().getFirebaseRef();
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
        Log.d("Facebook error: ", "facebook return an error");
    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
        Log.d("Facebook error: ", "cut users hands");
    }

    @Override
    protected void onStart() {
        super.onStart();
        setEnabledAuthProvider(AuthProviderType.FACEBOOK);
        setEnabledAuthProvider(AuthProviderType.PASSWORD);
    }

    @Override
    public void onFirebaseLoggedIn(AuthData authData) {
        // TODO: Handle successful login
        Log.d("User's UID: ", authData.getUid());
    }

    @Override
    public void onClick(View v) {
        showFirebaseLoginPrompt();
    }
}

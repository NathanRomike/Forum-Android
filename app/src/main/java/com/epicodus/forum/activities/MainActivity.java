package com.epicodus.forum.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.epicodus.forum.ForumApplication;
import com.epicodus.forum.R;
import com.epicodus.forum.adapters.FirebaseCategoryListAdapter;
import com.epicodus.forum.fragments.LogOutFragment;
import com.epicodus.forum.fragments.LoginFragment;
import com.epicodus.forum.util.FirebaseRecyclerAdapter;
import com.epicodus.forum.fragments.AddContentFragment;
import com.epicodus.forum.models.Category;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FirebaseLoginBaseActivity implements View.OnClickListener{
    private Query mQuery;
    private Firebase mFirebaseRef;
    private FirebaseRecyclerAdapter mAdapter;
    private Menu menu;

    @Bind(R.id.view) RecyclerView mRecyclerView;
//    @Bind(R.id.signIn) MenuItem mSignInMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpFirebaseQuery();
        setUpRecyclerView();
    }

    @Override
    public void onResume () {
        super.onResume();
        authValidator();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.category_menu_sign_in, menu);
        this.menu = menu;
        authValidator();
        return true;
    }

    private void authValidator() {
        if (menu != null) {
            MenuItem signOption = menu.findItem(R.id.signIn);
            if (mFirebaseRef.getAuth() == null) {
                signOption.setTitle("sign in");
            } else {
                signOption.setTitle("sign out");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addCategory:
                if (mFirebaseRef.getAuth() != null) {
                    FragmentManager fm = getSupportFragmentManager();
                    Bundle bundle = new Bundle();
                    bundle.putString("childName", "categories");
                    AddContentFragment categoriesFragment = AddContentFragment.newInstance();
                    categoriesFragment.setArguments(bundle);
                    categoriesFragment.show(fm, "fragment_categories");
                } else {
                    FragmentManager fm = getSupportFragmentManager();
                    LoginFragment loginFragment = LoginFragment.newInstance();
                    loginFragment.show(fm, "fragment_login");
                }
                return true;
            case R.id.signIn:
                if (mFirebaseRef.getAuth() == null) {
                    showFirebaseLoginPrompt();
                } else {
                    FragmentManager logOutFRManager = getSupportFragmentManager();
                    Bundle logOutBundle = new Bundle();
                    logOutBundle.putString("email", mFirebaseRef.getAuth().getProviderData().get("email").toString());
                    LogOutFragment logOutFragment = LogOutFragment.newInstance();
                    logOutFragment.setArguments(logOutBundle);
                    logOutFragment.show(logOutFRManager, "fragment_log_out");
                    authValidator();
                }
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpFirebaseQuery() {
        Firebase.setAndroidContext(this);
        mFirebaseRef = ForumApplication.getAppInstance().getFirebaseRef();
        String location = mFirebaseRef.child("categories").toString();
        mQuery = new Firebase(location);

    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseCategoryListAdapter(mQuery, Category.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected Firebase getFirebaseRef() {
        return ForumApplication.getAppInstance().getFirebaseRef();
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
        Log.d("Facebook error: ", "facebook return an error");
        dismissFirebaseLoginPrompt();
    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
        Log.d("Facebook error: ", "cut users hands");
        dismissFirebaseLoginPrompt();
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
        Log.d("User's UID: ", authData.getProviderData().get("profileImageURL").toString());
        authValidator();
    }

    @Override
    public void onClick(View v) {
        ;
    }
}

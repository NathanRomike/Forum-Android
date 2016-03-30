package com.epicodus.forum;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Guest on 3/30/16.
 */
public class ForumApplication extends Application {
    private static ForumApplication app;
    private Firebase mFirebaseRef;

    public static ForumApplication getAppInstance() {
        return app;
    }

    public Firebase getFirebaseRef() {
        return mFirebaseRef;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase(this.getString(R.string.firebase_ref));
    }
}

package com.epicodus.forum.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.forum.ForumApplication;
import com.epicodus.forum.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogOutFragment extends DialogFragment implements View.OnClickListener{
    @Bind(R.id.userEmail) TextView userEmailLabel;
    @Bind(R.id.logOutButton) Button logOutButton;
    public LogOutFragment() {
        // Required empty public constructor
    }

    public static LogOutFragment newInstance() {
        return new LogOutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_out, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        userEmailLabel.setText(bundle.getString("email"));
        logOutButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        ForumApplication.getAppInstance().getFirebaseRef().unauth();
        dismiss();
    }
}

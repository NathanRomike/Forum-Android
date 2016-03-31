package com.epicodus.forum.activities;

import android.content.Intent;
import android.os.Parcel;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.epicodus.forum.ForumApplication;
import com.epicodus.forum.R;
import com.epicodus.forum.adapters.FirebaseMessageListAdapter;
import com.epicodus.forum.adapters.FirebaseTopicListAdapter;
import com.epicodus.forum.fragments.AddContentFragment;
import com.epicodus.forum.models.Message;
import com.epicodus.forum.models.Topic;
import com.epicodus.forum.util.FirebaseRecyclerAdapter;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity {
    private Topic topic;
    private Firebase mFirebaseRef;
    private FirebaseMessageListAdapter mAdapter;
    private Query mQuery;

    @Bind(R.id.messageRecycleView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        topic = Parcels.unwrap(bundle.getParcelable("chosenItem"));
        ButterKnife.bind(this);

        setUpFirebaseQuery();
        setUpRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.message_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addMessage:
                FragmentManager fm = getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString("childName", "messages");
                bundle.putString("topicId", topic.getTopicId());
                AddContentFragment categoriesFragment = AddContentFragment.newInstance();
                categoriesFragment.setArguments(bundle);
                categoriesFragment.show(fm, "fragment_categories");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpFirebaseQuery() {
        Firebase.setAndroidContext(this);
        mFirebaseRef = ForumApplication.getAppInstance().getFirebaseRef();
        String location = mFirebaseRef.child("messages/" + topic.getTopicId()).toString();
        mQuery = new Firebase(location);

    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseMessageListAdapter(mQuery, Message.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}

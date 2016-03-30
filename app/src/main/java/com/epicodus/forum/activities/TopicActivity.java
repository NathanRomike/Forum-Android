package com.epicodus.forum.activities;

import android.content.Intent;
import android.os.Parcel;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.epicodus.forum.R;
import com.epicodus.forum.fragments.AddContentFragment;
import com.epicodus.forum.models.Category;

import org.parceler.Parcels;

public class TopicActivity extends AppCompatActivity {
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        category = Parcels.unwrap(bundle.getParcelable("category"));
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("childName", "topics");
        bundle.putString("categoryId", category.getCategoryId());
        AddContentFragment topicFragment = AddContentFragment.newInstance();
        topicFragment.setArguments(bundle);
        topicFragment.show(fm, "Test String");
        return super.onOptionsItemSelected(item);
    }
}

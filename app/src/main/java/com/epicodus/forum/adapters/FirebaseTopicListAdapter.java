package com.epicodus.forum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.forum.R;
import com.epicodus.forum.models.Category;
import com.epicodus.forum.models.Topic;
import com.epicodus.forum.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;

import java.util.ArrayList;

/**
 * Created by Guest on 3/30/16.
 */
public class FirebaseTopicListAdapter extends FirebaseRecyclerAdapter<ViewHolder, Topic> {

    public FirebaseTopicListAdapter(Query query, Class<Topic> itemClass) {
        super(query, itemClass);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        ArrayList<Object> firebaseItems = new ArrayList<>();

        ArrayList<Topic> topicList = getItems();
        for (int i = 0; i < topicList.size(); i++) {
            firebaseItems.add(topicList.get(i));
        }

        return new ViewHolder(view, firebaseItems);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTopic(getItem(position));
    }

    @Override
    protected void itemAdded(Topic item, String key, int position) {

    }

    @Override
    protected void itemChanged(Topic oldItem, Topic newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Topic item, String key, int position) {

    }

    @Override
    protected void itemMoved(Topic item, String key, int oldPosition, int newPosition) {

    }


}

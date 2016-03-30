package com.epicodus.forum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.forum.R;
import com.epicodus.forum.models.Category;
import com.epicodus.forum.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;

/**
 * Created by Guest on 3/30/16.
 */
public class FirebaseCategoryListAdapter extends FirebaseRecyclerAdapter<ViewHolder, Category> {

    public FirebaseCategoryListAdapter(Query query, Class<Category> itemClass) {
        super(query, itemClass);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);

        return new ViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindCategory(getItem(position));
    }

    @Override
    protected void itemAdded(Category item, String key, int position) {

    }

    @Override
    protected void itemChanged(Category oldItem, Category newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Category item, String key, int position) {

    }

    @Override
    protected void itemMoved(Category item, String key, int oldPosition, int newPosition) {

    }


}

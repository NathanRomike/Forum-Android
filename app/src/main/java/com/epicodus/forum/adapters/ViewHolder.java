package com.epicodus.forum.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.forum.R;
import com.epicodus.forum.activities.MainActivity;
import com.epicodus.forum.activities.TopicActivity;
import com.epicodus.forum.models.Category;
import com.epicodus.forum.models.Topic;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/30/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;
    private ArrayList<Category> categoryArrayList = new ArrayList<>();
    private ArrayList<Topic> topicArrayList = new ArrayList<>();

    @Bind(R.id.categoryNameTextView) TextView mCategoryNameTextView;

    public ViewHolder(View itemView, ArrayList<Object> objects) {
        super(itemView);
        if (objects.size() > 0) {
            if (objects.get(0) instanceof Category) {
                for (int i = 0; i < objects.size(); i++) {
                    categoryArrayList.add((Category) objects.get(i));
                }
            } else {
                for(int i = 0; i < objects.size(); i++) {
                    this.topicArrayList.add((Topic) objects.get(i));
                }
            }
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, TopicActivity.class);
        intent.putExtra("chosenItem", Parcels.wrap(categoryArrayList.get(getLayoutPosition())));
        mContext.startActivity(intent);
    }

    public void bindCategory(Category category) {
        mCategoryNameTextView.setText(category.getCategoryName());
    }

    public void bindTopic(Topic topic) {
        mCategoryNameTextView.setText(topic.getTopicName());
    }


}

package com.epicodus.forum.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.forum.ForumApplication;
import com.epicodus.forum.R;
import com.epicodus.forum.activities.MainActivity;
import com.epicodus.forum.activities.MessageActivity;
import com.epicodus.forum.activities.TopicActivity;
import com.epicodus.forum.models.Category;
import com.epicodus.forum.models.Message;
import com.epicodus.forum.models.Topic;
import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

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
    private ArrayList<Message> messageArrayList = new ArrayList<>();
    private TextView mMessageBodyTextView;
    private TextView mMessageDateTextView;
    private ImageView profileImageView;
    private ImageView trashCan;

    @Bind(R.id.categoryNameTextView) TextView mCategoryNameTextView;

    public ViewHolder(View itemView, ArrayList<Object> objects) {
        super(itemView);
        if (objects.size() > 0) {
            if (objects.get(0) instanceof Category) {
                for (int i = 0; i < objects.size(); i++) {
                    categoryArrayList.add((Category) objects.get(i));
                }
            } else if (objects.get(0) instanceof Topic) {
                for(int i = 0; i < objects.size(); i++) {
                    this.topicArrayList.add((Topic) objects.get(i));
                }
            } else {
                for(int i = 0; i < objects.size(); i++) {
                    this.messageArrayList.add((Message) objects.get(i));
                }
            }
            if (!(itemView.getContext() instanceof MessageActivity)) {
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this);
            } else {
                mMessageBodyTextView = (TextView) itemView.findViewById(R.id.bodyTextView);
                mMessageDateTextView = (TextView) itemView.findViewById(R.id.messageDateText);
                profileImageView = (ImageView) itemView.findViewById(R.id.profileView);
                trashCan = (ImageView) itemView.findViewById(R.id.trashCanImageView);
            }
            mContext = itemView.getContext();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (mContext instanceof MainActivity) {
            intent = new Intent(mContext, TopicActivity.class);
            intent.putExtra("chosenItem", Parcels.wrap(categoryArrayList.get(getLayoutPosition())));
        } else if (mContext instanceof TopicActivity) {
            intent = new Intent(mContext, MessageActivity.class);
            intent.putExtra("chosenItem", Parcels.wrap(topicArrayList.get(getLayoutPosition())));
        } else {
            intent = new Intent(mContext, MessageActivity.class);
        }
        mContext.startActivity(intent);
    }

    public void bindCategory(Category category) {
        mCategoryNameTextView.setText(category.getCategoryName());
    }

    public void bindTopic(Topic topic) {
        mCategoryNameTextView.setText(topic.getTopicName());
    }

    public void bindMessage(final Message message) {
        mMessageBodyTextView.setText(message.getMessage());
        mMessageDateTextView.setText(message.getDateCreated());
        Picasso.with(mContext).load(message.getProfileURL()).resize(50,50).centerCrop().into(profileImageView);
        trashCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase firebase = ForumApplication.getAppInstance().getFirebaseRef();
                String userId = firebase.getAuth().getUid().toString();
                if (messageArrayList.get(getLayoutPosition()).getUserID().equals(userId)){
                    Firebase firebaseRef = new Firebase("https://itforum.firebaseio.com/messages/" + message.getTopicId() + "/" + message.getMessageId());
                    firebaseRef.removeValue();
                } else {
                    Toast.makeText(mContext, "Don't touch! I'm not yours!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

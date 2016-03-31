package com.epicodus.forum.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.forum.ForumApplication;
import com.epicodus.forum.R;
import com.epicodus.forum.models.Category;
import com.epicodus.forum.models.Message;
import com.epicodus.forum.models.Topic;
import com.firebase.client.Firebase;

import java.util.Date;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContentFragment extends DialogFragment implements View.OnClickListener {
    private String childName;
    private String categoryId;
    private String topicId;
    @Bind(R.id.categoryNameEditText) EditText mCategoryNameText;
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.addCategoryButton) Button mAddCategoryButton;

    public AddContentFragment() {
        // Required empty public constructor
    }

    public static AddContentFragment newInstance() {
        return new AddContentFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        childName = bundle.getString("childName");
        categoryId = bundle.getString("categoryId");
        topicId = bundle.getString("topicId");
        mTitleTextView.setText(childName.substring(0,1).toUpperCase() + childName.substring(1) + " Name");
        mAddCategoryButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String userInput = mCategoryNameText.getText().toString();
        saveCategoryToFirebase(userInput);
        dismiss();
    }

    public void saveCategoryToFirebase(String name) {

        Firebase data;
        if (childName.equals("categories")) {
                data = ForumApplication.getAppInstance().getFirebaseRef().child(childName).push();
                Category category = new Category(data.getKey().toString(), name);
                data.setValue(category);
            } else if (childName.equals("topics")) {
                data = ForumApplication.getAppInstance().getFirebaseRef().child(childName+"/"+categoryId).push();
                Topic topic = new Topic(name, categoryId, data.getKey().toString());
                data.setValue(topic);
            } else {
                data = ForumApplication.getAppInstance().getFirebaseRef().child(childName + "/" + topicId).push();
                Message message = new Message(topicId, data.getKey().toString(), "userIdHere", name);
                data.setValue(message);
            }
        }




}

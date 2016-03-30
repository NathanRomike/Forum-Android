package com.epicodus.forum.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.forum.R;
import com.epicodus.forum.activities.MainActivity;
import com.epicodus.forum.models.Category;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/30/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;
    private ArrayList<Category> categoryArrayList;

    @Bind(R.id.categoryNameTextView) TextView mCategoryNameTextView;

    public ViewHolder(View itemView, ArrayList<Category> categories) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        this.categoryArrayList = categories;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    public void bindCategory(Category category) {
        mCategoryNameTextView.setText(category.getCategoryName());
    }


}

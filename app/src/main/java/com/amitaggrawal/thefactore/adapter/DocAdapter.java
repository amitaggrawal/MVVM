package com.amitaggrawal.thefactore.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amitaggrawal.thefactore.model.DocModel;
import com.amitaggrawal.thefactore.R;

import java.util.ArrayList;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public class DocAdapter extends RecyclerView.Adapter<DocAdapter.DocRecyclerViewHolder> {

    private ArrayList<DocModel> mListItems = new ArrayList<>();

    public DocAdapter(Activity context, ArrayList<DocModel> list) {
        this.mListItems = list;
    }

    @NonNull
    @Override
    public DocRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_row,viewGroup,false);
        return new DocRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DocRecyclerViewHolder docRecyclerViewHolder, int i) {
        DocModel modelItem = mListItems.get(i);
        docRecyclerViewHolder.title.setText(modelItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    static class DocRecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public DocRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleView);
        }

    }
}

package com.amitaggrawal.thefactore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amitaggrawal.thefactore.R;
import com.amitaggrawal.thefactore.data.remote.ListMyEntry;

import java.util.List;

/**
 * Created by Amit Aggrawal on 13-01-2019.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {

    private List<ListMyEntry> mData;
    private Context mContext;

    public MyAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_row, viewGroup, false);
        return new MyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder myAdapterViewHolder, int i) {
        ListMyEntry entry = mData.get(i);

        String title = entry.getTitle();
        myAdapterViewHolder.titleTextView.setText(title);

    }

    @Override
    public int getItemCount() {
        if (null == mData)
            return 0;
        else
            return mData.size();
    }


    public void refreshData(List<ListMyEntry> newData) {
        if (mData == null) {
            mData = newData;
            notifyDataSetChanged();
        } else {
            mData = newData;
            notifyDataSetChanged();
        }
    }


    class MyAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;

        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleView);
        }

    }
}

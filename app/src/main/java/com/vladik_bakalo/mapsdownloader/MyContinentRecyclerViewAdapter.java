package com.vladik_bakalo.mapsdownloader;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vladik_bakalo.mapsdownloader.dummy.Region;

import java.util.List;
import java.util.Locale;

/**
 * Created by Владислав on 12.05.2017.
 */

public class MyContinentRecyclerViewAdapter extends RecyclerView.Adapter<MyContinentRecyclerViewAdapter.ViewHolder> {

    public List<Region> mValues;
    private final ContinentFragment.OnContinentFragmentListener mListener;

    public MyContinentRecyclerViewAdapter(List<Region> items, ContinentFragment.OnContinentFragmentListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public MyContinentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_continent, parent, false);
        return new MyContinentRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyContinentRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContinentNameView.setText(mValues.get(position).getName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onContinentFragment(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContinentNameView;
        public Region mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContinentNameView = (TextView) view.findViewById(R.id.textContinentName);
        }
    }
}

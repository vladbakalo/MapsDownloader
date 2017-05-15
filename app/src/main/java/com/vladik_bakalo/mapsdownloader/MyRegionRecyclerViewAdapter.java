package com.vladik_bakalo.mapsdownloader;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vladik_bakalo.mapsdownloader.dummy.Region;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Region} and makes a call to the
 * specified {@link RegionFragment.OnRegionFragmentListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyRegionRecyclerViewAdapter extends RecyclerView.Adapter<MyRegionRecyclerViewAdapter.ViewHolder> {

    private final List<Region> mValues;
    private final RegionFragment.OnRegionFragmentListener mListener;
    Context context;

    public MyRegionRecyclerViewAdapter(List<Region> items, RegionFragment.OnRegionFragmentListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_region, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.mTextRegion.setText(mValues.get(position).getName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onRegionFragment(holder.mItem);
                }
            }
        });
        if (mValues.get(position).isDownloaded())
            holder.mImageViewRegion.setColorFilter(ContextCompat.getColor(context, R.color.colorDownloadedMap));
        if (!mValues.get(position).isHasMapForDownload())
            holder.mImageDownloadRegion.setVisibility(View.GONE);
        holder.mImageDownloadRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener)
                    mListener.onRerionClickDownloadOrCancel(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTextRegion;
        public final ImageView mImageViewRegion;
        public final ImageView mImageDownloadRegion;
        public Region mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageDownloadRegion = (ImageView)view.findViewById(R.id.imageDownload);
            mImageViewRegion = (ImageView)view.findViewById(R.id.imageViewRegion);
            mTextRegion = (TextView)view.findViewById(R.id.textRegionName);
        }
    }
}

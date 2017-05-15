package com.vladik_bakalo.mapsdownloader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladik_bakalo.mapsdownloader.dummy.Region;
import com.vladik_bakalo.mapsdownloader.dummy.RegionHelper;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnRegionFragmentListener}
 * interface.
 */
public class RegionFragment extends Fragment {

    private OnRegionFragmentListener mListener;
    private Region region;
    private ActionBar actionBar;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RegionFragment() {
    }

    public static RegionFragment newInstance(Region region) {
        RegionFragment fragment = new RegionFragment();
        fragment.setRegion(region);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_region_list, container, false);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
            recyclerView.setAdapter(new MyRegionRecyclerViewAdapter(region.getRegionsList(), mListener));
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        actionBar.setTitle(region.getName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegionFragmentListener) {
            mListener = (OnRegionFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegionFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public interface OnRegionFragmentListener {
        void onRegionFragment(Region item);
        void onRerionClickDownloadOrCancel(Region item);
    }
}

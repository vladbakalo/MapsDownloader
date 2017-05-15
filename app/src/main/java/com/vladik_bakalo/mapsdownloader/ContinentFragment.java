package com.vladik_bakalo.mapsdownloader;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladik_bakalo.mapsdownloader.dummy.Region;
import com.vladik_bakalo.mapsdownloader.rest.NetworkHelper;
import com.vladik_bakalo.mapsdownloader.rest.RegionXmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.vladik_bakalo.mapsdownloader.rest.NetworkHelper.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContinentFragment extends Fragment {





    private OnContinentFragmentListener mListener;
    private MyContinentRecyclerViewAdapter adapter;

    public ContinentFragment() {
    }
    @SuppressWarnings("unused")
    public static RegionFragment newInstance() {
        RegionFragment fragment = new RegionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_continent_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new MyContinentRecyclerViewAdapter(new ArrayList<Region>(), mListener);
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new DownloadXmlTask().execute(BASE_URL);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContinentFragmentListener) {
            mListener = (OnContinentFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnContinentFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    private class DownloadXmlTask extends AsyncTask<String, Void, List<Region>> {
        RegionXmlParser regionXmlParser;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            regionXmlParser = new RegionXmlParser();
        }

        @Override
        protected List<Region> doInBackground(String... urls) {
            try {
                return regionXmlParser.parse(NetworkHelper.downloadUrl(BASE_URL));
            } catch (IOException e) {

            } catch (XmlPullParserException e) {
                String i = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Region> result) {
            adapter.mValues = result;
            adapter.notifyDataSetChanged();
        }
    }
    public interface OnContinentFragmentListener {
        void onContinentFragment(Region item);
    }

}

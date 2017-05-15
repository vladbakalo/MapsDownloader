package com.vladik_bakalo.mapsdownloader;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vladik_bakalo.mapsdownloader.dummy.Region;
import com.vladik_bakalo.mapsdownloader.dummy.RegionHelper;
import com.vladik_bakalo.mapsdownloader.storage.StorageHelper;

public class MainActivity extends AppCompatActivity implements ContinentFragment.OnContinentFragmentListener {

    ProgressBar progressBar;
    TextView textViewSpace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBarMemoryUsage);
        textViewSpace = (TextView) findViewById(R.id.textViewSpace);
        setSpaceAvailableInformation();

    }
    private void setSpaceAvailableInformation()
    {
        StorageHelper storageHelper = new StorageHelper(Environment.getExternalStorageDirectory().getPath());
        Long i1 = storageHelper.getTotalMemorySpace() / 100;
        int i2 = StorageHelper.safeLongToInt(i1);
        Long j1 = storageHelper.getUsedMemorySpace() / 100;
        int j2 = StorageHelper.safeLongToInt(j1);
        progressBar.setMax(i2);
        progressBar.setProgress(j2);
        String freeSpace = storageHelper.humanReadableByteCount(storageHelper.getAvailableMemorySpace(), true);
        textViewSpace.setText(freeSpace);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

    }

    @Override
    public void onContinentFragment(Region item) {
        if (item.getRegionsList().size() != 0)
        {
            RegionHelper.getInstance().setContinent(item);
            startActivity(new Intent(getApplicationContext(), RegionMainActivity.class));

        }
    }
}

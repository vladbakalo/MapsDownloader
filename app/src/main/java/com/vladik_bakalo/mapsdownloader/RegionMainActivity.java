package com.vladik_bakalo.mapsdownloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.vladik_bakalo.mapsdownloader.dummy.Region;
import com.vladik_bakalo.mapsdownloader.dummy.RegionHelper;

public class RegionMainActivity extends AppCompatActivity implements RegionFragment.OnRegionFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRegion);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerRegion, RegionFragment.newInstance(RegionHelper.getInstance().getContinent()))
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onRegionFragment(Region item) {
        if (item.getRegionsList().size() != 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                            R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.containerRegion, RegionFragment.newInstance(item))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onRerionClickDownloadOrCancel(Region item) {
        Toast.makeText(this, "Download", Toast.LENGTH_SHORT).show();
    }
}

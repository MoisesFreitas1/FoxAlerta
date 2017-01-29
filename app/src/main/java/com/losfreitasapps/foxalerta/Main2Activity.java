package com.losfreitasapps.foxalerta;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager FM;

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //UnityAds.init(this, "1074656", new UnityAdsListener());

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6303877676651382/8304392558");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();

        FM = getFragmentManager();
        FM.beginTransaction().replace(R.id.content_principal, new instrucoes()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        UnityAds.changeActivity(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_instrucoes) {
            FM.beginTransaction().replace(R.id.content_principal, new instrucoes()).commit();
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            //if ( UnityAds.canShow() )
            //{
              //  UnityAds.show();
            //}
        } else if (id == R.id.nav_comprar) {
            FM.beginTransaction().replace(R.id.content_principal, new compra()).commit();
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            //if ( UnityAds.canShow() )
            //{
            //  UnityAds.show();
            //}
        } else if (id == R.id.nav_vender) {
            FM.beginTransaction().replace(R.id.content_principal, new venda()).commit();
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            //if ( UnityAds.canShow() )
            //{
            //  UnityAds.show();
            //}
        } else if (id == R.id.nav_mb) {
            Uri uri = Uri.parse("https://foxbit.exchange/#trading");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

class UnityAdsListener implements IUnityAdsListener {

    @Override
    public void onHide() {

    }

    @Override
    public void onShow() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoCompleted(String s, boolean b) {

    }

    @Override
    public void onFetchCompleted() {
        if(UnityAds.canShow()) {
            UnityAds.show();
        }
    }

    @Override
    public void onFetchFailed() {

    }
}


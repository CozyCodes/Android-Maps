package com.thul.map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.thul.map.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener ,LocationListener {

    private GoogleMap mMap; 
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest mLocationRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        
                .setFastestInterval(1000); 
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }


    private void setUpMapIfNeeded() {
        
        if (mMap == null) {
          
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
          
            if (mMap != null) {
                setUpMap();
                mMap.setMyLocationEnabled(true);
            }

        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());     

    }
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        LatLng latLng = new LatLng(13.111889, 80.184343);
        //LatLng latLng = new LatLng(currentLatitude, currentLongitude);
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("Korattur");
        mMap.addMarker(options);
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.111889, 80.184343)).title("DCB Bank ATM").snippet("KORATTUR"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.111889,80.184320)).title("Canara Bank Atm").snippet("KORATTUR")); 
 	   mMap.addMarker(new MarkerOptions().position(new LatLng(13.111894, 80.184549)).title("Mary Coffee").snippet("KORATTUR")); 
 	mMap.addMarker(new MarkerOptions().position(new LatLng(13.111454, 80.183970)).title("Axis Bank Atm").snippet("KORATTUR"));
 	    mMap.addMarker(new MarkerOptions().position(new LatLng(13.110241, 80.184175)).title("More Supermarket").snippet("KORATTUR")); 
 	mMap.addMarker(new MarkerOptions().position(new LatLng(13.109835, 80.184144)).title("Veni Supermarket").snippet("KORATTUR")); 
 	   mMap.addMarker(new MarkerOptions().position(new LatLng(13.109800, 80.183673)).title("ICICI Atm").snippet("KORATTUR")); 
 	mMap.addMarker(new MarkerOptions().position(new LatLng(13.109262, 80.183143)).title("Bhaktavatsalam Vidhyashram").snippet("KORATTUR"));
 	    mMap.addMarker(new MarkerOptions().position(new LatLng(13.108875,80.183602)).title("Mummy Daddy").snippet("KORATTUR")); 
 	mMap.addMarker(new MarkerOptions().position(new LatLng(13.108115,80.185410)).title("New Saravana's").snippet("KORATTUR")); 
 	   mMap.addMarker(new MarkerOptions().position(new LatLng(13.108065, 80.186253)).title("Tnsc Bank").snippet("KORATTUR")); 
 	mMap.addMarker(new MarkerOptions().position(new LatLng(13.107564, 80.186715)).title("Carrie Cakes").snippet("KORATTUR"));
 	    mMap.addMarker(new MarkerOptions().position(new LatLng(13.106996, 80.186680)).title("Kotak Mahindra Bank Atm").snippet("KORATTUR")); 
 	mMap.addMarker(new MarkerOptions().position(new LatLng(13.106444, 80.18661)).title("Green Trends Saloon").snippet("KORATTUR")); 
 	   mMap.addMarker(new MarkerOptions().position(new LatLng(13.105768, 80.186535)).title("Ongara Ashram").snippet("KORATTUR")); 
 	mMap.addMarker(new MarkerOptions().position(new LatLng(13.103314, 80.186418)).title("Domino's Pizza").snippet("KORATTUR"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
               
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
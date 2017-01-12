package test_app.pro.mylocation.mylocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class G_Maps_Main_Activity extends FragmentActivity implements LocationListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener , View.OnClickListener{

    GoogleMap googleMap;
    Location location;
    Button btclick;
    TextView locationTv;
    double latitude ;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_maps_act);

        btclick=(Button)findViewById(R.id.Btclick);
        //show error dialog if GoolglePlayServices not available


        if (!isGooglePlayServicesAvailable()) {
            finish();
        } else {



            SupportMapFragment supportMapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);

            googleMap = supportMapFragment.getMap();

                googleMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, true);

            Log.e("bestProvider", bestProvider);

            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {




                return;
            } else {

                location = locationManager.getLastKnownLocation(bestProvider);

                //   Log.e("Location", location.toString());

                if (location != null) {

                    onLocationChanged(location);

                }


                locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);

            }

        }

        btclick.setOnClickListener(this);

    }


    @Override
    public void onLocationChanged(Location location) {
        locationTv = (TextView) findViewById(R.id.latlongLocation);

            latitude = location.getLatitude();
         longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);

        Log.i("Latitude", "" + latitude);
        Log.i("Lognitude", "" + longitude);

        googleMap.addMarker(new MarkerOptions().position(latLng).title("Current Position").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        locationTv.setText(" Latitude:" + latitude + "\n Longitude:" + longitude);

    }


    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onClick(View view) {

        Intent i= new Intent(getApplicationContext(),NameandDescription.class);
        i.putExtra("my_name", String.valueOf(latitude));
        i.putExtra("my_name1", String.valueOf(longitude));
        Log.i("Latitude_intent", "" + latitude);
        Log.i("Lognitude_intent", "" + longitude);

        startActivity(i);
        finish();



    }

}

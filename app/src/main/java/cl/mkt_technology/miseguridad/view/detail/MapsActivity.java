package cl.mkt_technology.miseguridad.view.detail;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cl.mkt_technology.miseguridad.R;

import static java.lang.Long.parseLong;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    String longitud, latitud;
    double longi, lati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        longitud = getIntent().getStringExtra("longitud");
        latitud = getIntent().getStringExtra("latitud");

        Log.d("VALORES" , "el valor de long es " + longitud);
        longi = Double.parseDouble(longitud);
        lati = Double.parseDouble(latitud);


        Log.d("VALORES" , "el valor de long es " + longi);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker
        LatLng miUbicacion = new LatLng(lati, longi);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(miUbicacion, 16);
        if (marcador != null) {
            marcador.remove();}
            marcador = mMap.addMarker(new MarkerOptions()
                    .position(miUbicacion)
                    .title("Alerta!"));
            mMap.animateCamera(cameraUpdate);
    }
}

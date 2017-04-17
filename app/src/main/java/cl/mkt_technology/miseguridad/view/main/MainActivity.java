package cl.mkt_technology.miseguridad.view.main;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cl.mkt_technology.miseguridad.R;
import cl.mkt_technology.miseguridad.adapter.AlertAdapter;
import cl.mkt_technology.miseguridad.alert.SendAlert;
import cl.mkt_technology.miseguridad.alert.Validate;


public class MainActivity extends AppCompatActivity implements LocationListener, Validate{

    public LocationManager locationManager;
    String provider;
    TextView longitud, direcciontv, latitud, status;
    Button alertaBoton;
    public static final int MY_PERMISSIONS = 333;
    private AlertAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new UploadUser().toFirebase();
        //subimos el usuario logeado a firebase

        latitud = (TextView) findViewById(R.id.latitudTv);
        longitud = (TextView) findViewById(R.id.longitudTv);
        direcciontv = (TextView) findViewById(R.id.direccionTv);
        alertaBoton = (Button) findViewById(R.id.alertaBtn);
        status = (TextView) findViewById(R.id.statusTv);

        permission();
        IniciarServicio();
        positionGps();
        statusGps();


        // int permissionCheck = ContextCompat.checkSelfPermission(this,
        // Manifest.permission.ACCESS_FINE_LOCATION);

        alertaBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adress = direcciontv.getText().toString();
                String lat = latitud.getText().toString();
                String lon = longitud.getText().toString();

                new SendAlert(MainActivity.this).enviarAlert(adress,lat,lon);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.alertasRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new AlertAdapter();
        recyclerView.setAdapter(adapter);
    }



    public void statusGps(){
        if (direcciontv.getText().equals("Direccion")){
            status.setBackgroundColor(Color.RED);
        }else{
            status.setBackgroundColor(Color.GREEN);
        }
    }

    public void permission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS);


            }
        }
    }

    public void IniciarServicio() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locationManager.getBestProvider(criteria, true);
        //latitud.setText(provider);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(provider, 10000, 1, this);

    }

    public void positionGps() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location==null){
            Log.d("LOCATION", "localizacion desconocida");
        }else{
            latitud.setText(String.valueOf(location.getLatitude()));
            longitud.setText(String.valueOf(location.getLongitude()));
        }

        setDireccion(location);

    }

    public void setDireccion(Location loc){
        if (loc!=null){
            if (loc.getLatitude()!=0 && loc.getLongitude()!=0){
                try{
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    List<Address> list = geocoder.getFromLocation(loc.getLatitude(),loc.getLongitude(),1);
                        if (!list.isEmpty()){
                            Address direccion = list.get(0);
                            direcciontv.setText(direccion.getAddressLine(0));
                            status.setBackgroundColor(Color.GREEN);
                        }
                }catch (IOException e){
                    direcciontv.setText("error: "+e);
                }
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location==null){
            Log.d("LOCATION", "localizacion desconocida");
        }else{
            latitud.setText(String.valueOf(location.getLatitude()));
            longitud.setText(String.valueOf(location.getLongitude()));
        }

        setDireccion(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void success() {
        TastyToast.makeText(this,"Haz creado una alerta", Toast.LENGTH_SHORT,TastyToast.SUCCESS);
    }

    @Override
    public void error() {
        TastyToast.makeText(this,"La alerta no fue enviada", Toast.LENGTH_SHORT,TastyToast.SUCCESS);
    }
}

package ru.ivan.parentcontroller;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng child = new LatLng(53.9073501, 46.3890642);

        int height = 170;
        int width = 170;
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_name);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        mMap.addMarker(new MarkerOptions().position(child).title("Это ваш ребенок").snippet("находится здесь").icon(smallMarkerIcon));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(child, 18.0f));


        double X = 53.9073501;
        double _X = 53.9073501;
        double Y = 46.3890642;
        double _Y = 46.3890642;


        Random r = new Random();
        PolylineOptions line = new PolylineOptions().add(new LatLng(X, Y)).width(7).color(Color.BLUE);
//        mMap.addPolyline(line);

        int size = 500000;

        for (int i = 0; i < size; i++) {
            _X = X;
            _Y = Y;
            X += r.nextDouble() * 0.0001;
            Y += r.nextDouble() * 0.0001;


           // int color = Color.rgb(i * 255 / size, 0, 255 - i * 255 / size);

//            PolylineOptions l = new PolylineOptions();
//            CircleOptions c = new CircleOptions().center(new LatLng(X, Y)).fillColor(color).radius(0.5).strokeWidth(0);

            line.add(new LatLng(X, Y));
//            l.add(new LatLng(_X, _Y)).add(new LatLng(X, Y)).color(color).width(7);
//            mMap.addPolyline(l);
//            mMap.addCircle(c);
        }
        mMap.addPolyline(line);


    }
}

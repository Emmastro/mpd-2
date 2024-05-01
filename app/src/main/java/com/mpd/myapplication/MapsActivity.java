package com.mpd.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mpd.myapplication.databinding.ActivityMapsBinding;
import com.mpd.myapplication.weatherLocation.WeatherLocationContentNoAdapter;


import java.util.List;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private WeatherLocationContentNoAdapter weatherLocationContent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityMapsBinding.inflate(inflater, container, false);
        weatherLocationContent = new WeatherLocationContentNoAdapter();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;  // Use default InfoWindow frame
            }

            @Override
            public View getInfoContents(Marker marker) {
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);

                TextView title = infoWindow.findViewById(R.id.title);
                TextView snippet = infoWindow.findViewById(R.id.snippet);

                title.setText(marker.getTitle());
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });


        try {
            weatherLocationContent.loadWeatherData(getActivity().getApplicationContext(), this::updateMapMarkers);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateMapMarkers(List<WeatherLocationItem> weatherLocationItems) {
        for (WeatherLocationItem item : weatherLocationItems) {
            LatLng location = new LatLng(item.latitude, item.longitude);

            mMap.addMarker(new MarkerOptions().position(location).title(item.getLocation()).snippet(item.getFormatedAll()));
        }
    }
}

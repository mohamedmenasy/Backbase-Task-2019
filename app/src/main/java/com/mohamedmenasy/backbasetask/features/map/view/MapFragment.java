package com.mohamedmenasy.backbasetask.features.map.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mohamedmenasy.backbasetask.R;


public class MapFragment extends Fragment {
    private static final String ARG_LATLNG = "LatLng";
    private static final String ARG_NAME = "name";

    private LatLng mLocation;
    private String name;

    private MapView mapView;
    private GoogleMap map;

    public MapFragment() {
    }

    public static MapFragment newInstance(String name, LatLng mLocation) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_LATLNG, mLocation);
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLocation = getArguments().getParcelable(ARG_LATLNG);
            name = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setTitle(name);
        }

        mapView = v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(googleMap -> {
            map = googleMap;
            map.getUiSettings().setMyLocationButtonEnabled(false);
            MapsInitializer.initialize(getActivity());
            setMarker(name, mLocation);
        });

        return v;
    }

    private void setMarker(String markerName, LatLng location) {
        if (mLocation != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 10);
            map.animateCamera(cameraUpdate);
            map.addMarker(new MarkerOptions()
                    .position(location)
                    .title(markerName));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mActionBar.setDisplayHomeAsUpEnabled(false);
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setTitle(R.string.app_name);

        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

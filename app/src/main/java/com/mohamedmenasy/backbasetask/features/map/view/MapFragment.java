package com.mohamedmenasy.backbasetask.features.map.view;

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
import com.mohamedmenasy.backbasetask.R;


public class MapFragment extends Fragment {
    private static final String ARG_LATLNG = "LatLng";

    private LatLng mLocation;

    private MapView mapView;
    private GoogleMap map;

    public MapFragment() {
    }

    public static MapFragment newInstance(LatLng mLocation) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_LATLNG, mLocation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLocation = getArguments().getParcelable(ARG_LATLNG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        //TODO : handel the landscape mode

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        mapView = v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(googleMap -> {
            map = googleMap;
            map.getUiSettings().setMyLocationButtonEnabled(false);
            MapsInitializer.initialize(getActivity());
            if (mLocation != null) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mLocation, 10);
                map.animateCamera(cameraUpdate);
            }
        });

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();


        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setDisplayShowHomeEnabled(false);
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

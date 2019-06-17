package com.mohamedmenasy.backbasetask.features.clitylist.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.core.data.City;
import com.mohamedmenasy.backbasetask.features.clitylist.data.LoadCitiesInteractor;
import com.mohamedmenasy.backbasetask.features.clitylist.data.SearchForCitiesInteractor;
import com.mohamedmenasy.backbasetask.features.info.view.InfoActivity;
import com.mohamedmenasy.backbasetask.features.map.view.MapFragment;

import java.util.List;

public class CityFragment extends Fragment implements CityView {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private CityPresenter presenter;
    private EditText searchET;
    private CityRecyclerViewAdapter adapter;

    public CityFragment() {
    }

    public static CityFragment newInstance() {
        return new CityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(R.string.loading);
        progressDialog.setCancelable(false);
        presenter = new CityPresenter(getActivity(), this, new LoadCitiesInteractor(), new SearchForCitiesInteractor());

    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        searchET = view.findViewById(R.id.searchET);
        searchET.addTextChangedListener(filterTextWatcher);

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {

        getActivity().runOnUiThread(() -> progressDialog.show());
    }

    @Override
    public void hideProgress() {

        getActivity().runOnUiThread(() -> progressDialog.hide());
    }

    @Override
    public void setItems(List<City> items) {
        getActivity().runOnUiThread(() -> {
            adapter = new CityRecyclerViewAdapter(items, item -> openMapFragment(item), item -> showCityInfo(item));

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });


    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

    }


    private TextWatcher filterTextWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            presenter.search(s.toString());
        }

    };


    private void openMapFragment(City item) {
        hideKeyboard();
        String name = item.getName() + ", " + item.getCountry();
        LatLng location = new LatLng(item.getCoord().getLat(), item.getCoord().getLon());
        android.view.View currentMapFragment = getActivity().findViewById(R.id.mapfrag);

        if (currentMapFragment != null && currentMapFragment.getVisibility() == android.view.View.VISIBLE) {

            MapView mapView = getActivity().findViewById(R.id.mapView);

            mapView.getMapAsync(googleMap -> {
                GoogleMap map = googleMap;
                map.getUiSettings().setMyLocationButtonEnabled(false);
                MapsInitializer.initialize(getActivity());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 10);
                map.animateCamera(cameraUpdate);
                map.clear();
                map.addMarker(new MarkerOptions()
                        .position(location)
                        .title(name));
            });
        } else {
            MapFragment mapFragment = MapFragment.newInstance(name, location);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.contents, mapFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void showCityInfo(City item) {

        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra(InfoActivity.INTENT_KEY, item);
        getActivity().startActivity(intent);

        /*new AlertDialog.Builder(getActivity())
                .setTitle(item.getName() + ", " + item.getCountry())
                .setMessage(item.toString())
                .setNeutralButton(R.string.ok, null)
                .show();*/

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchET.getWindowToken(), 0);
    }
}

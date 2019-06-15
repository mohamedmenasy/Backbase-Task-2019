package com.mohamedmenasy.backbasetask.features.main.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.core.model.City;
import com.mohamedmenasy.backbasetask.core.model.LoadCitiesInteractor;
import com.mohamedmenasy.backbasetask.features.map.view.MapFragment;

import java.util.List;

public class CityFragment extends Fragment implements MainView, CityRecyclerViewAdapter.OnListClickInteractionListener {

    private CityRecyclerViewAdapter.OnListClickInteractionListener mListener;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private MainPresenter presenter;
    private EditText searchET;

    public CityFragment() {
    }

    public static CityFragment newInstance() {
        return new CityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(R.string.loading);
        progressDialog.setCancelable(false);
        presenter = new MainPresenter(getActivity(), this, new LoadCitiesInteractor());
        mListener = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        searchET = view.findViewById(R.id.searchET);
        searchET.addTextChangedListener(filterTextWatcher);

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();

    }

    @Override
    public void setItems(List<City> items) {
        CityRecyclerViewAdapter adapter = new CityRecyclerViewAdapter(items, mListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


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
            // adapter.getFilter().filter(s);
        }

    };

    @Override
    public void onListClickInteractionListener(City item) {
        MapFragment mapFragment = MapFragment.newInstance(new LatLng(item.getCoord().getLat(), item.getCoord().getLon()));

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contents, mapFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}

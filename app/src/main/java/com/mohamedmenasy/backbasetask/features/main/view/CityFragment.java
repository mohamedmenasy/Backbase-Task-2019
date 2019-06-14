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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.core.model.City;
import com.mohamedmenasy.backbasetask.core.model.LoadCitiesInteractor;

import java.util.List;

public class CityFragment extends Fragment implements MainView {

    private OnListFragmentInteractionListener mListener;
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
        presenter = new MainPresenter(this, new LoadCitiesInteractor());

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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
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

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(City item);
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

}

package com.mohamedmenasy.backbasetask.features.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.core.model.City;

import java.util.List;


public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.ViewHolder> {

    private final List<City> mValues;
    private final OnListClickInteractionListener mListener;

    public CityRecyclerViewAdapter(List<City> items, OnListClickInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public interface OnListClickInteractionListener {
        void onListClickInteractionListener(City item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleTV.setText(mValues.get(position).getName() + ", " + mValues.get(position).getCountry());
        holder.mSubTitleTV.setText(mValues.get(position).getCoord().getLat() + ", " + mValues.get(position).getCoord().getLon());

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListClickInteractionListener(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleTV;
        public final TextView mSubTitleTV;
        public City mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleTV = view.findViewById(R.id.titleTV);
            mSubTitleTV = view.findViewById(R.id.subTitleTV);
        }
    }
}

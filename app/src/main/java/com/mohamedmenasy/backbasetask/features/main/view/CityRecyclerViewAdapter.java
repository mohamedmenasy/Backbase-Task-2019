package com.mohamedmenasy.backbasetask.features.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.features.main.data.City;

import java.util.List;


public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.ViewHolder> {

    private final List<City> mValues;
    private final OnListClickInteractionListener onListClickInteractionListener;
    private final OnInfoClickInteractionListener onInfoClickInteractionListener;

    public CityRecyclerViewAdapter(List<City> items, OnListClickInteractionListener onListClickInteractionListener, OnInfoClickInteractionListener onInfoClickInteractionListener) {
        mValues = items;
        this.onListClickInteractionListener = onListClickInteractionListener;
        this.onInfoClickInteractionListener = onInfoClickInteractionListener;
    }

    public interface OnListClickInteractionListener {
        void onListClickInteractionListener(City item);
    }

    public interface OnInfoClickInteractionListener {
        void onClick(City item);
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
            if (null != onListClickInteractionListener) {
                onListClickInteractionListener.onListClickInteractionListener(holder.mItem);
            }
        });
        holder.mInfoIV.setOnClickListener(v -> {
            if (null != onInfoClickInteractionListener) {
                onInfoClickInteractionListener.onClick(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mTitleTV;
        final TextView mSubTitleTV;
        final AppCompatImageView mInfoIV;
        City mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleTV = view.findViewById(R.id.titleTV);
            mSubTitleTV = view.findViewById(R.id.subTitleTV);
            mInfoIV = view.findViewById(R.id.infoIV);
        }
    }
}

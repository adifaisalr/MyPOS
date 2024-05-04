package com.adifaisalr.mypos;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adifaisalr.mypos.data.Menu;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Menu> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTV;
        private final TextView categoryTV;
        private final TextView priceTV;

        public ViewHolder(View view) {
            super(view);

            nameTV = view.findViewById(R.id.menu_name_tv);
            categoryTV = view.findViewById(R.id.menu_cat_tv);
            priceTV = view.findViewById(R.id.menu_price_tv);
        }

        public TextView getNameTV() {
            return nameTV;
        }

        public TextView getCategoryTV() {
            return categoryTV;
        }

        public TextView getPriceTV() {
            return priceTV;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView
     */
    public CustomAdapter(ArrayList<Menu> dataSet) {
        localDataSet = dataSet;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNewList(ArrayList<Menu> dataSet) {
        localDataSet.clear();
        localDataSet.addAll(dataSet);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_menu, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNameTV().setText(localDataSet.get(position).name());
        viewHolder.getCategoryTV().setText(localDataSet.get(position).category().getName());
        viewHolder.getPriceTV().setText(String.valueOf(localDataSet.get(position).price()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}


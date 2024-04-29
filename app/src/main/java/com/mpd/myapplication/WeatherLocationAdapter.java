/*
Name                 Emmanuel Bauma Murairi
Student ID           s2110859
Programme of Study   Computing
*/

package com.mpd.myapplication;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mpd.myapplication.databinding.WeatherLocationListContentBinding;
import com.mpd.myapplication.weatherLocation.WeatherLocationContent;

import java.util.ArrayList;


public class WeatherLocationAdapter
        extends RecyclerView.Adapter<WeatherLocationAdapter.ViewHolder> {

    public final ArrayList<WeatherLocationItem> mValues;
    private final View mItemDetailFragmentContainer;
    public Context context;
    public WeatherLocationContent weatherLocationContent;

    WeatherLocationAdapter(ArrayList<WeatherLocationItem> items,
                           View itemDetailFragmentContainer) {
        mValues = items;
        mItemDetailFragmentContainer = itemDetailFragmentContainer;
//        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WeatherLocationListContentBinding binding =
                WeatherLocationListContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTemperatureView.setText(mValues.get(position).getTemperature());
        holder.mPressureView.setText(mValues.get(position).getPressure());
        holder.itemView.setTag(mValues.get(position));
        holder.mLocationView.setText(mValues.get(position).getLocation());
            holder.itemView.setOnClickListener(itemView -> {
                //WeatherLocationItem item = (WeatherLocationItem) itemView.getTag();
                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, String.valueOf(mValues.get(position)));
                System.out.println("mItemDetailFragmentContainer");
                System.out.println(mItemDetailFragmentContainer);
                if (mItemDetailFragmentContainer != null) {
                    Navigation.findNavController(mItemDetailFragmentContainer)
                            .navigate(R.id.fragment_item_detail, arguments);
                } else {
                    Navigation.findNavController(itemView).navigate(R.id.show_item_detail, arguments);
                }
            });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*
             * Context click listener to handle Right click events
             * from mice and trackpad input to provide a more native
             * experience on larger screen devices
             */
//                holder.itemView.setOnContextClickListener(v -> {
//                    weatherLocationContent.WeatherLocationItems item =
//                            (weatherLocationContent.WeatherLocationItems) holder.itemView.getTag();
//                    Toast.makeText(
//                            holder.itemView.getContext(),
//                            "Context click of item " + item.id,
//                            Toast.LENGTH_LONG
//                    ).show();
//                    return true;
//                });
        }
//            holder.itemView.setOnLongClickListener(v -> {
//                // Setting the item id as the clip data so that the drop target is able to
//                // identify the id of the content
//                ClipData.Item clipItem = new ClipData.Item(mValues.get(position).id);
//                ClipData dragData = new ClipData(
//                        ((weatherLocationContent.WeatherLocationItems) v.getTag()).content,
//                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
//                        clipItem
//                );
//
//                if (Build.VERSION.SDK_INT >= 24) {
//                    v.startDragAndDrop(
//                            dragData,
//                            new View.DragShadowBuilder(v),
//                            null,
//                            0
//                    );
//                } else {
//                    v.startDrag(
//                            dragData,
//                            new View.DragShadowBuilder(v),
//                            null,
//                            0
//                    );
//                }
//                return true;
//            });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mLocationView;
        final TextView mTemperatureView;

        final TextView mPressureView;

        ViewHolder(WeatherLocationListContentBinding binding) {
            super(binding.getRoot());
            mLocationView = binding.locationText;
            mTemperatureView = binding.temperatureText;
            mPressureView = binding.pressureText;

        }

    }
}
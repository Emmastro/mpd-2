/*
Name                 Emmanuel Bauma Murairi
Student ID           s2110859
Programme of Study   Computing
*/

package com.mpd.weatherapp_s2110859;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mpd.myapplication.R;
import com.mpd.myapplication.databinding.FragmentItemDetailBinding;
import com.mpd.weatherapp_s2110859.weatherLocation.WeatherLocationContentForcast;
import com.mpd.weatherapp_s2110859.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDetailFragment extends Fragment {


    public static final String ARG_ITEM_ID = "item_id";
    public static final String  ARG_ITEM_LOCATION = "item_location";
    private List<WeatherLocationItem> mItem;
    private View rootView ;
    private CollapsingToolbarLayout mToolbarLayout;
    private final Constants constants = new Constants();
    private FragmentItemDetailBinding binding;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            new Thread(() -> {
                WeatherLocationContentForcast weatherLocationForcast = new WeatherLocationContentForcast();
                mItem = weatherLocationForcast.fetchWeatherData(getArguments().getString(ARG_ITEM_ID));
                if (getActivity() != null) {
                    getActivity().runOnUiThread(this::updateContent);
                }
                // Process or update UI with weather data as needed, ensuring any UI updates are posted back to the main thread
            }).start();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentItemDetailBinding.inflate(inflater, container, false);
        rootView = binding.getRoot();

        mToolbarLayout = rootView.findViewById(R.id.toolbar_layout);
        updateContent();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @SuppressLint("SetTextI18n")
    private void updateContent() {
        if (mItem != null) {
            TextView textView;
            for (int i = 0; i < mItem.size(); i++) {
                WeatherLocationItem item = mItem.get(i);

                textView = rootView.findViewById(
                        getResources().getIdentifier("textTemperature" + i, "id", getActivity().getPackageName())
                );
                textView.setText(item.getTemperature());

                textView = rootView.findViewById(
                        getResources().getIdentifier("textDay" + i, "id", getActivity().getPackageName())
                );
                textView.setText(item.day);

                textView = rootView.findViewById(
                        getResources().getIdentifier("textDescription" + i, "id", getActivity().getPackageName())
                );
                textView.setText(item.description);

                textView = rootView.findViewById(
                        getResources().getIdentifier("textMaxMinTemperature" + i, "id", getActivity().getPackageName())
                );
                textView.setText("High: " + item.getTemperatureMaximum() +" | " + "Low: " + item.getTemperatureMinimum());

                ImageView imageView = rootView.findViewById(
                        getResources().getIdentifier("imageView" + i, "id", getActivity().getPackageName())
                );

                Integer drawableId = constants.weatherMap.get(item.description.strip());

                if (drawableId == null) {
                    drawableId = R.drawable.cloudy; // Default drawable if no match is found
                }
                else{
                    System.out.println("setting new image" + drawableId + item.description);
                }

                imageView.setImageResource(drawableId);

            }

            if (mToolbarLayout != null) {
                assert getArguments() != null;
                mToolbarLayout.setTitle(getArguments().getString(ARG_ITEM_LOCATION));
            }
        }
    }
}
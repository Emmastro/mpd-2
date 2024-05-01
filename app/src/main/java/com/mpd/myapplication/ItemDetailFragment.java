/*
Name                 Emmanuel Bauma Murairi
Student ID           s2110859
Programme of Study   Computing
*/

package com.mpd.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mpd.myapplication.databinding.FragmentItemDetailBinding;
import com.mpd.myapplication.weatherLocation.WeatherLocationContentForcast;
import com.mpd.myapplication.utils.Constants;

import java.util.List;

public class ItemDetailFragment extends Fragment {


    public static final String ARG_ITEM_ID = "item_id";
    public static final String  ARG_ITEM_LOCATION = "item_location";
    private List<WeatherLocationItem> mItem;
    private final Constants constants = new Constants();
    private CollapsingToolbarLayout mToolbarLayout;

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

    private FragmentItemDetailBinding binding;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemDetailBinding.inflate(inflater, container, false);
        rootView = binding.getRoot();
        return  rootView;
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
            ImageView imageView;
            for (int i = 0; i < mItem.size(); i++) {
                WeatherLocationItem item = mItem.get(i);

                // Dynamically accessing the TextViews and ImageView
                int tempId = getResources().getIdentifier("textTemperature" + i, "id", getActivity().getPackageName());
                int dayId = getResources().getIdentifier("textDay" + i, "id", getActivity().getPackageName());
                int descId = getResources().getIdentifier("textDescription" + i, "id", getActivity().getPackageName());
                int maxMinTempId = getResources().getIdentifier("textMaxMinTemperature" + i, "id", getActivity().getPackageName());
                int imageId = getResources().getIdentifier("imageView" + i, "id", getActivity().getPackageName());

//
//                textView = rootView.findViewById(
//                        getResources().getIdentifier("textTemperature" + i, "id", getActivity().getPackageName())
//                );
//                textView.setText(item.getTemperature());
//
//                textView = rootView.findViewById(
//                        getResources().getIdentifier("textDay" + i, "id", getActivity().getPackageName())
//                );
//                textView.setText(item.day);
//
//                textView = rootView.findViewById(
//                        getResources().getIdentifier("textDescription" + i, "id", getActivity().getPackageName())
//                );
//                textView.setText(item.description);
//
//                textView = rootView.findViewById(
//                        getResources().getIdentifier("textMaxMinTemperature" + i, "id", getActivity().getPackageName())
//                );
//                textView.setText("High: " + item.getTemperatureMaximum() +" | " + "Low: " + item.getTemperatureMinimum());
//
//                ImageView imageView = rootView.findViewById(
//                        getResources().getIdentifier("imageView" + i, "id", getActivity().getPackageName())
//                );

                textView = (TextView) rootView.findViewById(tempId);
                textView.setText(item.getTemperature());

                textView = (TextView) rootView.findViewById(dayId);
                textView.setText(item.day);

                textView = (TextView) rootView.findViewById(descId);
                textView.setText(item.description);

                textView = (TextView) rootView.findViewById(maxMinTempId);
                textView.setText("High: " + item.getTemperatureMaximum() +" | " + "Low: " + item.getTemperatureMinimum());

                imageView = (ImageView) rootView.findViewById(imageId);

                Integer drawableId = constants.weatherMap.get(item.description.strip());

                if (drawableId == null) {
                    drawableId = R.drawable.cloudy; // Default drawable if no match is found
                }
                else{
                    System.out.println("setting new image" + drawableId + item.description);
                }

                imageView.setImageResource(drawableId);

            }

//            if (mToolbarLayout != null) {
//                assert getArguments() != null;
//                mToolbarLayout.setTitle(getArguments().getString(ARG_ITEM_LOCATION));
//            }
        }
    }
}
package com.mpd.myapplication;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mpd.myapplication.placeholder.PlaceholderContent;
import com.mpd.myapplication.databinding.FragmentItemDetailBinding;
import com.mpd.myapplication.weatherLocation.WeatherLocationContent;
import com.mpd.myapplication.weatherLocation.WeatherLocationContentForcast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListFragment}
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String  ARG_ITEM_LOCATION = "item_location";
    private List<WeatherLocationItem> mItem;
    private View rootView ;
    /**
     * The placeholder content this fragment is presenting.
     */
    private CollapsingToolbarLayout mToolbarLayout;

    private FragmentItemDetailBinding binding;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
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


            }

            if (mToolbarLayout != null) {
                assert getArguments() != null;
                mToolbarLayout.setTitle(getArguments().getString(ARG_ITEM_LOCATION));
            }
        }
    }
}
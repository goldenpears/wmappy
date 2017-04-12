package com.locovna.wmappy.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.locovna.wmappy.R;
import com.locovna.wmappy.model.Country;

import java.util.List;

/**
 * An {@link CountryAdapter} knows how to create a list item layout for each country
 * in the data source (a list of {@link Country} objects).
 */

public class CountryAdapter extends ArrayAdapter<Country> {

  /**
   * Constructs a new {@link CountryAdapter}.
   *
   * @param context   of the app
   * @param countries is the list of countries, which is the data source of the adapter
   */
  public CountryAdapter(Context context, List<Country> countries) {
    super(context, 0, countries);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View listItemView = convertView;
    if (listItemView == null) {
      listItemView = LayoutInflater.from(getContext()).inflate(
          R.layout.country_list_item, parent, false);
    }

    // Find the country at the given position in the list of countries
    Country currentCountry = getItem(position);
    TextView countryView = (TextView) listItemView.findViewById(R.id.country);
    // Display the name of the current country in that TextView
    countryView.setText(currentCountry.getCountry());

    return listItemView;
  }
}

package com.locovna.wmappy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.locovna.wmappy.controller.CountryAdapter;
import com.locovna.wmappy.controller.QueryUtils;
import com.locovna.wmappy.model.Country;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  public static final String LOG_TAG = MainActivity.class.getSimpleName();
  public static final String REQUEST_URL =
      "https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";
  private CountryAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ListView countriesListView = (ListView) findViewById(R.id.list);

    mAdapter = new CountryAdapter(this, new ArrayList<Country>());
    countriesListView.setAdapter(mAdapter);

    countriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        // Find the current country that was clicked on
        Country currentCountry = mAdapter.getItem(position);
        ArrayList cities = currentCountry.getCities();
        //        Toast.makeText(getApplicationContext(), "Cities of " + currentCountry.getCountry() + ": " + cities, Toast.LENGTH_LONG).show();
        showDialog(cities);
      }
    });

    CountryAsyncTask task = new CountryAsyncTask();
    task.execute(REQUEST_URL);
  }

  private void showDialog(final ArrayList cities) {
    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, cities);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Choose your city to explore")
        .setAdapter(adapter, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {
            Object currentCity = cities.toArray()[id].toString();
            Toast.makeText(getApplicationContext(), "we are going to " + currentCity + "!", Toast.LENGTH_SHORT).show();
            //            Uri wikiUri = Uri.parse("http://api.geonames.org//wikipediaSearchJSON?q=" + currentCity + "&maxRows=10&username=demo");
            //
            //            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, wikiUri);
            //            startActivity(websiteIntent);

            Intent intent = new Intent(getApplicationContext(), WikiActivity.class);
            intent.putExtra("Current city", currentCity.toString().toLowerCase());
            startActivity(intent);
          }
        });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private class CountryAsyncTask extends AsyncTask<String, Void, List<Country>> {
    @Override
    protected List<Country> doInBackground(String... urls) {
      // Don't perform the request if there are no URLs, or the first URL is null
      if (urls.length < 1 || urls[0] == null) {
        return null;
      }

      List<Country> result = QueryUtils.fetchCountriesData(urls[0]);
      return result;
    }

    @Override
    protected void onPostExecute(List<Country> data) {
      // Clear the adapter of previous countries data
      mAdapter.clear();

      // If there is a valid list of {@link Countries}s, then add them to the adapter's
      // data set. This will trigger the ListView to update.
      if (data != null && !data.isEmpty()) {
        mAdapter.addAll(data);
      }
    }
  }
}

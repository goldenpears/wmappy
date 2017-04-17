package com.locovna.wmappy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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

    CountryAsyncTask task = new CountryAsyncTask();
    task.execute(REQUEST_URL);
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
      // Clear the adapter of previous earthquake data
      mAdapter.clear();

      // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
      // data set. This will trigger the ListView to update.
      if (data != null && !data.isEmpty()) {
        mAdapter.addAll(data);
      }
    }
  }
}

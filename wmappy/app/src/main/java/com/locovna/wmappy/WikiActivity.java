package com.locovna.wmappy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.locovna.wmappy.controller.QueryUtils;
import com.locovna.wmappy.model.City;

public class WikiActivity extends AppCompatActivity {
  public static final String LOG_TAG = WikiActivity.class.getSimpleName();
  public static final String REQUEST_URL = "http://api.geonames.org//wikipediaSearchJSON?q=london&maxRows=10&username=demo";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wiki);

    ImageView cityPhoto = (ImageView) findViewById(R.id.city_photo);
    TextView cityName = (TextView) findViewById(R.id.city_name);
    TextView citySummary = (TextView) findViewById(R.id.city_summary);

    City city = new City("London", "Is great");

    cityName.setText(city.getCityName());
    citySummary.setText(city.getCitySummary());

    CityAsyncTask task = new CityAsyncTask();
    task.execute(REQUEST_URL);
  }

  private class CityAsyncTask extends AsyncTask<String, Void, City> {
    @Override
    protected City doInBackground(String... urls) {
      // Don't perform the request if there are no URLs, or the first URL is null
      if (urls.length < 1 || urls[0] == null) {
        return null;
      }

      City result = QueryUtils.fetchCityData(urls[0]);
      return result;
    }
  }
}

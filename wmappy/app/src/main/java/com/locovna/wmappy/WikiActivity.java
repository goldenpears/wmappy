package com.locovna.wmappy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.locovna.wmappy.controller.QueryUtils;
import com.locovna.wmappy.model.City;

public class WikiActivity extends AppCompatActivity {
  public static final String LOG_TAG = WikiActivity.class.getSimpleName();
  public static final String REQUEST_URL = "http://api.geonames.org//wikipediaSearchJSON?q=";
  //  london&maxRows=10&username=demo


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wiki);

    Bundle extras = getIntent().getExtras();
    String currentCity = extras.getString("Current city");
    Toast.makeText(getApplicationContext(),
        // Capitalize first letter of the City
        "we are here, " + currentCity.substring(0, 1).toUpperCase() + currentCity.substring(1) + " <3",
        Toast.LENGTH_SHORT).show();

    TextView cityName = (TextView) findViewById(R.id.city_name);
    TextView citySummary = (TextView) findViewById(R.id.city_summary);
    ImageView cityPhoto = (ImageView) findViewById(R.id.city_photo);

    City city = new City("Waiting city", "It seems like nothing here! \nTry to check your connection or just waaaaiit");

    cityName.setText(city.getCityName());
    citySummary.setText(city.getCitySummary());

    CityAsyncTask task = new CityAsyncTask();
    task.execute(REQUEST_URL + currentCity + "&maxRows=10&username=demo");
  }

  private class CityAsyncTask extends AsyncTask<String, Void, City> {
    @Override
    protected City doInBackground(String... urls) {
      // Don't perform the request if there are no URLs, or the first URL is null
      if (urls.length < 1 || urls[0] == null) {
        return null;
      }

      City city = QueryUtils.fetchCityData(urls[0]);
      return city;
    }

    @Override
    protected void onPostExecute(City data) {
      if (data != null) {
        TextView cityName = (TextView) findViewById(R.id.city_name);
        TextView citySummary = (TextView) findViewById(R.id.city_summary);
        ImageView cityPhoto = (ImageView) findViewById(R.id.city_photo);
        cityName.setText(data.getCityName());
        citySummary.setText(data.getCitySummary());
      }
    }
  }
}

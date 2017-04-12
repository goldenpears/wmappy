package com.locovna.wmappy.controller;

import android.util.Log;

import com.locovna.wmappy.model.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Helper methods related to requesting and receiving country data from JSON
 */

public class QueryUtils {

  public static final String LOG_TAG = QueryUtils.class.getSimpleName();
  //  public static final String REQUEST_URL = "https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";
  public static final String TEST_JSON_RESPONCE = "{\n" +
      "  \"China\": [\"fendou\", \"hetao\"],\n" +
      "  \"Japan:\" : [\"tokyo\", \"hiroshima\"]\n" +
      "}";

  /**
   * No one should create {@link QueryUtils},
   * This class is only meant to hold static variables and methods
   */
  private QueryUtils() {
  }

  public static ArrayList<Country> extractCountries() {
    ArrayList<Country> countries = new ArrayList<>();

    try {
      JSONObject obj = new JSONObject(TEST_JSON_RESPONCE);

      for (int i = 0; i < obj.length(); i++) {

        // parse every key in JSONObject
        Iterator<String> keys = obj.keys();
        while (keys.hasNext()) {
          String countryName = (String) keys.next();
          JSONArray cities = obj.getJSONArray(countryName);

          // convert JSONArray to ArrayList<String>
          ArrayList<String> citiesArray = new ArrayList<String>();
          if (cities != null) {
            int len = cities.length();
            for (int x = 0; x < len; x++) {
              citiesArray.add(cities.get(i).toString());
            }
          }

          Country country = new Country(countryName, citiesArray);
          countries.add(country);
          Log.v(LOG_TAG, country.getCountry() + country.getCities());
        }
      }

    } catch (JSONException e) {
      Log.e(LOG_TAG, "Problem parsing the countries JSON results", e);
    }

    return countries;
  }
}



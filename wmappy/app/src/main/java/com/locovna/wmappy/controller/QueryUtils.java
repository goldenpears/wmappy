package com.locovna.wmappy.controller;

import android.text.TextUtils;
import android.util.Log;

import com.locovna.wmappy.model.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Helper methods related to requesting and receiving country data from JSON
 */

public class QueryUtils {

  public static final String LOG_TAG = QueryUtils.class.getSimpleName();
  //  public static final String TEST_JSON_RESPONCE = "{\n" +
  //      "  \"China\": [\"fendou\", \"hetao\"],\n" +
  //      "  \"Japan\" : [\"tokyo\", \"hiroshima\"]\n" +
  //      "}";

  /**
   * No one should create {@link QueryUtils},
   * This class is only meant to hold static variables and methods
   */
  private QueryUtils() {
  }

  public static List<Country> fetchCountriesData(String requestUrl) {
    // Create URL object
    URL url = createUrl(requestUrl);

    // Perform HTTP request to the URL and receive a JSON response back
    String jsonResponse = null;
    try {
      jsonResponse = makeHttpRequest(url);
    } catch (IOException e) {
      Log.e(LOG_TAG, "Problem making the HTTP request.", e);
    }

    // Extract relevant fields from the JSON response and create a list of {@link Countries}s
    List<Country> countries = extractDataFromJson(jsonResponse);

    // Return the list of {@link Countries}
    return countries;
  }

  private static URL createUrl(String stringUrl) {
    URL url = null;
    try {
      url = new URL(stringUrl);
    } catch (MalformedURLException e) {
      Log.e(LOG_TAG, "Problem building the URL ", e);
    }
    return url;
  }

  /**
   * Make an HTTP request to the given URL and return a String as the response.
   */
  private static String makeHttpRequest(URL url) throws IOException {
    String jsonResponse = "";

    // If the URL is null, then return early.
    if (url == null) {
      return jsonResponse;
    }

    HttpURLConnection urlConnection = null;
    InputStream inputStream = null;
    try {
      urlConnection = (HttpURLConnection) url.openConnection();
      urlConnection.setReadTimeout(10000 /* milliseconds */);
      urlConnection.setConnectTimeout(15000 /* milliseconds */);
      urlConnection.setRequestMethod("GET");
      urlConnection.connect();

      // If the request was successful (response code 200),
      // then read the input stream and parse the response.
      if (urlConnection.getResponseCode() == 200) {
        inputStream = urlConnection.getInputStream();
        jsonResponse = readFromStream(inputStream);
      } else {
        Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
      }
    } catch (IOException e) {
      Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
    } finally {
      if (urlConnection != null) {
        urlConnection.disconnect();
      }
      if (inputStream != null) {
        // Closing the input stream could throw an IOException, which is why
        // the makeHttpRequest(URL url) method signature specifies than an IOException
        // could be thrown.
        inputStream.close();
      }
    }
    return jsonResponse;
  }

  /**
   * Convert the {@link InputStream} into a String which contains the
   * whole JSON response from the server.
   */
  private static String readFromStream(InputStream inputStream) throws IOException {
    StringBuilder output = new StringBuilder();
    if (inputStream != null) {
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
      BufferedReader reader = new BufferedReader(inputStreamReader);
      String line = reader.readLine();
      while (line != null) {
        output.append(line);
        line = reader.readLine();
      }
    }
    return output.toString();
  }


  public static List<Country> extractDataFromJson(String countriesJson) {
    if (TextUtils.isEmpty(countriesJson)) {
      return null;
    }

    List<Country> countries = new ArrayList<>();

    try {
      JSONObject obj = new JSONObject(countriesJson);

      // parse every key in JSONObject
      Iterator<String> keys = obj.keys();
      while (keys.hasNext()) {
        Log.v(LOG_TAG, "keys: " + keys);
        String countryName = (String) keys.next();
        JSONArray cities = obj.getJSONArray(countryName);

        // convert JSONArray to ArrayList<String>
        ArrayList<String> citiesArray = new ArrayList<String>();
        if (cities != null) {
          int len = cities.length();
          for (int i = 0; i < len; i++) {
            citiesArray.add(cities.get(i).toString());
          }
        }

        Country country = new Country(countryName, citiesArray);
        countries.add(country);
        Log.v(LOG_TAG, country.getCountry() + country.getCities());
      }

    } catch (
        JSONException e)

    {
      Log.e(LOG_TAG, "Problem parsing the countries JSON results", e);
    }

    return countries;
  }
}



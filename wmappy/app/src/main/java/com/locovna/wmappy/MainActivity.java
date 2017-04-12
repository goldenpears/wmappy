package com.locovna.wmappy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.locovna.wmappy.controller.CountryAdapter;
import com.locovna.wmappy.controller.QueryUtils;
import com.locovna.wmappy.model.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  public static final String LOG_TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ArrayList<Country> countries = QueryUtils.extractCountries();

    //    ArrayList<Country> countries = new ArrayList<>();
    //    countries.add(new Country("USA"));
    //    countries.add(new Country("Canada"));
    //    countries.add(new Country("Some Canada"));
    //    countries.add(new Country("Any Japan"));

    ListView countriesListView = (ListView) findViewById(R.id.list);

    CountryAdapter adapter = new CountryAdapter(this, countries);
    countriesListView.setAdapter(adapter);
  }
}

package com.locovna.wmappy.model;

import java.util.ArrayList;

/**
 * An {@link Country} object contains information related to a single Country.
 */

public class Country {
  private String mCountry;
  private ArrayList<String> mCities;

  public Country(String countryName) {
    mCountry = countryName;
  }

  public Country(String countryName, ArrayList<String> cities) {
    mCountry = countryName;
    mCities = cities;
  }

  public String getCountry() {
    return mCountry;
  }

  public void setCountry(String country) {
    mCountry = country;
  }

  public ArrayList getCities() {
    return mCities;
  }

  public void setCities(ArrayList cities) {
    mCities = cities;
  }
}

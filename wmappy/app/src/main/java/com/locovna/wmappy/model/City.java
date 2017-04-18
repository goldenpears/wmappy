package com.locovna.wmappy.model;

import android.media.Image;

public class City {

  private String cityName;
  private String citySummary;
  private Image cityPhoto;

  public City(){}
  public City(String name, String summary){
    cityName = name;
    citySummary = summary;
  }
  public City(String name, String summary, Image photo){
    cityName = name;
    citySummary = summary;
    cityPhoto = photo;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCitySummary() {
    return citySummary;
  }

  public void setCitySummary(String citySummary) {
    this.citySummary = citySummary;
  }

  public Image getCityPhoto() {
    return cityPhoto;
  }

  public void setCityPhoto(Image cityPhoto) {
    this.cityPhoto = cityPhoto;
  }
}

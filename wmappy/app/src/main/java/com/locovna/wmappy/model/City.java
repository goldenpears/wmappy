package com.locovna.wmappy.model;

public class City {

  private String cityName;
  private String citySummary;
  private String cityPhoto;

  public City(){}
  public City(String name, String summary){
    cityName = name;
    citySummary = summary;
  }
  public City(String name, String summary, String photo){
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

  public String getCityPhoto() {
    return cityPhoto;
  }

  public void setCityPhoto(String cityPhoto) {
    this.cityPhoto = cityPhoto;
  }
}

package com.codepath.example.masterdetailmanual.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Comics implements Serializable{

  @SerializedName("available") @Expose private int available;
  @SerializedName("collectionURI") @Expose private String collectionURI;
  @SerializedName("comic") @Expose private List<Comic> comics = null;
  @SerializedName("returned") @Expose private int returned;

  public int getAvailable() {
    return available;
  }

  public void setAvailable(int available) {
    this.available = available;
  }

  public String getCollectionURI() {
    return collectionURI;
  }

  public void setCollectionURI(String collectionURI) {
    this.collectionURI = collectionURI;
  }

  public List<Comic> getComics() {
    return comics;
  }

  public void setComics(List<Comic> comics) {
    this.comics = comics;
  }

  public int getReturned() {
    return returned;
  }

  public void setReturned(int returned) {
    this.returned = returned;
  }
}

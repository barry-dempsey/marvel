package com.dempsey.example.marvelapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Events implements Serializable {

  @SerializedName("available") @Expose private int available;
  @SerializedName("collectionURI") @Expose private String collectionURI;
  @SerializedName("items") @Expose private List<Item___> items = null;
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

  public List<Item___> getItems() {
    return items;
  }

  public void setItems(List<Item___> items) {
    this.items = items;
  }

  public int getReturned() {
    return returned;
  }

  public void setReturned(int returned) {
    this.returned = returned;
  }
}

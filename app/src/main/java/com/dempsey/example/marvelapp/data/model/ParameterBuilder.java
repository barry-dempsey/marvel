package com.dempsey.example.marvelapp.data.model;

import android.support.annotation.NonNull;

public final class ParameterBuilder {

  private String characterId;
  private String parameterOne;
  private String parameterTwo;
  private String dateTimeParameter;
  private String encodedParameter;

  public String getCharacterId() {
    return characterId;
  }

  public String getParameterOne() {
    return parameterOne;
  }

  public String getParameterTwo() {
    return parameterTwo;
  }

  public String getDateParameter() {
    return dateTimeParameter;
  }

  public String getEncodedParameter() {
    return encodedParameter;
  }

  public ParameterBuilder withCharacterId(@NonNull final String characterId) {
    this.characterId = characterId;
    return this;
  }

  public ParameterBuilder withFirstParameter(@NonNull final String parameterOne) {
    this.parameterOne = parameterOne;
    return this;
  }

  public ParameterBuilder withSecondParameter(@NonNull final String parameterTwo) {
    this.parameterTwo = parameterTwo;
    return this;
  }

  public ParameterBuilder withDateParameter(@NonNull final String dateParameter) {
    this.dateTimeParameter = dateParameter;
    return this;
  }

  public ParameterBuilder withEncodedParameter(@NonNull final String encodedParameter) {
    this.encodedParameter = encodedParameter;
    return this;
  }
}

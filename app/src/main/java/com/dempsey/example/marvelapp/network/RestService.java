package com.dempsey.example.marvelapp.network;

import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.Comics;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {

  @GET("v1/public/characters?")
  Call<Comics> getCharacterByName(@Query("name") final String name,
                                   @Query("ts") final String ts,
                                   @Query("apikey") final String apiKey,
                                   @Query("limit") final String limit,
                                   @Query("hash") final String hash);

  @GET("v1/public/characters/{characterId}/comics?")
  Call<Comic> getListOfComicForCharacter(@Path("characterId") final String characterId,
                                               @Query("ts") final String ts,
                                               @Query("apikey") final String apiKey,
                                               @Query("limit") final String limit,
                                               @Query("hash") final String hash);
}

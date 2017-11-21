package com.dempsey.example.marvelapp.network;

import android.content.Context;
import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.Comics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RestServiceProvider<T> {

  protected Context context;

  private T service;

  RestServiceProvider(@NonNull final Context context) {
    this.context = context;
    service = createService();
  }

  public T getService() {
    return service;
  }

  private T createService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(getServiceEndpoint())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .client(provideOkHttpClient())
        .build();
    return retrofit.create(getServiceClass());
  }

  private OkHttpClient provideOkHttpClient() {
    final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.readTimeout(60, TimeUnit.SECONDS);
    builder.connectTimeout(60, TimeUnit.SECONDS);
    return builder.build();
  }

  private Gson getGson() {
    final GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Comic.class, new JsonDeserializer<Comic>() {
      @Override
      public Comic deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement networkElement = json.getAsJsonObject().get("data");
        final JsonElement comicJson = networkElement.getAsJsonObject().get("results");
        final List<Comic> comicList = new Gson().fromJson(comicJson, new TypeToken<List<Comic>>(){}.getType());
        final Comics comics = new Comics();
        comics.setComics(comicList);
        final Comic comic = new Comic();
        comic.setComics(comics);
        return comic;
      }
    });
    return builder.create();
  }

  protected abstract String getServiceEndpoint();

  protected abstract Class<T> getServiceClass();
}

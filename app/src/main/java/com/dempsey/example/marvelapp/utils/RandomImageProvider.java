package com.dempsey.example.marvelapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.dempsey.example.marvelapp.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomImageProvider {

  private final Random random;

  private RandomImageProvider() {
    random = new Random();
  }

  public static RandomImageProvider newInstance() {
    return new RandomImageProvider();
  }

  public Drawable provideImage(final Context context) {
    final List<Drawable> drawables = new ArrayList<>();
    drawables.add(ContextCompat.getDrawable(context, R.drawable.deadpool_img_1));
    drawables.add(ContextCompat.getDrawable(context, R.drawable.deadpool_img_2));
    drawables.add(ContextCompat.getDrawable(context, R.drawable.deadpool_img_3));
    drawables.add(ContextCompat.getDrawable(context, R.drawable.deadpool_img_4));
    drawables.add(ContextCompat.getDrawable(context, R.drawable.deadpool_img_5));
    final int randomIndex = random.nextInt(drawables.size()-1);
    return drawables.get(randomIndex);
  }

}

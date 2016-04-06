package com.liu.testdesignpatterns;

import android.graphics.Bitmap;

/**
 * Created by Liu on 2016/4/7.
 */
public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url,Bitmap bitmap);
}

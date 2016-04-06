package com.liu.testdesignpatterns;

import android.graphics.Bitmap;

/**
 * Created by Liu on 2016/4/7.
 */
public class DoubleCache implements ImageCache{
    MemoryCache mMemoryCache=new MemoryCache();
    DiskCache mDishCache=new DiskCache();

    public Bitmap get(String url){
        Bitmap bitmap=null;
        bitmap=mMemoryCache.get(url);
        if (bitmap==null)
            bitmap=mDishCache.get(url);
        return bitmap;
    }

    public void put(String url,Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
        mDishCache.put(url, bitmap);
    }
}

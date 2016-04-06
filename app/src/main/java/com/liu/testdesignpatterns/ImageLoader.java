package com.liu.testdesignpatterns;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Liu on 2016/4/6.
 */
public class ImageLoader {
    ImageCache mImageCache=new MemoryCache();

    ExecutorService mExecutorService= Executors.newFixedThreadPool(Runtime.getRuntime()
    .availableProcessors());

    public void setImageCache(ImageCache cache){
        mImageCache=cache;
    }


    public void displayImage(final String url,final ImageView imageView){
        Bitmap bitmap=mImageCache.get(url);
        if (bitmap!=null){
           imageView.setImageBitmap(bitmap);
            return;
        }

        submitLoadRequest(url,imageView);
    }

    private void submitLoadRequest(final String imageUrl,final ImageView imageView){
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=downloadImage(imageUrl);
                if (bitmap==null){
                    return;
                }
                if (imageView.getTag().equals(imageUrl)){
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(imageUrl,bitmap);
            }
        });
    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap=null;
        try{
            URL url=new URL(imageUrl);
            final HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            bitmap= BitmapFactory.decodeStream(conn.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}

package com.liu.testdesignpatterns;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;

/**
 * Created by Liu on 2016/4/6.
 */
public class DiskCache implements ImageCache{
    static String cacheDir="sdcard/cache/";
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(cacheDir+url);
    }

    public void put(String url,Bitmap bitmap){
        FileOutputStream fileOutputStream=null;
        try{
            fileOutputStream=new FileOutputStream(cacheDir+url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileOutputStream!=null){
                try{
                    fileOutputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.liu.testdesignpatterns;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imv;
    private Button btn_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imv= (ImageView) findViewById(R.id.imv);
        btn_change= (Button) findViewById(R.id.btn_change);

        final ImageLoader loader=new ImageLoader();
//        loader.setImageCache(new MemoryCache());
//        loader.setImageCache(new DiskCache());
        loader.setImageCache(new DoubleCache());

//        loader.setImageCache(new ImageCache() {
//            @Override
//            public Bitmap get(String url) {
//                return null;
//            }
//
//            @Override
//            public void put(String url, Bitmap bitmap) {
//
//            }
//        });

        loader.displayImage("http://e.hiphotos.baidu.com/image/pic/item/e7cd7b899e510fb34395d1c3de33c895d0430cd1.jpg"
                ,imv);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.displayImage("http://img.ugirls.com/uploads/magazine/content/b99de2be8f74c784f5f9bf7b51c85556_magazine_web_m.jpg",imv);
            }
        });
    }
}

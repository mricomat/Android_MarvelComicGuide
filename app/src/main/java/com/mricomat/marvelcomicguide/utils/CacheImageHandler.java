package com.mricomat.marvelcomicguide.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mricomat on 17/05/2018.
 */

@Singleton
public class CacheImageHandler {


    private Context mContext;
    private SoftImageCache mSoftCache;

    @Inject
    public CacheImageHandler(Context context) {
        mContext = context;
        int memClass = ( (ActivityManager) mContext.getSystemService( Context.ACTIVITY_SERVICE ) ).getMemoryClass();
        int cacheSize = 1024 * 1024 * memClass;
        mSoftCache = new SoftImageCache(cacheSize);
    }

    // Save Bitmap to CacheDir and save it on softCache
    public void saveBitmapToCache(String key, Bitmap bitmap) {
        if (bitmap != null) {
            File file;
            try {
                file = new File(mContext.getCacheDir(), HashGenerator.md5(key));
                FileOutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                Log.w("CacheImageHandler", "Error writing file from cache!", e);
            }

                mSoftCache.put(HashGenerator.md5(key), bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String key) {
        Bitmap softBitmap = getFromSoftCache(key);
        return softBitmap == null ? getFromHardCache(key) : softBitmap;
    }

    private Bitmap getFromSoftCache(String key) {
        return mSoftCache.get(HashGenerator.md5(key));
    }

    private Bitmap getFromHardCache(String key) {
        File cacheFile = getFile(key);
        if (cacheFile == null || !cacheFile.exists()) {
            return null;
        }
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(cacheFile);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            mSoftCache.put(HashGenerator.md5(key), bitmap);
            return bitmap;
        } catch (IOException ex) {
            Log.w("CacheImageHandler", "Error reading file from cache!", ex);
        }
        return null;
    }

    private File getFile(String url) {
        if (mContext.getCacheDir() == null) {
            return null;
        }
        return new File(mContext.getCacheDir(), HashGenerator.md5(url));
    }

    private class SoftImageCache extends LruCache<String, Bitmap> {

        public SoftImageCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    }
}

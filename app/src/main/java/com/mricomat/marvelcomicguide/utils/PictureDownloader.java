package com.mricomat.marvelcomicguide.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.mricomat.marvelcomicguide.data.network.MarvelImageApi;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mricomat on 16/05/2018.
 */

@Singleton
public class PictureDownloader {

    private static final String BASE_URL = "http://i.annihil.us/";

    private CacheImageHandler mCacheHandler;

    @Inject
    public PictureDownloader(CacheImageHandler cacheHandler) {
        this.mCacheHandler = cacheHandler;
    }

    public void download(String url, ImageView imageView) {
        if (url == null) {
            return;
        }
        if (imageView == null) {
            return;
        }

        Bitmap bitmap = mCacheHandler.getBitmapFromCache(url);

        if (bitmap == null) {
            forceDownload(url, imageView);
        } else {
            cancelPotentialDownload(url, imageView);
            imageView.setImageBitmap(bitmap);
        }
    }

    private void forceDownload(String url, ImageView imageView) {
        if (url == null) {
            imageView.setImageDrawable(null);
            return;
        }

        if (cancelPotentialDownload(url, imageView)) {
            BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
            DownloadedDrawable downloadedDrawable = new DownloadedDrawable(task);
            imageView.setImageDrawable(downloadedDrawable);
            task.execute(url);
        }
    }

    Bitmap downloadBitmapFromUrl(String url) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        MarvelImageApi service = retrofit.create(MarvelImageApi.class);

        try {
            Call<ResponseBody> call = service.getImage(url);
            ResponseBody response = call.execute().body();
            if (response != null) {
                InputStream inputStream = null;
                try {
                    inputStream = response.byteStream();
                    return BitmapFactory.decodeStream(inputStream);
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        } catch (IOException e) {
            //service.getImage().cancel();
            Log.w("DownloadBitMap", "I/O error while retrieving bitmap from " + url, e);
        } catch (IllegalStateException e) {
            //service.getImage().cancel();
            Log.w("DownloadBitMap", "Incorrect URL: " + url);
        } catch (Exception e) {
            //service.getImage().cancel();
            Log.w("DownloadBitMap", "Error while retrieving bitmap from " + url, e);
        }

        return null;
    }

    private static boolean cancelPotentialDownload(String url, ImageView imageView) {
        BitmapDownloaderTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);

        if (bitmapDownloaderTask != null) {
            String bitmapUrl = bitmapDownloaderTask.url;
            if ((bitmapUrl == null) || (!bitmapUrl.equals(url))) {
                bitmapDownloaderTask.cancel(true);
            } else {
                return false;
            }
        }
        return true;
    }

    private class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {

        private String url;
        private final WeakReference<ImageView> mImageViewWeakReference;

        public BitmapDownloaderTask(ImageView imageView) {
            mImageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            url = strings[0];
            return downloadBitmapFromUrl(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

           // Bitmap bitmapCopy = bitmap.copy(bitmap.getConfig(), true);
            mCacheHandler.saveBitmapToCache(url, bitmap);

            if (mImageViewWeakReference != null) {
                ImageView imageView = mImageViewWeakReference.get();
                BitmapDownloaderTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);
                // Change bitmap only if this process is still associated with it
                if ((this == bitmapDownloaderTask)) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    private static BitmapDownloaderTask getBitmapDownloaderTask(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof DownloadedDrawable) {
                DownloadedDrawable downloadedDrawable = (DownloadedDrawable) drawable;
                return downloadedDrawable.getBitmapDownloaderTask();
            }
        }
        return null;
    }

    private static class DownloadedDrawable extends ColorDrawable {
        private final WeakReference<BitmapDownloaderTask> mBitmapDownloaderTaskReference;

        public DownloadedDrawable(BitmapDownloaderTask bitmapDownloaderTask) {
            super(Color.BLACK);
            mBitmapDownloaderTaskReference =
                new WeakReference<BitmapDownloaderTask>(bitmapDownloaderTask);
        }

        public BitmapDownloaderTask getBitmapDownloaderTask() {
            return mBitmapDownloaderTaskReference.get();
        }
    }
}

package com.mricomat.marvelcomicguide.data.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by mricomat on 16/05/2018.
 */

public interface MarvelImageApi {
    @GET
    Call<ResponseBody> getImage(@Url String imageUrl);
}

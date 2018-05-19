package com.mricomat.marvelcomicguide.data.network;

import android.support.annotation.Nullable;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mricomat on 18/05/2018.
 */

public interface MarvelApi {

    @GET("characters")
    Call<DataWrapperModel<List<CharacterModel>>> getCharacters(@Query("apikey") String apiKey,
                                                               @Query("hash") String md5Key,
                                                               @Query("ts") long timestamp,
                                                               @Nullable @Query("offset") Integer offset,
                                                               @Nullable @Query("limit") Integer limit,
                                                               @Nullable @Query("nameStartWith") String searchQuery);

    @GET("characters/{characterId}/{comicType}")
    Call<DataWrapperModel<List<ComicModel>>> getCharacterComics(@Path("characterId") long characterId,
                                                                @Path("comicType") String comicType,
                                                                @Query("offset") Integer offset,
                                                                @Query("limit") Integer limit,
                                                                @Query("apikey") String publicKey,
                                                                @Query("hash") String md5Digest,
                                                                @Query("ts") long timestamp);
}

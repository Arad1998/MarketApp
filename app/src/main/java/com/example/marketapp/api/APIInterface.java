package com.example.marketapp.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIInterface {
    @GET
    Call<ResponseBody> getNewsData(@Url String url);
}

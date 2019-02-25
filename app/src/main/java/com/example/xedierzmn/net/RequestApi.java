package com.example.xedierzmn.net;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface RequestApi {
    @POST
    Call<ResponseBody> getPost(@Url String url, @FieldMap HashMap<String,String>params);
    @GET
    Call<ResponseBody>getReg(@Url String url);
    @PUT
    Call<ResponseBody> getPut(@Header("userId") String userId, @Header("sessionId") String sessionId, @Field("nickName") String name);
}

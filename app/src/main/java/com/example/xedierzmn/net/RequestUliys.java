package com.example.xedierzmn.net;

import com.example.xedierzmn.api.Api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestUliys {
    private static RequestUliys imadaeUliys;
    private Retrofit retrofit;
    public RequestUliys(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(Api.WEIDU_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public static RequestUliys getImadaeUliys(){
        if (imadaeUliys==null){
            synchronized (RequestUliys.class){
                if (imadaeUliys==null){
                    imadaeUliys=new RequestUliys();
                }
            }
        }
        return imadaeUliys;
    }
    public void doGet(String url, final RequestCallback callback){
        RequestApi requestApi = retrofit.create(RequestApi.class);
        requestApi.getReg(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String string = response.body().string();
                        if (callback != null) {
                            callback.onSuccess(string);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (callback!=null){
                        callback.onFailUre("网络异常，请稍后再试");
                    }
            }
        });
    }
    public void doPut(String userId, String sessionId, final RequestCallback callback){
        RequestApi requestApi = retrofit.create(RequestApi.class);
        requestApi.getPut(userId,sessionId,"").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    try {
                        String string = response.body().string();
                        if (callback!=null){
                            callback.onSuccess(string);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback!=null){
                    callback.onFailUre("网咯不稳定，请稍后再试");
                }
            }
        });
    }
}

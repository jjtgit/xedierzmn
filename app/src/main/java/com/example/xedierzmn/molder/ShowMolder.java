package com.example.xedierzmn.molder;

import com.example.xedierzmn.api.Api;
import com.example.xedierzmn.contract.ShowContract;
import com.example.xedierzmn.net.RequestCallback;
import com.example.xedierzmn.net.RequestUliys;

import java.util.HashMap;

public class ShowMolder implements ShowContract.ShowMolder {

    @Override
    public void SosMolder(HashMap<String, String> params, final RequestCallback callback) {
        String keyword = params.get("keyword");
        String page = params.get("page");
        String count = params.get("count");
        RequestUliys.getImadaeUliys().doGet(Api.SHOW_URL + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                if (callback!=null){
                    callback.onSuccess(result);
                }
            }

            @Override
            public void onFailUre(String msg) {
                if (callback!=null){
                    callback.onFailUre(msg);
                }
            }
        });
    }

    @Override
    public void XianMolder(HashMap<String, String> params, final RequestCallback callback) {
        String commodityId = params.get("commodityId");
        RequestUliys.getImadaeUliys().doGet(Api.SHOW_XIAN + "?commodityId=" + commodityId, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                if (callback!=null){
                    callback.onSuccess(result);
                }
            }

            @Override
            public void onFailUre(String msg) {
                if (callback!=null){
                    callback.onFailUre(msg);
                }
            }
        });
    }

    @Override
    public void TjgwcMolder(HashMap<String, String> params, RequestCallback callback) {

    }

    @Override
    public void GwcMolder(HashMap<String, String> params, RequestCallback callback) {

    }
}

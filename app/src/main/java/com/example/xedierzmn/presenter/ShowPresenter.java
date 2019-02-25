package com.example.xedierzmn.presenter;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.xedierzmn.bean.ShowBean;
import com.example.xedierzmn.bean.ShowXiang;
import com.example.xedierzmn.contract.ShowContract;
import com.example.xedierzmn.molder.ShowMolder;
import com.example.xedierzmn.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class ShowPresenter extends ShowContract.ShowPresenter {

    private ShowMolder showMolder;
    private ShowContract.ShowView showView;

    public ShowPresenter( ShowContract.ShowView showView) {
        showMolder=new ShowMolder();
        this.showView = showView;
    }

    @Override
    public void SosPresenter(HashMap<String, String> params) {
        showMolder.SosMolder(params, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                if (showView!=null){
                    ShowBean showBean = new Gson().fromJson(result, ShowBean.class);
                    showView.onSuccess(showBean);
                }
            }

            @Override
            public void onFailUre(String msg) {
                if (showView!=null){
                    showView.onFailUre(msg);
                }
            }
        });
    }

    @Override
    public void XianPresenter(HashMap<String, String> params) {
        showMolder.XianMolder(params, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                if (showView!=null){
                    ShowXiang showXiang = new Gson().fromJson(result, ShowXiang.class);
                    showView.onSuccessX(showXiang);
                }
            }

            @Override
            public void onFailUre(String msg) {
                if (showView!=null){
                    showView.onFailUre(msg);
                }
            }
        });
    }
}

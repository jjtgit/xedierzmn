package com.example.xedierzmn.contract;

import com.example.xedierzmn.bean.ShowBean;
import com.example.xedierzmn.bean.ShowGwc;
import com.example.xedierzmn.bean.ShowTjgwc;
import com.example.xedierzmn.bean.ShowXiang;
import com.example.xedierzmn.net.RequestCallback;

import java.util.HashMap;

public interface ShowContract {
    public abstract class ShowPresenter{
        public abstract void SosPresenter(HashMap<String ,String>params);
        public abstract void XianPresenter(HashMap<String,String>params);
        public abstract void TjgwcPresenter(HashMap<String,String>params);
        public abstract void GwcPresenter(HashMap<String,String>params);
    }
    interface ShowMolder{
        void SosMolder(HashMap<String ,String>params, RequestCallback callback);
        void XianMolder(HashMap<String ,String>params, RequestCallback callback);
        void TjgwcMolder(HashMap<String ,String>params, RequestCallback callback);
        void GwcMolder(HashMap<String ,String>params, RequestCallback callback);
    }
    interface ShowView{
        void onSuccess(ShowTjgwc showTjgwc);
        void onSuccess(ShowGwc showGwc);
        void onSuccess(ShowBean showBean);
        void onSuccessX(ShowXiang showXiang);
        void onFailUre(String msg);
    }
}

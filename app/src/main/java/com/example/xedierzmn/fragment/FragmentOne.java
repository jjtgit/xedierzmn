package com.example.xedierzmn.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xedierzmn.R;
import com.example.xedierzmn.activity.SHowXiangActivity;
import com.example.xedierzmn.adapter.SosAdapter;
import com.example.xedierzmn.bean.ShowBean;
import com.example.xedierzmn.bean.ShowXiang;
import com.example.xedierzmn.contract.ShowContract;
import com.example.xedierzmn.presenter.ShowPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentOne extends BaseFragment implements ShowContract.ShowView,SosAdapter.onClickLister {

    private ShowPresenter showPresenter;
    @BindView(R.id.xrv)
    XRecyclerView xrv;
    @BindView(R.id.gimg)
    ImageView gimg;
    @BindView(R.id.simg)
    ImageView simg;
    /*@BindView(R.id.ss)
    Button ss;*/
    @BindView(R.id.sos)
    EditText sos;
    private String page="1";
    private String count="20";
    private SosAdapter sosAdapter;

    @Override
    protected int getlayout() {
        return R.layout.fragmentone;
    }

    @Override
    protected void initView(View view) {
        showPresenter = new ShowPresenter(this);
        sosAdapter = new SosAdapter(getActivity());
        sosAdapter.SosAdapter(this);
        xrv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        xrv.setAdapter(sosAdapter);
    }

    @Override
    protected void initData() {

    }
    @OnClick(R.id.simg)
    public void but(View view){
        String string = sos.getText().toString();
        HashMap<String,String>params=new HashMap<>();
        params.put("keyword",string);
        params.put("page",page);
        params.put("count",count);
        showPresenter.SosPresenter(params);
    }

    @Override
    public void onSuccess(ShowBean showBean) {
        if (showBean!=null){
            sosAdapter.setList(showBean.getResult());
        }
    }

    @Override
    public void onSuccessX(ShowXiang showXiang) {

    }

    @Override
    public void onFailUre(String msg) {

    }

    @Override
    public void onclick(int positions) {
        Toast.makeText(getActivity(),positions+"",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), SHowXiangActivity.class);
        intent.putExtra("commodityId",positions+"");
        startActivity(intent);
    }
}

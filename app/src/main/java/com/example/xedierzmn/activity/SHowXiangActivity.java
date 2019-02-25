package com.example.xedierzmn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xedierzmn.R;
import com.example.xedierzmn.bean.ShowBean;
import com.example.xedierzmn.bean.ShowXiang;
import com.example.xedierzmn.contract.ShowContract;
import com.example.xedierzmn.presenter.ShowPresenter;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SHowXiangActivity extends AppCompatActivity implements ShowContract.ShowView {

    private TextView name;
    private TextView price;
    private Button bt_gwc;
    private String id;
    private ShowPresenter showPresenter;
    private FlyBanner banner;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_xiang);
        initView();
        Intent intent=getIntent();
        id = intent.getStringExtra("commodityId");
        Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
        showPresenter = new ShowPresenter(this);
        HashMap<String,String>params=new HashMap<>();
        params.put("commodityId",id);
        showPresenter.XianPresenter(params);
    }

    @Override
    public void onSuccess(ShowBean showBean) {

    }

    @Override
    public void onSuccessX(ShowXiang showXiang) {
        String picture = showXiang.getResult().getPicture();
        String[] split = picture.split("\\,");
        List<String>list=new ArrayList<>();
        for (int i=0;i<split.length;i++){
            list.add(split[split.length-i-1]);
        }
        banner.setImagesUrl(list);
        name.setText(showXiang.getResult().getCategoryName());
        price.setText("￥："+showXiang.getResult().getPrice()+"");

        web.setWebChromeClient(new WebChromeClient());
        web.loadDataWithBaseURL(null, showXiang.getResult().getDetails(), "text/html", "utf-8", null);
    }

    @Override
    public void onFailUre(String msg) {
        Toast.makeText(this,"请求失败",Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        web = (WebView) findViewById(R.id.web);
        banner = (FlyBanner) findViewById(R.id.banner);
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        bt_gwc = (Button) findViewById(R.id.bt_gwc);
        bt_gwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SHowXiangActivity.this,GwcActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

}

package com.example.xedierzmn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xedierzmn.R;
import com.example.xedierzmn.api.Api;
import com.example.xedierzmn.bean.ShowBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SosAdapter extends XRecyclerView.Adapter<SosAdapter.HolderView>{
    private List<ShowBean.ResultBean> list;
    private Context context;

    public SosAdapter( Context context) {
        list=new ArrayList<>();
        this.context = context;
    }

    public void setList(List<ShowBean.ResultBean> list) {
       if (list!=null){
           this.list = list;
           notifyDataSetChanged();
       }
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.home_sos_item,parent,false);
        HolderView holderView = new HolderView(view);
        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, final int position) {
        Uri uri=Uri.parse(list.get(position).getMasterPic());
        DraweeController controller=Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.image.setController(controller);
        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText("￥："+list.get(position).getPrice()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickLister!=null){
                    onClickLister.onclick(list.get(position).getCommodityId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size()>0?list.size():0;
    }

    public class HolderView extends RecyclerView.ViewHolder {

        private SimpleDraweeView image;
        private TextView name,price;
        public HolderView(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
        }
    }
    private onClickLister onClickLister;

    public void SosAdapter(onClickLister onClickLister) {
        this.onClickLister = onClickLister;
    }

    public interface onClickLister{
        void onclick(int positions);
    }
}

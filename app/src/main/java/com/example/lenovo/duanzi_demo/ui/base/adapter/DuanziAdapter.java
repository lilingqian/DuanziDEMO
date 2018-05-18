package com.example.lenovo.duanzi_demo.ui.base.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.duanzi_demo.R;
import com.example.lenovo.duanzi_demo.bean.DuanziBean;
import com.example.lenovo.duanzi_demo.ui.DuanziFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.xml.transform.Templates;

public class DuanziAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DuanziBean.DataBean> list;
    private LayoutInflater inflater;

    public DuanziAdapter(Context context ,List<DuanziBean.DataBean> list){

        this.context =context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.xrv_layout, parent, false);
        return new MyViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



        DuanziBean.DataBean dataBean = list.get(position);

        MyViewHodel myViewHodel= (MyViewHodel) holder;
        String imgUrls = (String) dataBean.getImgUrls();

        String imUrl="";

        if (!TextUtils.isEmpty(imgUrls)){
                imUrl= imgUrls.split("\\|")[0];
        }

        myViewHodel.iv.setImageURI(imUrl);
        myViewHodel.tvName.setText( dataBean.getUser().getNickname()+"");
        myViewHodel.tvTime.setText(dataBean.getCreateTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private class MyViewHodel extends RecyclerView.ViewHolder{

        private final SimpleDraweeView iv;
        private final TextView tvName;
        private final TextView tvTime;
        public MyViewHodel(View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

    public  void  refresh(List<DuanziBean.DataBean> templist){

        //清楚原先集合
        this.list.clear();
         this.list.addAll(templist);
         notifyDataSetChanged();
    }

    public void  loadMore(List<DuanziBean.DataBean> templist){

        this.list.addAll(templist);
        notifyDataSetChanged();
    }

    public  int getListNum(){
        return list.size();

    }
}

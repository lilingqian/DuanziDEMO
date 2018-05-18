package com.example.lenovo.duanzi_demo.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.example.lenovo.duanzi_demo.R;
import com.example.lenovo.duanzi_demo.bean.DuanziBean;


import com.example.lenovo.duanzi_demo.component.DaggerHttpComponent;
import com.example.lenovo.duanzi_demo.ui.base.BaseFragment;
import com.example.lenovo.duanzi_demo.ui.base.adapter.DuanziAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DuanziFragment  extends BaseFragment<DuanziPresenter> implements DuanziContract.View{

    private XRecyclerView xlv;
    private List<DuanziBean.DataBean> list = new ArrayList<>();

    private boolean isRefresh = true;
    private int page = 1;

   private  DuanziAdapter duanziAdapter;




    @Override
    public int getContentLayout() {

        return R.layout.fragment_duanzi;
    }

    @Override
    public void initView(View view) {

        xlv = view.findViewById(R.id.xrl);
        xlv.setLayoutManager(new LinearLayoutManager(getContext()));

        duanziAdapter = new DuanziAdapter(getContext(), list);
        xlv.setAdapter(duanziAdapter);
        mPresenter.getDuanzi(page+"");

        //设置刷新和加载和更多

        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
            page=1;
            isRefresh=true;
            mPresenter.getDuanzi(page+"");

            }

            @Override
            public void onLoadMore() {
                //加载更多
                page++;
                isRefresh=false;
                mPresenter.getDuanzi(page+"");

            }
        });



    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);


    }

    @Override
    public void onSuccess(List<DuanziBean.DataBean> list) {


        //刷新和加载更多
        
           if (isRefresh){
               duanziAdapter.refresh(list);
               //关闭刷新
               xlv.refreshComplete();
           } else {

               duanziAdapter.loadMore(list);
               //结束加载更多

               int listNum = duanziAdapter.getListNum();
                xlv.refreshComplete();
               int size=list.size();
               int count=listNum+size;

               if (count>=20){
                         xlv.setLoadingMoreEnabled(false);
                   

               }

               
           }



    }
}

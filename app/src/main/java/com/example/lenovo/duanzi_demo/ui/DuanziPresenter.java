package com.example.lenovo.duanzi_demo.ui;

import com.example.lenovo.duanzi_demo.bean.DuanziBean;
import com.example.lenovo.duanzi_demo.net.DuanziApi;
import com.example.lenovo.duanzi_demo.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DuanziPresenter extends BasePresenter<DuanziContract.View> implements  DuanziContract.Presnter {

    private DuanziApi duanziApi;

    @Inject
    public  DuanziPresenter(DuanziApi duanziApi){

        this.duanziApi=duanziApi;

    }
    @Override
    public void getDuanzi(String page) {

        duanziApi.getDuanzi(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//               .map(new Function<DuanziBean, List<DuanziBean.DataBean>>() {
//                   @Override
//                   public List<DuanziBean.DataBean> apply(DuanziBean duanziBean) throws Exception {
//                       return null;
//                   }
//               }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<DuanziBean.DataBean>>() {
//                    @Override
//                    public void accept(List<DuanziBean.DataBean> dataBeans) throws Exception {
//                        mView.onSuccess(dataBeans);
//                    }
//                });
        .map(new Function<DuanziBean, List<DuanziBean.DataBean>>() {
            @Override
            public List<DuanziBean.DataBean> apply(DuanziBean duanziBean) throws Exception {
                return duanziBean.getData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DuanziBean.DataBean>>() {
                    @Override
                    public void accept(List<DuanziBean.DataBean> dataBeans) throws Exception {
                        mView.onSuccess(dataBeans);
                    }
                });


    }
}

package com.example.lenovo.duanzi_demo.ui.base;

import com.example.lenovo.duanzi_demo.ui.base.BaseContract;

public class BasePresenter<T extends  BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    //表示子类可以调用
   protected T mView;

    @Override
    public void attchView(T view) {


       if (view !=null){

           this.mView=view;


       }

    }

    @Override
    public void detachView() {

        if (mView!=null){
           this.mView=null;

        }
    }
}

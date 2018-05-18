package com.example.lenovo.duanzi_demo.ui.base;

public interface BaseContract {

    interface BasePresenter<T extends BaseView>{

        void  attchView(T view);
        void  detachView();
    }

    interface  BaseView{

        void  showLoading();

        void dismissLoading();

    }
}

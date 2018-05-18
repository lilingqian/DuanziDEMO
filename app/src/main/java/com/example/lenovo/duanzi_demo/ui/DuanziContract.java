package com.example.lenovo.duanzi_demo.ui;

import com.example.lenovo.duanzi_demo.bean.DuanziBean;
import com.example.lenovo.duanzi_demo.ui.base.BaseContract;

import java.util.List;

public interface DuanziContract {
    interface  View extends BaseContract.BaseView{


        void onSuccess(List<DuanziBean.DataBean> dataBeans);

    }
    interface  Presnter extends BaseContract.BasePresenter<View>{
        void getDuanzi(String page);
    }

}

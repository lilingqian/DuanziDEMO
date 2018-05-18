package com.example.lenovo.duanzi_demo.component;

import com.example.lenovo.duanzi_demo.module.HttpModule;
import com.example.lenovo.duanzi_demo.ui.DuanziFragment;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {

    void inject(DuanziFragment duanziFragment);


}

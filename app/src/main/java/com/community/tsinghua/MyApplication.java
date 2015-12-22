package com.community.tsinghua;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by LG on 2015-11-30.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this, "cc91440359f99ac8888a1867cfff71f9");
    }
}


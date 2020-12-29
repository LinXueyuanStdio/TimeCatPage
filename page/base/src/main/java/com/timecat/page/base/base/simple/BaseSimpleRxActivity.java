package com.timecat.page.base.base.simple;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.timecat.page.base.base.theme.BaseRxActivity;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/10
 * @description 友好的配置式代码风格
 * @usage 单功能页面 可直接继承
 */
public abstract class BaseSimpleRxActivity extends BaseRxActivity {

    protected void bindView() {
    }

    @LayoutRes
    protected abstract int layout();

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layout() != 0) {
            setContentView(layout());
            bindView();
        }
    }
}

package com.timecat.page.base.base.simple;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.timecat.page.base.base.theme.AbsRxActivity;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/6/6
 * @description null
 * @usage null
 */
public abstract class BaseSimpleActivity extends AbsRxActivity {

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

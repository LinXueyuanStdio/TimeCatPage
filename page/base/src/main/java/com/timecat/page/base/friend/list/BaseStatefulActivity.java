package com.timecat.page.base.friend.list;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.gturedi.views.StatefulLayout;
import com.timecat.page.base.R;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/6/8
 * @description StatefulLayout
 * @usage null
 */
public abstract class BaseStatefulActivity extends BaseBackgroundActivity {
    @Nullable
    public StatefulLayout mStatefulLayout;

    @Override
    protected int layout() {
        return R.layout.base_blur_toolbar_stateful_container;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStatefulLayout = findViewById(R.id.ll_stateful);
    }
}

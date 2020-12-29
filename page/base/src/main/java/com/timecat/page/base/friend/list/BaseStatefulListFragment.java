package com.timecat.page.base.friend.list;

import android.view.View;

import com.gturedi.views.StatefulLayout;
import com.timecat.page.base.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/24
 * @description Theme -> Event -> LazyLoad -> Simple -> List -> Stateful
 * @usage StatefulLayout : R.id.ll_stateful
 */
public abstract class BaseStatefulListFragment extends BaseListFragment {
    public StatefulLayout mStatefulLayout;

    @Override
    protected int layout() {
        return R.layout.base_rv_empty_trans;
    }

    @Override
    protected void bindView(@NotNull View view) {
        super.bindView(view);
        mStatefulLayout = view.findViewById(R.id.ll_stateful);
    }

}

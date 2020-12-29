package com.timecat.page.base.friend.toolbar;

import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timecat.page.base.R;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2019-07-12
 * @description RecyclerView : R.id.rv
 * @usage 无刷新的、有状态的列表
 */
public abstract class BaseListActivity extends BaseToolbarActivity {
    public RecyclerView mRecyclerView;

    @Override
    protected int layout() {
        return R.layout.base_toolbar_rv_empty_bg;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true); //Size of RV will not change
        LayoutAnimationController animationController =
                AnimationUtils.loadLayoutAnimation(this, R.anim.layout_on_load);
        mRecyclerView.setLayoutAnimation(animationController);
        mRecyclerView.setAdapter(getAdapter());
        loadData();
    }

    @NonNull
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @NonNull
    protected abstract RecyclerView.Adapter<? extends RecyclerView.ViewHolder> getAdapter();

    protected abstract void loadData();
}

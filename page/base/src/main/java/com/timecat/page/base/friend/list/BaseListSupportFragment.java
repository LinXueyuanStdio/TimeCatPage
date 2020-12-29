package com.timecat.page.base.friend.list;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timecat.page.base.R;
import com.timecat.page.base.base.simple.BaseSimpleSupportFragment;

import org.jetbrains.annotations.NotNull;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/16
 * @description Theme -> Support -> Event -> LazyLoad -> Simple -> List
 * @usage RecyclerView : R.id.rv
 */
public abstract class BaseListSupportFragment extends BaseSimpleSupportFragment {

    public RecyclerView mRecyclerView;

    @Override
    protected int layout() {
        return R.layout.base_rv;
    }

    @Override
    protected void bindView(@NotNull View view) {
        super.bindView(view);
        mRecyclerView = view.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true); //Size of RV will not change
        LayoutAnimationController animationController =
                AnimationUtils.loadLayoutAnimation(_mActivity, R.anim.layout_on_load);
        mRecyclerView.setLayoutAnimation(animationController);
        mRecyclerView.setAdapter(getAdapter());
    }

    @NonNull
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(requireContext());
    }

    @NonNull
    protected abstract RecyclerView.Adapter<? extends RecyclerView.ViewHolder> getAdapter();

    @Override
    public void lazyInit() {
        loadData();
    }

    protected abstract void loadData();

}
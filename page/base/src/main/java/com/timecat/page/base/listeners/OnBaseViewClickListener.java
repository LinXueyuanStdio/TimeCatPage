package com.timecat.page.base.listeners;

import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/9/8
 * @description null
 * @usage null
 */
public interface OnBaseViewClickListener {

    void onSelectAll();

    void onDeleteSelectedItem();

    void onArchiveSelectedItem();

    void onMoveSelectedItem();

    boolean onBackPressed();

    void onViewRefreshClick();

    void onViewSortClick();

    void initSearchView(Menu menu, AppCompatActivity activity);
}
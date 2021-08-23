package com.timecat.page.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timecat.page.base.R;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/24
 * @description null
 * @usage null
 */
public class ToolbarUtils {

    /**
     * 设置标题，返回
     *
     * @param activity
     * @param title
     */
    public static void initToolbarTitleBack(final Activity activity, String title) {
        TextView tvTitle = activity.findViewById(R.id.title);
        Toolbar toolbar = activity.findViewById(R.id.toolbar);

        tvTitle.setText(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();

            }
        });
    }

    public static Toolbar initToolbarTitleNoBack(final Activity activity, String title) {
        TextView tvTitle = activity.findViewById(R.id.title);
        Toolbar toolbar = activity.findViewById(R.id.toolbar);

        tvTitle.setText(title);
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();

            }
        });


        return toolbar;
    }

    public static Toolbar initToolbarTitleBackWithSearch(final Activity activity) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();

            }
        });

        return toolbar;
    }

    public static Toolbar initToolbarTitleNoBack(View view, final Fragment fragment, String title) {
        TextView tvTitle = view.findViewById(R.id.title);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        tvTitle.setText(title);
        toolbar.setNavigationIcon(null);

        return toolbar;
    }

    public static void initTintStatusBar(View tintStatusBar, Context context) {
        ViewGroup.LayoutParams lp = tintStatusBar.getLayoutParams();
        lp.height = getStatusbarHeight(context);
        tintStatusBar.setLayoutParams(lp);
    }


    public static int getStatusbarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    public static void initToolbarNav(Toolbar toolbar, boolean isHome, View.OnClickListener listener) {
        toolbar.setContentInsetEndWithActions(0);
        toolbar.setContentInsetsRelative(0, 0);
        toolbar.setContentInsetStartWithNavigation(0);

        toolbar.setNavigationIcon(isHome ? R.drawable.ic_menu : R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(listener);
    }
}

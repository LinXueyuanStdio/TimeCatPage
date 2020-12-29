package com.timecat.page.base.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/7/10
 * @description null
 * @usage null
 */
public class PaddingToolbar extends Toolbar {
    public PaddingToolbar(Context context) {
        super(context);
    }

    public PaddingToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaddingToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * 获取状态栏高度
     */
    private int getStatusBarHeight(Context context) {
        int result = 24;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);
        } else {
            result = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    result, Resources.getSystem().getDisplayMetrics());
        }
        return result;
    }

    public void setPaddingStatusBar(Context context) {
        int h = getStatusBarHeight(context);

        getLayoutParams().height += h;
        setPadding(getPaddingLeft(), getPaddingTop() + h,
                getPaddingRight(), getPaddingBottom());
    }
}

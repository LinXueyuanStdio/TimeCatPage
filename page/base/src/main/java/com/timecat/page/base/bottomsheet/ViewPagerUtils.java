package com.timecat.page.base.bottomsheet;

import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class ViewPagerUtils {
    public static View getCurrentView(ViewPager viewPager) {
        try {
            final int currentItem = viewPager.getCurrentItem();
            for (int i = 0; i < viewPager.getChildCount(); i++) {
                final View child = viewPager.getChildAt(i);
                final ViewPager.LayoutParams layoutParams = (ViewPager.LayoutParams) child.getLayoutParams();

                Field f = layoutParams.getClass().getDeclaredField("position"); //NoSuchFieldException
                f.setAccessible(true);
                int position = (Integer) f.get(layoutParams); //IllegalAccessException

                if (!layoutParams.isDecor && currentItem == position) {
                    return child;
                }
            }
        } catch (NoSuchFieldException e) {
            Log.e("ViewPagerUtils", e.toString());
        } catch (IllegalArgumentException e) {
            Log.e("ViewPagerUtils", e.toString());
        } catch (IllegalAccessException e) {
            Log.e("ViewPagerUtils", e.toString());
        }
        return null;
    }
}

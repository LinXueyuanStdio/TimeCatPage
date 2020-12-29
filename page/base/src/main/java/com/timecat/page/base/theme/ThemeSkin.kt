package com.timecat.page.base.theme

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.text.TextUtils
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import com.timecat.page.base.R
import com.timecat.extend.arms.BaseApplication
import com.timecat.component.identity.Attr
import com.timecat.component.setting.DEF

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2019-07-28
 * @description null
 * @usage null
 */
object ThemeSkin {

    //region field

    const val LIGHT = 1
    const val DARK = 2
    const val AMLOD = 3
    const val BLUISH = 4
    const val MID_NIGHT_BLUE = 5

    const val RED = 1
    const val PINK = 2
    const val PURPLE = 3
    const val DEEP_PURPLE = 4
    const val INDIGO = 5
    const val BLUE = 6
    const val LIGHT_BLUE = 7
    const val CYAN = 8
    const val TEAL = 9
    const val GREEN = 10
    const val LIGHT_GREEN = 11
    const val LIME = 12
    const val YELLOW = 13
    const val AMBER = 14
    const val ORANGE = 15
    const val DEEP_ORANGE = 16

    @IntDef(LIGHT, DARK, AMLOD, MID_NIGHT_BLUE, BLUISH)
    @Retention(AnnotationRetention.SOURCE)
    annotation class ThemeType

    @IntDef(
        RED,
        PINK,
        PURPLE,
        DEEP_PURPLE,
        INDIGO,
        BLUE,
        LIGHT_BLUE,
        CYAN,
        TEAL,
        GREEN,
        LIGHT_GREEN,
        LIME,
        YELLOW,
        AMBER,
        ORANGE,
        DEEP_ORANGE
    )
    @Retention(AnnotationRetention.SOURCE)
    internal annotation class ThemeColor
    //endregion

    //region get
    fun isNightMode(resources: Resources): Boolean {
        @ThemeType val themeType = getThemeType(resources)
        return themeType != LIGHT
    }

    @ThemeType
    fun getThemeType(context: Context): Int {
        return getThemeType(context.resources)
    }

    @ThemeType
    fun getThemeType(): Int {
        return getThemeType(BaseApplication.getInstance().resources)
    }

    @ThemeColor
    fun getThemeColor(context: Context): Int {
        return getThemeColor(context.resources)
    }

    @ThemeType
    internal fun getThemeType(resources: Resources): Int {
        val appTheme =
            DEF.skin().getString("appTheme", resources.getString(R.string.dark_theme_mode))
        if (!TextUtils.isEmpty(appTheme)) when {
            resources.getString(R.string.dark_theme_mode)
                .equals(appTheme, ignoreCase = true) -> return DARK
            resources.getString(R.string.light_theme_mode)
                .equals(appTheme, ignoreCase = true) -> return LIGHT
            resources.getString(R.string.amlod_theme_mode)
                .equals(appTheme, ignoreCase = true) -> return AMLOD
            resources.getString(R.string.mid_night_blue_theme_mode)
                .equals(appTheme, ignoreCase = true) -> return MID_NIGHT_BLUE
            resources.getString(R.string.bluish_theme)
                .equals(appTheme, ignoreCase = true) -> return BLUISH
        }
        return LIGHT
    }

    @ThemeColor
    private fun getThemeColor(resources: Resources): Int {
        val appColor =
            DEF.skin().getString("appColor", resources.getString(R.string.light_blue_theme_mode))
        return getThemeColor(resources, appColor)
    }

    // used for color picker to get the index of the color (enum) from the name of the color
    fun getThemeColor(resources: Resources, appColor: String?): Int {
        if (!TextUtils.isEmpty(appColor)) {
            when {
                resources.getString(R.string.red_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return RED
                resources.getString(R.string.pink_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return PINK
                resources.getString(R.string.purple_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return PURPLE
                resources.getString(R.string.deep_purple_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return DEEP_PURPLE
                resources.getString(R.string.indigo_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return INDIGO
                resources.getString(R.string.blue_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return BLUE
                resources.getString(R.string.light_blue_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return LIGHT_BLUE
                resources.getString(R.string.cyan_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return CYAN
                resources.getString(R.string.teal_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return TEAL
                resources.getString(R.string.green_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return GREEN
                resources.getString(R.string.light_green_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return LIGHT_GREEN
                resources.getString(R.string.lime_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return LIME
                resources.getString(R.string.yellow_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return YELLOW
                resources.getString(R.string.amber_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return AMBER
                resources.getString(R.string.orange_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return ORANGE
                resources.getString(R.string.deep_orange_theme_mode)
                    .equals(appColor, ignoreCase = true) -> return DEEP_ORANGE
            }
        }
        return BLUE
    }

    //endregion
    fun apply(activity: Activity, isDark: Boolean) {
        if (hasTheme(activity)) {
            return
        }
        val theme = if (isDark) R.style.ThemeDark else R.style.ThemeLight
        activity.setTheme(theme)
        setTaskDescription(activity)
        applyNavBarColor(activity)
    }

    fun apply(activity: Activity) {
        if (hasTheme(activity)) {
            return
        }
        val themeMode = getThemeType(activity)
        val themeColor = getThemeColor(activity)
        activity.setTheme(getTheme(themeMode, themeColor))
        setTaskDescription(activity)
        applyNavBarColor(activity)
    }

    @ColorInt
    private fun getColorAttr(context: Context, attr: Int): Int {
        val theme = context.theme
        val typedArray = theme.obtainStyledAttributes(intArrayOf(attr))
        val color = typedArray.getColor(0, Color.LTGRAY)
        typedArray.recycle()
        return color
    }

    private fun applyNavBarColor(activity: Activity) {
        if (!isNavBarTintingDisabled() && getThemeType() != LIGHT) {
            activity.window.navigationBarColor = Attr.getPrimaryColor(activity)
        }
    }

    fun isNavBarTintingDisabled(): Boolean {
        return DEF.skin().getBoolean("navigation_color", true)
    }

    fun saveNavBarTintingDisabled(enable: Boolean) {
        DEF.skin().putBoolean("navigation_color", enable)
    }

    fun applyDialogTheme(activity: AppCompatActivity) {
        val themeMode = getThemeType(activity)
        val themeColor = getThemeColor(activity)
        activity.setTheme(getDialogTheme(themeMode, themeColor))
        setTaskDescription(activity)
    }

    @StyleRes
    fun getTheme(themeMode: Int, themeColor: Int): Int {
        // I wish if I could simplify this :'( too many cases for the love of god.
        when (themeMode) {
            LIGHT -> when (themeColor) {
                RED -> return R.style.ThemeLight_Red
                PINK -> return R.style.ThemeLight_Pink
                PURPLE -> return R.style.ThemeLight_Purple
                DEEP_PURPLE -> return R.style.ThemeLight_DeepPurple
                INDIGO -> return R.style.ThemeLight_Indigo
                BLUE -> return R.style.ThemeLight
                LIGHT_BLUE -> return R.style.ThemeLight_LightBlue
                CYAN -> return R.style.ThemeLight_Cyan
                TEAL -> return R.style.ThemeLight_Teal
                GREEN -> return R.style.ThemeLight_Green
                LIGHT_GREEN -> return R.style.ThemeLight_LightGreen
                LIME -> return R.style.ThemeLight_Lime
                YELLOW -> return R.style.ThemeLight_Yellow
                AMBER -> return R.style.ThemeLight_Amber
                ORANGE -> return R.style.ThemeLight_Orange
                DEEP_ORANGE -> return R.style.ThemeLight_DeepOrange
                else -> return R.style.ThemeLight
            }
            DARK -> when (themeColor) {
                RED -> return R.style.ThemeDark_Red
                PINK -> return R.style.ThemeDark_Pink
                PURPLE -> return R.style.ThemeDark_Purple
                DEEP_PURPLE -> return R.style.ThemeDark_DeepPurple
                INDIGO -> return R.style.ThemeDark_Indigo
                BLUE -> return R.style.ThemeDark
                LIGHT_BLUE -> return R.style.ThemeDark_LightBlue
                CYAN -> return R.style.ThemeDark_Cyan
                GREEN -> return R.style.ThemeDark_Green
                TEAL -> return R.style.ThemeDark_Teal
                LIGHT_GREEN -> return R.style.ThemeDark_LightGreen
                LIME -> return R.style.ThemeDark_Lime
                YELLOW -> return R.style.ThemeDark_Yellow
                AMBER -> return R.style.ThemeDark_Amber
                ORANGE -> return R.style.ThemeDark_Orange
                DEEP_ORANGE -> return R.style.ThemeDark_DeepOrange
                else -> return R.style.ThemeDark
            }
            AMLOD -> when (themeColor) {
                RED -> return R.style.ThemeAmlod_Red
                PINK -> return R.style.ThemeAmlod_Pink
                PURPLE -> return R.style.ThemeAmlod_Purple
                DEEP_PURPLE -> return R.style.ThemeAmlod_DeepPurple
                INDIGO -> return R.style.ThemeAmlod_Indigo
                BLUE -> return R.style.ThemeAmlod
                LIGHT_BLUE -> return R.style.ThemeAmlod_LightBlue
                CYAN -> return R.style.ThemeAmlod_Cyan
                TEAL -> return R.style.ThemeAmlod_Teal
                GREEN -> return R.style.ThemeAmlod_Green
                LIGHT_GREEN -> return R.style.ThemeAmlod_LightGreen
                LIME -> return R.style.ThemeAmlod_Lime
                YELLOW -> return R.style.ThemeAmlod_Yellow
                AMBER -> return R.style.ThemeAmlod_Amber
                ORANGE -> return R.style.ThemeAmlod_Orange
                DEEP_ORANGE -> return R.style.ThemeAmlod_DeepOrange
                else -> return R.style.ThemeAmlod
            }
            MID_NIGHT_BLUE -> when (themeColor) {
                RED -> return R.style.ThemeMidnight_Red
                PINK -> return R.style.ThemeMidnight_Pink
                PURPLE -> return R.style.ThemeMidnight_Purple
                DEEP_PURPLE -> return R.style.ThemeMidnight_DeepPurple
                INDIGO -> return R.style.ThemeMidnight_Indigo
                BLUE -> return R.style.ThemeMidnight
                LIGHT_BLUE -> return R.style.ThemeMidnight_LightBlue
                CYAN -> return R.style.ThemeMidnight_Cyan
                TEAL -> return R.style.ThemeMidnight_Teal
                GREEN -> return R.style.ThemeMidnight_Green
                LIGHT_GREEN -> return R.style.ThemeMidnight_LightGreen
                LIME -> return R.style.ThemeMidnight_Lime
                YELLOW -> return R.style.ThemeMidnight_Yellow
                AMBER -> return R.style.ThemeMidnight_Amber
                ORANGE -> return R.style.ThemeMidnight_Orange
                DEEP_ORANGE -> return R.style.ThemeMidnight_DeepOrange
                else -> return R.style.ThemeMidnight
            }
            BLUISH -> when (themeColor) {
                RED -> return R.style.ThemeBluish_Red
                PINK -> return R.style.ThemeBluish_Pink
                PURPLE -> return R.style.ThemeBluish_Purple
                DEEP_PURPLE -> return R.style.ThemeBluish_DeepPurple
                INDIGO -> return R.style.ThemeBluish_Indigo
                BLUE -> return R.style.ThemeBluish
                LIGHT_BLUE -> return R.style.ThemeBluish_LightBlue
                CYAN -> return R.style.ThemeBluish_Cyan
                TEAL -> return R.style.ThemeBluish_Teal
                GREEN -> return R.style.ThemeBluish_Green
                LIGHT_GREEN -> return R.style.ThemeBluish_LightGreen
                LIME -> return R.style.ThemeBluish_Lime
                YELLOW -> return R.style.ThemeBluish_Yellow
                AMBER -> return R.style.ThemeBluish_Amber
                ORANGE -> return R.style.ThemeBluish_Orange
                DEEP_ORANGE -> return R.style.ThemeBluish_DeepOrange
                else -> return R.style.ThemeBluish
            }
        }
        return R.style.ThemeLight
    }

    fun setTheme(theme: String) = DEF.skin().putString("appTheme", theme)

    @JvmOverloads
    fun setTheme(
        @ThemeType themeType: Int,
        resources: Resources = BaseApplication.getInstance().resources
    ) {
        val theme = when (themeType) {
            DARK -> resources.getString(R.string.dark_theme_mode)
            LIGHT -> resources.getString(R.string.light_theme_mode)
            AMLOD -> resources.getString(R.string.amlod_theme_mode)
            MID_NIGHT_BLUE -> resources.getString(R.string.mid_night_blue_theme_mode)
            BLUISH -> resources.getString(R.string.bluish_theme)
            else -> resources.getString(R.string.light_theme_mode)
        }
        setTheme(theme)
    }

    @StyleRes
    private fun getDialogTheme(themeMode: Int, themeColor: Int): Int {
        when (themeMode) {
            LIGHT -> when (themeColor) {
                RED -> return R.style.DialogThemeLight_Red
                PINK -> return R.style.DialogThemeLight_Pink
                PURPLE -> return R.style.DialogThemeLight_Purple
                DEEP_PURPLE -> return R.style.DialogThemeLight_DeepPurple
                INDIGO -> return R.style.DialogThemeLight_Indigo
                BLUE -> return R.style.DialogThemeLight
                LIGHT_BLUE -> return R.style.DialogThemeLight_LightBlue
                CYAN -> return R.style.DialogThemeLight_Cyan
                TEAL -> return R.style.DialogThemeLight_Teal
                GREEN -> return R.style.DialogThemeLight_Green
                LIGHT_GREEN -> return R.style.DialogThemeLight_LightGreen
                LIME -> return R.style.DialogThemeLight_Lime
                YELLOW -> return R.style.DialogThemeLight_Yellow
                AMBER -> return R.style.DialogThemeLight_Amber
                ORANGE -> return R.style.DialogThemeLight_Orange
                DEEP_ORANGE -> return R.style.DialogThemeLight_DeepOrange
                else -> return R.style.DialogThemeLight
            }
            DARK -> when (themeColor) {
                RED -> return R.style.DialogThemeDark_Red
                PINK -> return R.style.DialogThemeDark_Pink
                PURPLE -> return R.style.DialogThemeDark_Purple
                DEEP_PURPLE -> return R.style.DialogThemeDark_DeepPurple
                INDIGO -> return R.style.DialogThemeDark_Indigo
                BLUE -> return R.style.DialogThemeDark
                LIGHT_BLUE -> return R.style.DialogThemeDark_LightBlue
                CYAN -> return R.style.DialogThemeDark_Cyan
                TEAL -> return R.style.DialogThemeDark_Teal
                GREEN -> return R.style.DialogThemeDark_Green
                LIGHT_GREEN -> return R.style.DialogThemeDark_LightGreen
                LIME -> return R.style.DialogThemeDark_Lime
                YELLOW -> return R.style.DialogThemeDark_Yellow
                AMBER -> return R.style.DialogThemeDark_Amber
                ORANGE -> return R.style.DialogThemeDark_Orange
                DEEP_ORANGE -> return R.style.DialogThemeDark_DeepOrange
                else -> return R.style.DialogThemeDark
            }
            AMLOD -> when (themeColor) {
                RED -> return R.style.DialogThemeAmlod_Red
                PINK -> return R.style.DialogThemeAmlod_Pink
                PURPLE -> return R.style.DialogThemeAmlod_Purple
                DEEP_PURPLE -> return R.style.DialogThemeAmlod_DeepPurple
                INDIGO -> return R.style.DialogThemeAmlod_Indigo
                BLUE -> return R.style.DialogThemeAmlod
                LIGHT_BLUE -> return R.style.DialogThemeAmlod_LightBlue
                CYAN -> return R.style.DialogThemeAmlod_Cyan
                TEAL -> return R.style.DialogThemeAmlod_Teal
                GREEN -> return R.style.DialogThemeAmlod_Green
                LIGHT_GREEN -> return R.style.DialogThemeAmlod_LightGreen
                LIME -> return R.style.DialogThemeAmlod_Lime
                YELLOW -> return R.style.DialogThemeAmlod_Yellow
                AMBER -> return R.style.DialogThemeAmlod_Amber
                ORANGE -> return R.style.DialogThemeAmlod_Orange
                DEEP_ORANGE -> return R.style.DialogThemeAmlod_DeepOrange
                else -> return R.style.DialogThemeAmlod
            }
            MID_NIGHT_BLUE -> when (themeColor) {
                RED -> return R.style.DialogThemeMidnight_Red
                PINK -> return R.style.DialogThemeMidnight_Pink
                PURPLE -> return R.style.DialogThemeMidnight_Purple
                DEEP_PURPLE -> return R.style.DialogThemeMidnight_DeepPurple
                INDIGO -> return R.style.DialogThemeMidnight_Indigo
                BLUE -> return R.style.DialogThemeMidnight
                LIGHT_BLUE -> return R.style.DialogThemeMidnight_LightBlue
                CYAN -> return R.style.DialogThemeMidnight_Cyan
                TEAL -> return R.style.DialogThemeMidnight_Teal
                GREEN -> return R.style.DialogThemeMidnight_Green
                LIGHT_GREEN -> return R.style.DialogThemeMidnight_LightGreen
                LIME -> return R.style.DialogThemeMidnight_Lime
                YELLOW -> return R.style.DialogThemeMidnight_Yellow
                AMBER -> return R.style.DialogThemeMidnight_Amber
                ORANGE -> return R.style.DialogThemeMidnight_Orange
                DEEP_ORANGE -> return R.style.DialogThemeMidnight_DeepOrange
                else -> return R.style.DialogThemeLight
            }
            BLUISH -> when (themeColor) {
                RED -> return R.style.DialogThemeBluish_Red
                PINK -> return R.style.DialogThemeBluish_Pink
                PURPLE -> return R.style.DialogThemeBluish_Purple
                DEEP_PURPLE -> return R.style.DialogThemeBluish_DeepPurple
                INDIGO -> return R.style.DialogThemeBluish_Indigo
                BLUE -> return R.style.DialogThemeBluish
                LIGHT_BLUE -> return R.style.DialogThemeBluish_LightBlue
                CYAN -> return R.style.DialogThemeBluish_Cyan
                TEAL -> return R.style.DialogThemeBluish_Teal
                GREEN -> return R.style.DialogThemeBluish_Green
                LIGHT_GREEN -> return R.style.DialogThemeBluish_LightGreen
                LIME -> return R.style.DialogThemeBluish_Lime
                YELLOW -> return R.style.DialogThemeBluish_Yellow
                AMBER -> return R.style.DialogThemeBluish_Amber
                ORANGE -> return R.style.DialogThemeBluish_Orange
                DEEP_ORANGE -> return R.style.DialogThemeBluish_DeepOrange
                else -> return R.style.DialogThemeBluish
            }
        }
        return R.style.DialogThemeLight
    }

    private fun setTaskDescription(activity: Activity) {
        activity.setTaskDescription(
            ActivityManager.TaskDescription(
                activity.getString(R.string.app_name),
                BitmapFactory.decodeResource(activity.resources, R.mipmap.ic_launcher),
                Attr.getPrimaryColor(activity)
            )
        )
    }

    private fun hasTheme(activity: Activity) =
        activity.javaClass.name.contains("LoginChooserActivity")
                || activity.javaClass.name.contains("LoginActivity")
                || activity.javaClass.name.contains("com.time")
}
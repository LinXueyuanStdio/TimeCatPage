package com.timecat.page.base.extension

import android.content.Context
import com.timecat.component.identity.Attr

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/7/25
 * @description 主题相关
 * @usage null
 */
val Context.primaryColor get() = Attr.getPrimaryColor(this)
val Context.primaryDarkColor get() = Attr.getPrimaryDarkColor(this)
val Context.primaryTextColor get() = Attr.getPrimaryTextColor(this)
val Context.primaryTextColorReverse get() = Attr.getPrimaryTextColorReverse(this)
val Context.secondaryTextColor get() = Attr.getSecondaryTextColor(this)
val Context.tertiaryTextColor get() = Attr.getTertiaryTextColor(this)
val Context.accentColor get() = Attr.getAccentColor(this)
val Context.iconColor get() = Attr.getIconColor(this)
val Context.windowBackground get() = Attr.getWindowBackground(this)
val Context.drawerBackground get() = Attr.getDrawerBackground(this)
val Context.miniBackground get() = Attr.getMiniBackground(this)
val Context.listDivider get() = Attr.getListDivider(this)
val Context.cardBackground get() = Attr.getCardBackground(this)
val Context.textBackground get() = Attr.getTextBackground(this)
val Context.backgroundColor get() = Attr.getBackgroundColor(this)
val Context.backgroundDarkColor get() = Attr.getBackgroundDarkColor(this)
val Context.backgroundReverseColor get() = Attr.getBackgroundReverseColor(this)
val Context.patchAdditionColor get() = Attr.getPatchAdditionColor(this)
val Context.patchDeletionColor get() = Attr.getPatchDeletionColor(this)
val Context.patchRefColor get() = Attr.getPatchRefColor(this)






















package com.timecat.page.base.listeners;

/**
 * @author Jecelyin Peng <jecelyin@gmail.com>
 */

public interface ProgressInterface {
    void addOnDismissListener(OnDismissListener listener);
    void removeOnDismissListener(OnDismissListener listener);
    void setMessage(CharSequence message);
    void show();
    void dismiss();
}

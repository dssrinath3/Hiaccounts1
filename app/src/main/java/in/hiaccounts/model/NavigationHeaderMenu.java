package in.hiaccounts.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Prateek on 5/12/2017.
 */

public class NavigationHeaderMenu {

    String menuTitle;
    Drawable menuResId;

    public NavigationHeaderMenu(String menuTitle, Drawable menuResId) {
        this.menuTitle = menuTitle;
        this.menuResId = menuResId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public Drawable getMenuResId() {
        return menuResId;
    }

    public void setMenuResId(Drawable menuResId) {
        this.menuResId = menuResId;
    }

    @Override
    public String toString() {
        return menuTitle;
    }


}

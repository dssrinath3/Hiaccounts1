package in.hiaccounts.model;

/**
 * Created by Preateek on 7/10/2017.
 */

public class GridMenu {

    String menuTitle;
    int resId;

    public GridMenu(String menuTitle, int resId) {
        this.menuTitle = menuTitle;
        this.resId = resId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

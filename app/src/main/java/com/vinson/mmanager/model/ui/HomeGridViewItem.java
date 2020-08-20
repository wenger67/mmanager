package com.vinson.mmanager.model.ui;

import com.mikepenz.iconics.typeface.IIcon;

public class HomeGridViewItem {
    private IIcon icon;
    private String title;

    public HomeGridViewItem(IIcon icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public IIcon getIcon() {
        return icon;
    }

    public void setIcon(IIcon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.vinson.mmanager.model.ui;

import com.mikepenz.iconics.typeface.IIcon;
import com.vinson.mmanager.model.annotation.ModuleType;

public class HomeGridViewItem {
    private IIcon icon;
    private String title;
    @ModuleType
    private int type;

    public HomeGridViewItem(IIcon icon, String title, int type) {
        this.icon = icon;
        this.title = title;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

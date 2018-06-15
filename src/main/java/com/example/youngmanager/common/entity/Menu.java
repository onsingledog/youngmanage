package com.example.youngmanager.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Menu {
    private String menuId;

    private String menuTitle;

    private String menuIconClass;

    private String menuIconUrl;

    private String menuUrl;

    private Integer menuSort;

    private String menuLevel;

    private String menuParentId;

    private String menuState;

    private String createBy;

    private Date createDate;

    private String lastUpdateBy;

    private Date lastUpdateDate;

    private List<Menu> childMenus;

    public void addChildMenu(Menu menu){
        if(this.childMenus == null )
            this.childMenus = new ArrayList<>();
        this.childMenus.add(menu);
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle == null ? null : menuTitle.trim();
    }

    public String getMenuIconClass() {
        return menuIconClass;
    }

    public void setMenuIconClass(String menuIconClass) {
        this.menuIconClass = menuIconClass == null ? null : menuIconClass.trim();
    }

    public String getMenuIconUrl() {
        return menuIconUrl;
    }

    public void setMenuIconUrl(String menuIconUrl) {
        this.menuIconUrl = menuIconUrl == null ? null : menuIconUrl.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel == null ? null : menuLevel.trim();
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId == null ? null : menuParentId.trim();
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState == null ? null : menuState.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
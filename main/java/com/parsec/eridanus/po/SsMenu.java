package com.parsec.eridanus.po;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kevin on 2016/11/28.
 */
public class SsMenu implements Comparator<SsMenu>{
    private Integer id;
    private String url;
    private String urlName;
    private String icon = "fa-bookmark";
    private Integer parentId = 0;
    private Integer isDelete = 1;//0 delete,1 undelete
//    private SsMenu parentMenu;
//    private List<SsMenu> ssMenus = new ArrayList<>();
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "SsMenu{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", urlName='" + urlName + '\'' +
                ", icon='" + icon + '\'' +
                ", parentId=" + parentId +
                ", isDelete=" + isDelete +
                '}';
    }

    @Override
    public int compare(SsMenu o1, SsMenu o2) {
        if (o1.getId()>o2.getId()){
            return 1;
        }
        else if (o1.getId()<o2.getId()){
            return -1;
        }
        return 0;
    }
}

package com.gameley.vo;

import java.util.List;

public class Model {
    String menuid;
    String name;
    String url;
    List<SubMenu> list;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SubMenu> getList() {
        return list;
    }

    public void setList(List<SubMenu> list) {
        this.list = list;
    }
}

package com.models.web.tag;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/4.
 */
public class TagSelectItem implements Serializable {
    private int id;
    private String name;

    public TagSelectItem() {

    }

    public TagSelectItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

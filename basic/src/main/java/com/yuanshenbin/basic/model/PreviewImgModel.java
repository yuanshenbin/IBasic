package com.yuanshenbin.basic.model;

import java.io.Serializable;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2021-11-08
 * desc   : 预览大图
 */
public class PreviewImgModel implements Serializable {
    private List<String> path;
    private int index;

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

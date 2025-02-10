package com.yuanshenbin.basic.model;

import java.io.Serializable;

/**
 * author : yuanshenbin
 * time   : 2018/11/11
 * desc   : 文件压缩模型
 */

public class FileCompressModel  implements Serializable{
    private long size;//文件大小
    private long oldSize;//旧文件大小
    private String path;//文件路径
    private String oldPath;//旧文件路径

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getOldSize() {
        return oldSize;
    }

    public void setOldSize(long oldSize) {
        this.oldSize = oldSize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    @Override
    public String toString() {
        return "FileCompressModel{" +
                "size=" + size +
                ", oldSize=" + oldSize +
                ", path='" + path + '\'' +
                ", oldPath='" + oldPath + '\'' +
                '}';
    }
}

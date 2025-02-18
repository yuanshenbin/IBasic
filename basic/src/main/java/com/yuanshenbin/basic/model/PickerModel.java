package com.yuanshenbin.basic.model;

public class PickerModel {

    private String id;
    private String title;

    public PickerModel(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public PickerModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

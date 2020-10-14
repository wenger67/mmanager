package com.vinson.mmanager.model;

import com.vinson.mmanager.model.base.TimeWrapper;

public class FileUploadAndDownload {
    public int ID;
    public String CreatedAt;
    public String UpdatedAt;
    public TimeWrapper TimeWrapper;
    public String name;
    public String url;
    public String tag;
    public String key;

    @Override
    public String toString() {
        return "ExaFileUploadAndDownload{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", tag='" + tag + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

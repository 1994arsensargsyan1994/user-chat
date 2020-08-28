package com.human.userchat.servlet;

import org.apache.commons.fileupload.FileItem;

import java.util.List;

public class RequestValidator<T> {
    private T entity;
    private boolean isValid;
    private List<FileItem> fileItems;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<FileItem> getFileItems() {
        return fileItems;
    }

    public void setFileItems(List<FileItem> fileItems) {
        this.fileItems = fileItems;
    }
}

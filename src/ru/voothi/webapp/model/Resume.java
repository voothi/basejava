package ru.voothi.webapp.model;

/**
 * Initial resume class
 */
public class Resume {

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    public String toStringSuperclass() {
        return super.toString();
    }
}
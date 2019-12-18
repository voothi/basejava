package ru.voothi.webapp.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}

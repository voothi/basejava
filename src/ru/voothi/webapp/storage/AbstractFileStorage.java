package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.StorageException;
import ru.voothi.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath()
                    + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath()
                    + " is not RW access");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
//doRead abstract method
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
//as in update, only the file does not need to be created doWrite only
    }

    @Override
    protected void doDelete(File file) {
//file deletion
    }

    @Override
    protected List<Resume> doCopyAll() {
//returns a list of resumes
        return null;
    }

    @Override
    public int size() {
        return 0;
//how many files in the directory
    }

    @Override
    public void clear() {
//get and delete all files in the directory
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;
}

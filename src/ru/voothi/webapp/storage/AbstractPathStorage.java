package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.StorageException;
import ru.voothi.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not dir or RW");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return new Path(directory, uuid);
    }

    @Override
    protected boolean isExist(Path file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume resume, Path file) {
        try {
            file.createNewPath();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file" + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected Resume doGet(Path file) {
        try {
            return doRead(new BufferedInputStream(new PathInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Path read error", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path file) {
        try {
            doWrite(resume, new BufferedOutputStream(new PathOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path file) {
        if (!file.delete()) {
            throw new StorageException("Path delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        Path[] files = directory.listPaths();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (Path file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }

    @Override
    public void clear() {
        Path[] files = directory.listPaths();
        if (files != null) {
            for (Path file : files) {
                doDelete(file);
            }
        }
    }

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;
}

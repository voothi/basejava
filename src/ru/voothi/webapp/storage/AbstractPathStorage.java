package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.StorageException;
import ru.voothi.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
        return null;
    }

    @Override
    protected boolean isExist(Path file) {
        return false;
    }

    @Override
    protected void doSave(Resume resume, Path file) {

    }

    @Override
    protected Resume doGet(Path file) {
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Path file) {

    }

    @Override
    protected void  doDelete(Path file) {

    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {
        try {
            List<Path> list = Files.list(directory).collect(Collectors.toList());
            final PathConsumer action = new PathConsumer();
            list.forEach(action);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    private class PathConsumer implements Consumer<Path> {
        @Override
        public void accept(Path path) {
            doDelete(path);
        }
    }

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;
}

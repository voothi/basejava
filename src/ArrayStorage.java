import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int LENGTH = 10000;
    private static final int SIZE = 0;
    private Resume[] storage;
    private int size;

    public ArrayStorage() {
        storage = new Resume[LENGTH];
    }

    public void update(Resume resume) {
        //TODO check if Resume present
//        System.out.println("ERROR");
    }

    public void save(Resume resume) {
        //TODO check if Resume not present
        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.toString().equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        //TODO check if Resume present
        if (isElementOfArray(uuid)) {
            int index = getIndex(uuid);
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
            if (size > 0) {
                storage[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * getSize
     *
     * @return size
     */
    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, null);
        setSize(SIZE);
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    private int getIndex(String uuid) {
        int i;
        for (i = 0; i < size; i++) {
            if (uuid.toString().equals(storage[i].getUuid())) {
                return i;
            }
        }
        return i;
    }

    public boolean isElementOfArray(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.toString().equals(storage[i].toString())) {
                return true;
            }
        }
        return false;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
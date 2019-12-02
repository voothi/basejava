import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final static private int STORAGE_SIZE_CREATE = 10000;
    private Resume[] storage;
    private int counterElementsOfStorage;

    public ArrayStorage() {
        counterElementsOfStorage = 0;
        storage = new Resume[STORAGE_SIZE_CREATE];
    }

    /**
     * Inserts at the end of an array of receiving elements
     */
    void save(Resume resume) {
        storage[counterElementsOfStorage] = resume;
        counterElementsOfStorage++;
    }

    /**
     * Getting a summary from array
     */
    Resume get(String uuid) {
        for (int i = 0; i < counterElementsOfStorage; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    /**
     * After removal of the element is displaced to the left element in this position
     */
    void delete(String uuid) {
        int index = getIndex(uuid);
        Resume[] storageOut = new Resume[storage.length - 1];
        int remainingElements = storage.length - (index + 1);
        System.arraycopy(storage, 0, storageOut, 0, index);
        System.arraycopy(storage, index + 1, storageOut, index, remainingElements);
        storage = storageOut;
    }

    private int getIndex(String uuid) {
        int i;
        for (i = 0; i < counterElementsOfStorage; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return i;
    }

    /**
     * @return Size method returns the number of elements in the array,
     * occupied by objects such Resume, with sequential filling,
     * without holes (null).
     */
    int size() {
        int i;
        for (i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return i;
    }

    /**
     * Fills the storage null values
     */
    void clear() {
        Arrays.fill(storage, null);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, counterElementsOfStorage);
    }
}
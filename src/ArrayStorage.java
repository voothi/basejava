import java.util.Arrays;
import java.util.Optional;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int counterElementsOfStorage;
    private Resume[] storage;

    public ArrayStorage() {
        counterElementsOfStorage = 0;
        storage = new Resume[10];
    }

    /**
     * Вставляет в конец массива принимаемый элемент
     */
    void save(Resume resume) {
        storage[counterElementsOfStorage] = resume;
        counterElementsOfStorage++;
    }

    /**
     * Получение резюме из массива
     *
     * @param uuid принимает в качестве аргумента строку с идентификатором
     * @return объект Resume
     */
    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < counterElementsOfStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                resume = storage[i];
                break;
            }
        }
        return resume;
    }

    /**
     * После удаления элемента, происходит смещение элементов.
     *
     * @param uuid
     */
    void delete(String uuid) {

    }

    /**
     * @return Size method returns the number of elements in the array,
     * occupied by objects such Resume, with sequential filling,
     * without holes (null).
     */
    int size() {
        int sizeArrayStorageNotNull = 0;
        for (int i = 0; i < storage.length; i++) {
//            System.out.println(storage[i]);
            if (storage[i] == null) {
                continue;
            }
            sizeArrayStorageNotNull++;
        }
        return sizeArrayStorageNotNull;
    }

    void clear() {
        Arrays.fill(storage, null);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }
}
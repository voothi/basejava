import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final static private int STORAGE_SIZE_CREATE = 10;
    private int modCount = 0;
    private int counterElementsOfStorage;
    private Resume[] storage;

    public ArrayStorage() {
        counterElementsOfStorage = 0;
        storage = new Resume[STORAGE_SIZE_CREATE];
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
        for (int i = 0; i < counterElementsOfStorage; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    /**
     * После удаления элемента, происходит смещение элементов.
     *
     * @param uuid
     */
    void delete(String uuid) {
//        ArrayList<Integer> list = new ArrayList();
//        list.remove(1);
        int index = getIndex(uuid);
        modCount++;
        Resume oldValue = storage[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

//        rangeCheck(index);
//
//        modCount++;
//        E oldValue = elementData(index);
//
//        int numMoved = size - index - 1;
//        if (numMoved > 0)
//            System.arraycopy(elementData, index + 1, elementData, index,
//                    numMoved);
//        elementData[--size] = null; // clear to let GC do its work
//
//        return oldValue;
    }

//    private void rangeCheck(int index) {
//        if (index >= size)
//            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//    }
//
//    private String outOfBoundsMsg(int index) {
//        return "Index: " + index + ", Size: " + size;
//    }
//
//    E elementData(int index) {
//        return (E) elementData[index];
//    }

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
        return Arrays.copyOfRange(storage, 0, counterElementsOfStorage);
    }
}
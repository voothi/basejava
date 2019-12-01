/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    void save(Resume resume) {
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    int size() {
        return 0;
    }

    void clear() {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
            return new Resume[0];
    }
}
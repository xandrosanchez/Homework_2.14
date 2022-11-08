import java.util.Objects;

public class ObjectListImpl implements ObjectList {

    Object[] elementData;

    private int size;

    public ObjectListImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
            size = 0;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    public ObjectListImpl() {
        this.elementData = new Object[15];
        size = 0;
    }


    @Override
    public Object add(Object item) {
        return add(size, item);
    }

    @Override
    public Object add(int index, Object item) {
        if (size > elementData.length) {
            throw new IllegalArgumentException("Wrong index");
        }
        checkNotNull(item);
        checkNonNegativeIndex(index);
        checkIndex(index, false);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = item;
        size++;
        return item;
    }

    @Override
    public Object set(int index, Object item) {
        checkNotNull(item);
        checkIndex(index, true);
        checkNonNegativeIndex(index);
        return elementData[index] = item;
    }

    @Override
    public Object remove(Object item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1) {
            throw new IllegalArgumentException("Element not found");
        }
        return remove(indexForRemoving);
    }

    @Override
    public Object remove(int index) {
        checkNonNegativeIndex(index);
        checkIndex(index, true);
        Object removed = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        elementData[--size] = null;
        return removed;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Object item) {
        checkNotNull(item);
        if (Objects.equals(elementData.getClass(), Integer.class)) {
            sortInsertion((Integer[]) elementData);
            return binarySearch((Integer[]) elementData, (Integer) item);
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object item) {
        checkNotNull(item);
        if (Objects.equals(elementData.getClass(), Integer.class)) {
            sortInsertion((Integer[]) elementData);
            return binarySearch((Integer[]) elementData, (Integer) item);
        }
        int index = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Object get(int index) {
        checkNonNegativeIndex(index);
        checkIndex(index, true);
        return elementData[index];
    }

    @Override
    public boolean equals(ObjectList otherList) {
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elementData[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elementData, 0, result, 0, size);
        return result;
    }

    private void checkNotNull(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("Wrong item");
        }
    }

    private void checkIndex(int index, boolean includeEquality) {
        boolean expression = includeEquality ? index >= size : index > size;
        if (expression) {
            throw new IllegalArgumentException("Wrong index");
        }
    }

    private void checkNonNegativeIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Wrong index");
        }
    }

    private void sortInsertion(Integer[] arr) {
        System.out.println("sortInsertion");
        long start = System.currentTimeMillis();
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private Integer binarySearch(Integer[] arr, Integer element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (Objects.equals(element, arr[mid])) {
                return mid;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

}

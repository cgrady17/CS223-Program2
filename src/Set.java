import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Author: Connor P Grady
 * Born: 3/11/2015
 * Description: UWW CS223 Program 2 | Create custom data structure "Set".
 *  Similar to ArrayList, except it maintains sorted order of elements where
 *  the first element is smallest, last is largest. Additionally, no duplicates
 *  allowed. Supports: union, subtract, difference, and intersect.
 */
public class Set <E extends Comparable<E>> {
    // Define name
    private String name;

    // Initial Capacity
    private static final int INITIAL_CAPACITY = 10;

    // The current size
    private int size = 0;

    // The current capacity
    private int capacity = 0;

    // Define underlying data array
    private E[] data;

    // Default Constructor
    public Set() {
        capacity = INITIAL_CAPACITY;
        this.data = (E[]) new Comparable[capacity];
    }

    public Set(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void add (E item) {
        // Add item to set if not in the set
        // Maintain a sorted order without the use of a sort algorithm


        /*if (item.compareTo()) {

        }*/

        E[] data2 = (E[]) new Comparable[data.length + 1];
        int p = Arrays.binarySearch(data, item);
        if (p < 0) {
            p = -p - 1;
            System.arraycopy(data, 0, data2, 0, p);
            System.arraycopy(data, p, data2, p + 1, data.length - p);
            data2[p] = item;
        }

        data = data2;
    }

    public E get(int i) {
        // Return element at given position
        if (i < size) {
            return (E) data[i];
        } else {
            // throw
            return null;
        }
    }

    public E remove(E item) {
        // Remove specified item and return it
        E obj = null;
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(item)) {
                obj = data[i];
                data[i] = null;
                int tmp = i;
                while (tmp < size) {
                    data[tmp] = data[tmp+1];
                    data[tmp+1] = null;
                    tmp++;
                }
                size--;
            }
        }
        if (obj != null) {
            return obj;
        } else {
            // Throw?
            return (E) new NoSuchElementException();
        }

    }

    public int indexOf(E target) {
        /* Find target and return the index where it's found.
            Return -1 if target is not in the set. You must use the
            non-recursive binary search.
         */
        if (target == null) {
            for (int i = 0; i < size; i++) {
                if (this.data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (target.equals(this.data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}

/**
 * SI { 2, 4, 5}
 * Set parse(String s) {
 *     name =
 *     Set<Integer> set = ....
 *     while (true) {
 *     set.add(x);
 *     }
 *     return set;
 * }
 */
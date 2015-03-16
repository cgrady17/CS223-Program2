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
    /**
     * Define name variable
     */
    private String name;

    /**
     * Define static INITIAL_CAPACITY variable and initialize to 10
     */
    private static final int INITIAL_CAPACITY = 10;

    /**
     * Define size variable and initialize to 0
     */
    private int size = 0;

    /**
     * Define capacity variable and initialize to 0
     */
    private int capacity = 0;

    /**
     * Define underlying generic array
     */
    private E[] data;

    /**
     * Basic Constructor to create new Set
     */
    public Set() {
        capacity = INITIAL_CAPACITY;
        this.data = (E[]) new Comparable[capacity];
    }

    /**
     * Conditional Constructor that sets Name of Set with String argument
     * @param n Desired name of new Set as String
     */
    public Set(String n) {
        this.name = n;
    }

    /**
     * Get the name of the Set
     * @return Name of Set as String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the Set
     * @param n Desired name of Set as String
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * Add the specified item to the Set while maintaining value order
     * @param item The object to add to the Set
     */
    public void add (E item) {
        if (size > 0) {
            E[] data2 = (E[]) new Comparable[size + 1];
            int p = -1;
            //p = Arrays.binarySearch(data, item);
            if (p < 0) {
                p = -p - 1;
                System.arraycopy(data, 0, data2, 0, p);
                System.arraycopy(data, p, data2, p + 1, data.length - p);
                data2[p] = item;
                size++;
            }

            data = data2;
        } else {
            data[0] = item;
            size++;
        }


        /*int i = 0;
        while (i < size) {
            if (item.compareTo(data[i]) < 0 ) {
                i++;
            }
        }
        if (i < data.length) {
            for (int j = data.length-1; j > i; j--) {
                data[j] = data[j -1];
            }
            data[i] = item;
            size++;
        }*/

    }

    /**
     * Return the element at the specified index
     * @param i The index of the Set
     * @return The object at the specified index
     */
    public E get(int i) {
        if (i < size) {
            return (E) data[i];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Remove the specified item from the Set and return it
     * @param item The object to be removed from the Set
     * @return The object that was removed
     */
    public E remove(E item) {
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
            throw new NoSuchElementException();
        }
    }

    /**
     * Remove the object at the specified index and return it
     * @param index The index where the object to be removed exists
     * @return The object that was removed
     */
    public E remove(int index) {
        E obj = data[index];
        data[index] = null;
        // Need to rewrite to shift objects over
        // Can't leave empty object in array
        if (obj != null) {
            return obj;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Get the index of the specified object ("target")
     * @param target The object of which to find the index
     * @return The index of the specified target
     */
    public int indexOf(E target) {
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

    /**
     * Compares two sets, true if all elements are the same, otherwise false
     * @param s The second Set to compare to the calling Set
     * @return "false" if any element not same, otherwise "true"
     */
    public boolean equals(Set<E> s) {
        if (size != s.size()) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (data[i] != s.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get size of the Set
     * @return Number of elements in Set
     */
    public int size() {
        return this.data.length;
    }

    /**
     * Get formatted string of the Set's elements
     * @return String representation of the Set
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.name).append(":{");
        for (E obj : this.data) {
            s.append(obj).append(",");
        }
        s.deleteCharAt(s.length() -1);
        s.append("}");
        return String.valueOf(s);
    }
}
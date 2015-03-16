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
     * Define underlying generic array
     */
    private E[] data;

    /**
     * Basic Constructor to create new Set
     */
    public Set() {
     /**
      * Define capacity variable and initialize to INITIAL_CAPACITY
      */
        int capacity = INITIAL_CAPACITY;
        this.data = (E[]) new Comparable[capacity];
    }

    /**
     * Conditional Constructor that sets Name of Set with String argument
     * @param n Desired name of new Set as String
     */
    public Set(String n) {
        // Initialize name to the specified name
        this.name = n;
    }

    /**
     * Get the name of the Set
     * @return Name of Set as String
     */
    public String getName() {
        // Return the value of name
        return this.name;
    }

    /**
     * Set the name of the Set
     * @param n Desired name of Set as String
     */
    public void setName(String n) {
        // Initialize name to the specified name
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
        // Make sure the index is not out of bounds
        if (i < size) {
            // Return the element at the specified index
            return (E) data[i];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Remove the specified item from the Set and return it
     * @param item The object to be removed from the Set
     * @return The object that was removed
     */
    public E remove(E item) {
        // Create and initialize to null a generic object
        E obj = null;
        // Iterate over data
        for (int i = 0; i < data.length; i++) {
            // Check if the current element matches the specified item
            if (data[i].equals(item)) {
                // Set obj to the current element
                obj = data[i];
                // Set the current element to null
                data[i] = null;
                // Declare tmp to the current index
                int tmp = i;
                // Loop to move elements left
                while (tmp < size) {
                    // Element at current tmp is replaced by element at tmp + 1
                    data[tmp] = data[tmp+1];
                    // Nullify element at tmp + 1
                    data[tmp+1] = null;
                    // Step tmp by one
                    tmp++;
                }
                // Drop size by one
                size--;
            }
        }
        // If obj is not null, return it
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
        // Declare tmp to the current index
        int tmp = index;
        // Loop to move elements left
        while (tmp < size) {
            // Element at current tmp is replaced by element at tmp + 1
            data[tmp] = data[tmp+1];
            // Nullify element at tmp + 1
            data[tmp+1] = null;
            // Step tmp by one
            tmp++;
        }
        // Drop size by one
        size--;
        // Return the removed object if it's not null
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
            // This allows us to handle null objects
            for (int i = 0; i < size; i++) {
                if (this.data[i] == null) {
                    return i;
                }
            }
        } else {
            // Find the matching object and return its index (value of current i)
            for (int i = 0; i < size; i++) {
                if (target.equals(this.data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Return the union of the current Set and the specified Set
     * @param s The Set to be unified with the current Set
     * @return the union of the current Set and the specified Set
     */
    public Set<E> union(Set<E> s) {
        for (int i = 0; i < size; i++) {
            s.add(data[i]);
        }
        return s;
    }

    /**
     * Return a set of the common elements between the current Set and the specified Set
     * @param s The Set to be intersected with the current Set
     * @return the intersection of the current Set and the specified Set
     */
    public Set<E> intersect(Set<E> s) {
        for (int i = 0; i < size; i++) {
            if (!data[i].equals(s.get(i))) {
                s.remove(i);
            }
        }
        return s;
    }

    /**
     * Remove all objects from the current Set that are in the specified Set
     * @param s The Set to be subtracted from the current Set
     * @return the set with all objects in the specified Set removed
     * This seems, to me at least, the same as difference. Is that right?
     */
    public Set<E> subtract(Set<E> s) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(s.get(i))) {
                s.remove(i);
            } else {
                s.add(data[i]);
            }
        }
        return s;
    }

    /**
     * Return a set of the uncommon elements between the current Set and the specified Set
     * @param s The Set to be differentiated with the current Set
     * @return the difference of the current Set and specified Set
     */
    public Set<E> difference(Set<E> s) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(s.get(i))) {
                s.remove(i);
            } else {
                s.add(data[i]);
            }
        }
        return s;
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
            // For the element at the specified index in each array, determine if they equal
            for (int i = 0; i < size; i++) {
                if (data[i] != s.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Clear all the elements in the Set
     */
    public void clear() {
        // Replace each element in data with null
        Arrays.fill(data, null);
    }

    /**
     * Get size of the Set
     * @return Number of elements in Set
     */
    public int size() {
        // Return the length of the underlying data array
        return this.data.length;
    }

    /**
     * Get formatted string of the Set's elements
     * @return String representation of the Set
     */
    @Override
    public String toString() {
        // Create StringBuilder object
        StringBuilder s = new StringBuilder();
        // Append the Set's name followed by ":{"
        s.append(this.name).append(":{");
        // For each element in the Set, append it the StringBuilder followed by ","
        for (E obj : this.data) {
            s.append(obj).append(",");
        }
        // Delete the trailing character (will be a ",")
        s.deleteCharAt(s.length() -1);
        // Append the closing "}"
        s.append("}");
        // Return the String value of the StringBuilder
        return String.valueOf(s);
    }
}
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Author: Connor P Grady
 * Born: 3/11/2015
 * Description: Iterator for Set class, extends ListIterator
 **/
public class SetIterator<E extends ListIterator<E> & Comparable<E>> {
    // Define private Set
    private final Set<E> thisSet;
    // Define index
    private int index;

    /**
     * Primary Constructor to initialize Class
     * @param thisSet Set to be iterated
     */
    public SetIterator(Set<E> thisSet) {
        // Initialize thisSet to constructor's argument
        this.thisSet = thisSet;
        // Initialize the index to 0
        this.index = 0;
    }

    /**
     * Determine if the Set has any elements remaining in the forward direction
     * @return true if the Set continues in the forward direction
     */
    public boolean hasNext() {
        // If the size of the Set is not equal to the index, then there are more elements
        return thisSet.size() != index;
    }

    /**
     * Obtain the next element in the Set in the forward direction
     * @return The next element in the Set in the forward direction
     * @throws java.util.NoSuchElementException if there are no more elements
     */
    public E next() {
        if(hasNext()) {
            // If the Set has more elements, return the next element by calling get()
            return thisSet.get(index++);
        } else {
            throw new NoSuchElementException("There are no more elements in Set");
        }
    }

    /**
     * Find the index of the element that would be return by a call to next()
     * @return the index of the element that would be return by next()
     * @throws java.util.NoSuchElementException if there are no more elements
     */
    public int nextIndex() {
        if (hasNext()) {
            // If the Set has more elements, return the index of the previous element by calling indexOf()
            return thisSet.indexOf(next());
        } else {
            throw new NoSuchElementException("There are no more elements in Set");
        }
    }

    /**
     * Tests whether there are elements remaining in the Set in the reverse direction
     * @return true if the Set continues in the reverse direction
     */
    public boolean hasPrevious() {
        // If the index is not 0, then there are previous elements
        if (index != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtain the next element in the Set in the reverse direction
     * @return the next element in the Set in the reverse direction
     * @throws java.util.NoSuchElementException if there are no more elements
     */
    public E previous() {
        if(hasPrevious()) {
            // If the Set has previous elements, return the previous element by calling get()
            return thisSet.get(index--);
        } else {
            throw new NoSuchElementException("There are no previous elements in Set");
        }
    }

    /**
     * Find the index of the element that would be return by a call to previous()
     * @return the index of the element that would be return by previous()
     * @throws java.util.NoSuchElementException if there are no more elements
     */
    public int previousIndex() {
        if (hasPrevious()) {
            // If the Set has previous elements, return the index of the previous element by calling indexOf()
            return thisSet.indexOf(previous());
        } else {
            throw new NoSuchElementException("There are no previous elements in Set");
        }
    }

    /**
     * Replace the element last returned by a call to next() or previous() with
     * a given object
     * @param item the object to replace the element with
     */
    public void set(E item) {
        // Remove the object at the current index
        thisSet.remove(index);
        // Add the specified object to the Set
        // The Set will handle sorting
        thisSet.add(item);
    }
}

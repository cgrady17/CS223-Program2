import java.util.ListIterator;

/**
 * Author: Connor P Grady
 * Born: 3/11/2015
 * Description: Iterator for Set class.
 */
public class SetIterator< E extends ListIterator<E>> {
    public boolean hasNext() {
        // Return true if has a next item
        return true;
    }

    public E next() {
        // Return next item;

        return null;
    }

    public int nextIndex() {
        // Return index of next item

        return 0;
    }

    public boolean hasPrevious() {
        // Return true if has a previous item
        return true;
    }

    public E previous() {
        // Return previous item

        return null;
    }

    public int previousIndex() {
        // Return index of previous index

        return -1;
    }

    public void set(E item) {
        // Replace current element with item
    }
}

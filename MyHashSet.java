//import java.util.LinkedList;
import java.util.Objects;
import java.util.Iterator;

public class MyHashSet<E>{

    // Instance variables
    private Object[] hashArray;
    private int size;
    private static final int INITIAL_CAPACITY = 10000;
    private DLList<E> dlList; // To store elements in a doubly-linked list format

    // Constructor
    public MyHashSet() {
        this.hashArray = new Object[1000000];
        this.size = 0;
        this.dlList = new DLList<>();
    }

    // Add element to the set
    public boolean add(E e) {
        int hash = e.hashCode();
        int index = hash;

        if (hashArray[index] == null) {
            hashArray[index] = e;
            dlList.add(e);
            size++;
            return true;
        } else if (hashArray[index].equals(e)) {
            // Element already exists, do nothing
            return false;
        }

        // Handle collision
        // We assume a simple hash function, more complex collision handling can be
        // implemented
        return false;
    }

    // Clear the set
    public void clear() {
        for (int i = 0; i < hashArray.length; i++) {
            hashArray[i] = null;
        }
        dlList.clear();
        size = 0;
    }

    // Check if the set contains an element
    public boolean contains(Object o) {
        E e = (E) o;
        int hash = e.hashCode();
        int index = hash % hashArray.length;

        return hashArray[index] != null && hashArray[index].equals(e);
    }
// Remove an element from the set
    public boolean remove(Object o) {
        E e = (E) o;
        int hash = e.hashCode();
        int index = hash % hashArray.length;

        if (hashArray[index] != null && hashArray[index].equals(e)) {
            hashArray[index] = null;
            dlList.remove(e);
            size--;
            return true;
        }
        return false;
    }

    // Get the size of the set
    public int size() {
        return size;
    }

    // Convert the set into a doubly-linked list
    public DLList<E> toDLList() {
        return dlList;
    }



}
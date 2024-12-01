
public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    public boolean add(E e) {
        Node<E> newNode = new Node<E>(e);
        Node<E> prev = tail.prev();
        prev.setNext(newNode);
        newNode.setPrev(prev);
        newNode.setNext(tail);
        tail.setPrev(newNode);
        size++;
        return true;
    }

    public void add(int i, E e) {
        Node<E> node = getNode(i);
        Node<E> newNode = new Node<E>(e);
        Node<E> prev = node.prev();
        prev.setNext(newNode);
        newNode.setPrev(prev);
        newNode.setNext(node);
        node.setPrev(newNode);
        size++;
    }

    public E get(int i) {
        return getNode(i).get();
    }

    public E remove(int i) {
        Node<E> node = getNode(i);
        Node<E> prev = node.prev();
        Node<E> next = node.next();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return node.get();
    }

    public boolean remove(E e) {
        Node<E> current = head.next(); 
        int i = 0;
        while (current != tail) {
            if(current.get().equals(e)){
                remove(i);
                return true;
            }
            current = current.next();
            i ++;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public E set(int i, E e) {
        Node<E> node = getNode(i);
        E oldVal = node.get();
        node.set(e);
        return oldVal;
    }
public String toString() {
        if (size == 0) {
            return "[]";
        }
        String s = "[";
        Node<E> current = head.next(); 
        while (current != tail) {
            s += current.get();
            if (current.next() != tail) {
                s += ", ";
            }
            current = current.next();
        }
        s += "]";
        return s;
    }

    public void clear(){
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<E> current;
        if (index < size / 2) {
            current = head.next();
            for (int i = 0; i < index; i++) {
                current = current.next();
            }
        } else {
            current = tail.prev();
            for (int i = size - 1; i > index; i--) {
                current = current.prev();
            }
        }
        return current;
    }
}

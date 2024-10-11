/**
 * DoublyLinkedList class represents a doubly linked list data structure.
 * @param <E> the type of elements stored in the list.
 */
public class DoublyLinkedList<E> {

    /**
     * Node class represents a node in the doubly linked list.
     * @param <F> the type of value stored in the node.
     */
    private static class Node<F> {
        F val;
        Node<F> prev, next;

        /**
         * Constructs a new node with the given value, previous, and next nodes.
         * @param v the value of the node.
         * @param p the previous node in the list.
         * @param n the next node in the list.
         */
        public Node(F v, Node<F> p, Node<F> n) {
            val = v;
            prev = p;
            next = n;
        }
    }

    private Node<E> first, last;
    private int size;

    /**
     * Constructs an empty DoublyLinkedList.
     */
    public DoublyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Returns the number of items in the list.
     * @return the number of items in the list.
     */
    public int size(){
        return size;
    }

    /**
     * Returns the first item in the list.
     * @return the first item in the list or null if the list is empty.
     */
    public E getFirst() {
        if(size > 0){
            return first.val;
        }
        return null;
    }

    /**
     * Returns the last item in the list.
     * @return the last item in the list or null if the list is empty.
     */
    public E getLast() {
        if(size > 0){
            return last.val;
        }
        return null;
    }

    /**
     * Adds the given item to the start of the list.
     * @param item the item to be added.
     */
    public void addFirst(E item) {
        Node<E> newNode = new Node<>(item, null, first);
        if(size > 0){
            first.prev = newNode;
        }
        if(size == 0){
            last = newNode;
        }
        first = newNode;
        size++;
    }

    /**
     * Adds the given item to the end of the list.
     * @param item the item to be added.
     */
    public void addLast(E item) {
        Node<E> newNode = new Node<>(item, last, null);
        if(size > 0){
            last.next = newNode;
        }
        if(size == 0){
            first = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * Returns and removes the first item in the list.
     * @return the first item in the list or null if the list is empty.
     */
    public E removeFirst() {
        if (size > 0) {
            E firstVal = first.val;
            first = first.next;
    
            if (first != null) {
                first.prev = null;
            } else {
                // If the size was 1, update last as well
                last = null;
            }
    
            size--;
            return firstVal;
        }
        return null;
    }

    /**
     * Returns and removes the last item in the list.
     * @return the last item in the list or null if the list is empty.
     */
    public E removeLast() {
        if (size > 0) {
            E lastVal = last.val;
            last = last.prev;
    
            if (last != null) {
                last.next = null;
            } else {
                first = null;
            }
    
            size--;
            return lastVal;
        }
        return null;
    }
}

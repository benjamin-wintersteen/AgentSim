/*
  Written by Benjamin Wintersteen

  Creates an LinkedList class.

  This is the framework for a structure of Nodes who will store data.
*/
import java.util.Iterator;    // defines the Iterator interface

/**
 * LinkedList class represents a basic linked list data structure.
 * @param <T> the type of elements stored in the linked list.
 */
public class LinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private int size; 
    private Node<T> tail;

    /**
     * Node class represents a node in the linked list.
     * @param <F> the type of data stored in the node.
     */
    private class Node<F> {
        F container;
        Node<F> next;

        public Node(F item) {
            container = item;
            next = null;
        }
        public F getData(){
            return this.container;
        }
        public void setNext (Node<F> n){
            next = n;
        }
        public Node<F> getNext(){
            return this.next;
        }

    }

    /**
     * LLIterator class represents an iterator for the linked list.
     */
    private class LLIterator implements Iterator<T>{
        Node<T> walker;

        public LLIterator(Node<T> head) {
            walker = head;
        }

        public T next() {
            T out = walker.container;
            walker = walker.next;
            return out;
        }

        public boolean hasNext() {
            return walker != null;
        }
        public void remove(){
            System.out.println("does nothing");
        }
    }
    // Returns a new iterator
    public Iterator<T> iterator() {
        return new LLIterator(head);
    } 

    /**
     * Constructs an empty linked list.
     */
    public LinkedList(){
        head = null;
        size = 0;
    }

    /**
     * Gets the number of elements in the linked list.
     * @return the number of elements in the linked list.
     */
    public int size(){
        return size;
    }

    /**
     * Clears the linked list.
     */
    public void clear(){
        head = null; 
        size = 0;
    }

    /**
     * Checks if the linked list is empty.
     * @return true if the linked list is empty, false otherwise.
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns a string representation of the linked list.
     * @return a string representation of the linked list.
     */
    public String toString(){
        Node<T> walker = head; 
        StringBuilder output = new StringBuilder();
        output.append("[");
        while (walker != null){
            output.append(walker.container + ", ");
            walker = walker.next;
        }
        output.append("]");
        return output.toString();
    }

    /**
     * Adds an item to the linked list at the specified index.
     * @param item the item to be added.
     */
    public void add(T item){
        this.add(0, item);
    }

    /**
     * Adds an item to the linked list at the specified index.
     * @param index the index at which the item will be added.
     * @param item the item to be added.
     */
    public void add(int index, T item) {
        if (index == 0) {
            Node<T> newNode = new Node<>(item);
            newNode.setNext(head);
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else if (index == size) {
            Node<T> newNode = new Node<>(item);
            newNode.setNext(null);
            tail.next = newNode;
            tail = newNode;
        } else {
            Node<T> walker = head;
            for (int i = 0; i < index - 1; i++) {
                walker = walker.next;
            }

            Node<T> newNode = new Node<>(item);
            newNode.setNext(walker.next);
            walker.next = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the first item from the linked list.
     * @return the first item in the linked list.
     */
    public T remove(){
        T returnValue = head.container;
        head = head.next;
        size--;
        return returnValue; 
    }

    /**
     * Removes and returns the item at the specified index from the linked list.
     * @param index the index of the item to be removed.
     * @return the item at the specified index.
     */
    public T remove(int index) {
        Node<T> walker = head;
        for (int i = 0; i < index - 1; i++) {
            walker = walker.next;
        }
        T out = walker.next.container;
        walker.next = walker.next.next;
        if (index == size - 1) {
            tail = walker;
        }

        size--;

        return out;
    }

    /**
     * Checks if the linked list is equal to another object.
     * @param o the object to be compared.
     * @return true if the linked list is equal to the object, false otherwise.
     */
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }
        // If I have reached this line, o must be a LinkedList
        LinkedList<?> oAsALinkedList = (LinkedList<?>) o;
        
        if (oAsALinkedList.size != this.size){
            return false;
        }
        Node<T> walker = head;
        for(Object b: oAsALinkedList){
            if (!walker.getData().equals(b)){
                return false;
            }
            walker = walker.getNext();
        }
        return true;
    }

    /**
     * Checks if the linked list contains a specific object.
     * @param o the object to be checked for existence in the linked list.
     * @return true if the linked list contains the object, false otherwise.
     */
    public boolean contains(Object o){
        Node<T> walker = head; 
        while (walker != null){
            if(walker.container.equals(o)){
                return true;
            }
            walker = walker.next;
        }
        return false;
    }

    /**
     * Gets the item at the specified index in the linked list.
     * @param index the index of the item to be retrieved.
     * @return the item at the specified index.
     */
    public T get(int index){
        Node<T> walker = head; 
        if (index <= size){
            for(int i = 0; i < index ; i++){
                walker = walker.next;
            }
        }
        return walker.container;
    }

    /**
     * Removes the specified item from the linked list.
     * @param item the item to be removed.
     * @return the removed item.
     */
    public T removeItem(T item) {
        Node<T> current = head;
        Node<T> previous = null;

        // Traverse the list to find the node with the specified item
        while (current != null && !current.container.equals(item)) {
            previous = current;
            current = current.next;
        }

        // If the item is found, remove the node
        if (current != null) {
            if (previous == null) {
                // If the node to remove is the head
                head = current.next;
                if (head == null) {
                    // If the list becomes empty after removal
                    tail = null;
                }
            } else {
                // If the node to remove is not the head
                previous.next = current.next;
                if (current.next == null) {
                    // If the node to remove is the tail
                    tail = previous;
                }
            }
            size--;
        }
        return current.container;
    }
}

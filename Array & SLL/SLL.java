import java.util.Comparator;

/**
 * A simple singly linked list (SLL) implementation with the ability to store elements of a generic type T.
 * Elements in the list are added and maintained in ascending order based on their natural ordering or a provided comparator.
 * @author aasher
 * @author sammy
 * @param <T> The type of elements stored in the singly linked list.
 */
public class SLL<T extends Comparable<T>> {

    private Node<T> head;        // The head of the singly linked list.
    private int size;            // The number of elements in the singly linked list.
    private Comparator<T> comparator;  // A comparator for custom element comparison.

    /**
     * Constructs an empty singly linked list with no external comparator.
     */
    public SLL() {
        head = null;
        size = 0;
        comparator = null;
    }

    /**
     * Constructs an empty singly linked list with a specified external comparator for custom element comparison.
     *
     * @param externalComp The comparator to use for element comparison.
     */
    public SLL(Comparator<T> externalComp) {
        head = null;
        size = 0;
        comparator = externalComp;
    }
    /**
     * Gets the head of the singly linked list.
     *
     * @return The head node of the singly linked list.
     */
    public Node<T> getHead() {
        return head;
    }

    public int size(){
        return size;
    }

    // public void printAllAvengers() {
    //     Node<T> current = head;
    //     while (current != null) {
    //         System.out.println(current.getData());
    //         current = current.getNext();
    //     }
    // }
    
    // public void printTopNAvengers(int n) {
    //     // Implement logic to print the top N avengers by frequency.
    // }
    
    // public void printTopNPerformers(int n) {
    //     // Implement logic to print the top N performers by frequency.
    // }
    
    // public void printAllMentionedAvengers() {
    //     // Implement logic to print all mentioned avengers in alphabetical order.
    // }
    

    /**
     * Compares two elements using either the natural ordering or the provided comparator, if available.
     *
     * @param o1 The first element to compare.
     * @param o2 The second element to compare.
     * @return A negative integer if o1 < o2, zero if o1 equals o2, or a positive integer if o1 > o2.
     */
    private int compare(T o1, T o2) {
        if (comparator == null)
            return o1.compareTo(o2);
        else
            return comparator.compare(o1, o2);
    }

    /**
     * Adds an element to the singly linked list while maintaining ascending order based on natural ordering or the provided comparator.
     *
     * @param n The element to add to the singly linked list.
     */
    public void addInOrder(T n) {
        Node<T> newNode = new Node<>(n);

        if (head == null || compare(n, head.getData()) <= 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null && compare(n, current.getNext().getData()) > 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }

        size++;
    }

    /**
     * Adds an element to the singly linked list while maintaining ascending order based on natural ordering or the provided comparator.
     *
     * @param n The element to add to the singly linked list.
     */
    public void add(T n) {
        addInOrder(n);
    }

    /**
     * Finds and returns the first occurrence of an element in the singly linked list.
     *
     * @param t The element to find.
     * @return The first occurrence of the element in the singly linked list, or null if not found.
     */
    public T find(T t) {
        Node<T> current = head;
        while (current != null) {
            if (compare(t, current.getData()) == 0) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Removes the first occurrence of an element from the singly linked list.
     *
     * @param t The element to remove.
     */
    public void remove(T t) {
        if (head == null) {
            return;
        }
        if (compare(t, head.getData()) == 0) {
            head = head.getNext();
            size--;
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (compare(t, current.getNext().getData()) == 0) {
                current.setNext(current.getNext().getNext());
                size--;
                return;
            }
            current = current.getNext();
        }
    }
}

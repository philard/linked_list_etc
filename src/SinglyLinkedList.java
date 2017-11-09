import java.util.NoSuchElementException;

public class SinglyLinkedList {
    SinglyLinkedNode head;
    int size;

    // Constructors
    public SinglyLinkedList() {
        size = 0;
    }


    public SinglyLinkedList(SinglyLinkedNode head) {
        this.head = head;
        size = 0;
    }


    public SinglyLinkedList(Object headData) {
        head = new SinglyLinkedNode(headData);
        size = 0;
    }

    // Methods
    private void checkElementIndex(int index) {
        if (index < 0 || size <= index)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    private void checkPositionIndex(int index) {
        if (index < 0 || size < index)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    public void add(Object data) {
        addFirst(data);
    }


    public void addFirst(Object data) {
        SinglyLinkedNode e = new SinglyLinkedNode(data);
        e.next = head;

        head = e;
        size++;
    }


    public void addLast(Object data) {
        if (head == null)
            addFirst(data);
        else {
            SinglyLinkedNode x = head;

            while (x.next != null)
                x = x.next;

            x.next = new SinglyLinkedNode(data);
            size++;
        }
    }


    public void add(int index, Object data) {
        checkPositionIndex(index);

        if (index == 0)
            addFirst(data);
        else {
            SinglyLinkedNode x = head;

            for (int i = 0; i < index - 1; i++)
                x = x.next;

            x.next = new SinglyLinkedNode(data, x.next);
            size++;
        }
    }


    public Object get(int index) {
        checkElementIndex(index);

        SinglyLinkedNode x = head;
        for (int i = 0; i < index; i++)
            x = x.next;

        return x.data;
    }


    public int size() {
        return size;
    }


    public Object remove() {
        return removeFirst();
    }


    public Object removeFirst() {
        if (size == 0)
            throw new NoSuchElementException();
        else {
            Object temp = head.data;
            head = head.next;
            size--;
            return temp;
        }
    }


    public Object removeLast() {
        if (size <= 1)
            return removeFirst();
        else {
            SinglyLinkedNode x = head;

            while (x.next.next != null)
                x = x.next;

            Object temp = x.next.data;
            x.next = null;
            size--;
            return temp;
        }
    }


    public void remove(int index) {
        checkElementIndex(index);

        if (index == 0)
            removeFirst();
        else {
            SinglyLinkedNode x = head;
            for (int i = 0; i < index - 1; i++)
                x = x.next;

            x.next = x.next.next;
            size--;
        }
    }


    public void remove(Object data) {
        removeFirst(data);
    }


    public void removeFirst(Object data) {
        SinglyLinkedNode x = head;

        for (int i = 0; i < size; i++, x = x.next)
            if (x.data.equals(data)) {
                remove(i);
                return;
            }
    }


    public void removeLast(Object data) {
        int lastOccurrence = -1;
        SinglyLinkedNode x = head;

        for (int i = 0; i < size; i++, x = x.next)
            if (x.data.equals(data))
                lastOccurrence = i;

        if (lastOccurrence != -1)
            remove(lastOccurrence);
    }


    // TODO: impvove this O(n^2) method
    public void removeAll(Object data) {
        SinglyLinkedNode x = head;

        for (int i = 0; i < size; x = x.next)
            if (x.data.equals(data))
                remove(i);
            else
                i++;
    }


    public void clear() {
        head = null;
        size = 0;
    }


    public Object set(int index, Object newData) {
        checkElementIndex(index);

        SinglyLinkedNode x = head;
        for (int i = 0; i < index; i++)
            x = x.next;

        Object temp = x.data;
        x.data = newData;
        return temp;
    }


    public boolean contains(Object o) {
        for (SinglyLinkedNode x = head; x != null; x = x.next) {
            if (x.data.equals(o)) {
                return true;
            }
        }
        return false;
    }


    public String toString() {
        if (head == null) {
            return "null";
        } else {
            StringBuilder stringView = new StringBuilder();

            // using StringBuilder inside loops is very efficient
            for (SinglyLinkedNode x = head; x != null; x = x.next)
                stringView.append(x.toNodeString());

            return stringView.toString();
        }
    }


    public String debugString() {
        return toString() + " (size: " + size + ")";
    }
}
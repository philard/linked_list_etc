import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomLL implements Iterable<Object>{

    private CustomNode start;
    private CustomNode end;

    public void add(Object value) {
        addLast(value);
    }

    public void addLast(Object value) {
        CustomNode newEnd = new CustomNode(value);
        if(end == null) { addFirst(value); }
        else {
            end.setNext(newEnd);
            end = newEnd;
        }
    }

    public void add(final int index, Object value) {
        if (index < 0) throw new IndexOutOfBoundsException();
        if (index == 0) addFirst(value);
        else {
            CustomNode atIndex = walkToNodeAtIndex(index - 1);
            CustomNode insertedNode = new CustomNode(value, atIndex.getNext());
            addToEndIfApplicable(atIndex, insertedNode);
            atIndex.setNext(insertedNode);
        }
    }

    private void addToEndIfApplicable(CustomNode atIndex, CustomNode insertedNode) {
        if(atIndex == end || atIndex.getNext() == null) {
            if (atIndex == end && atIndex.getNext() == null) {
                end = insertedNode;
            } else {
                throw new IllegalStateException("Linked List state corrupeted");
            }
        }
    }


    public Object get(final int index) {
        return walkToNodeAtIndex(index).getValue();
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<>();
        for (Object element:this) strings.add(element.toString());
        return "[" + String.join(", ", strings) + "]";
    }

    private CustomNode walkToNodeAtIndex(int index) {
        CustomNode step = start;
        for (int stepIndex = 0; stepIndex != index; stepIndex++){
            if(step == null || step.getNext() == null) throw new IndexOutOfBoundsException();
            step = step.getNext();
        }
        return step;
    }

    public Integer size() {
        int size = 0;
        for(Object value: this) size++;
        return size;
    }

    public Iterator<Object> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements java.util.Iterator<Object> {
        int currentIndex = 0;
        CustomNode current = CustomLL.this.start;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if(!hasNext()) throw new java.util.NoSuchElementException();
            currentIndex++;
            CustomNode toReturn = current;
            current = current.getNext();
            return toReturn.getValue();
        }
    }

    public void addFirst(Object value) {
        CustomNode newStart = new CustomNode(value);
        if(start == null) {
            start = newStart;
            end = newStart;
        } else {
            CustomNode oldStart = start;
            start = newStart;
            start.setNext(oldStart);
        }
    }

    public void removeFirst() {
        if (start == null) return;
        start = start.getNext();
    }

    public void removeLast() {
        if (end == null) return;
        if (start == end) {
            start = end = null;
            return;
        }
        CustomNode secondLast = getPrevious(end);
        secondLast.setNext(null);
        end = secondLast;
    }

    private CustomNode getPrevious(CustomNode end) {
        return end.getPrevious();
    }

    private boolean isEmpty() {
        return start == null & end == null;
    }

    public void set(int index, int value) {
        CustomNode toRemove = walkToNodeAtIndex(index);
        toRemove.setValue(value);
    }

    public void remove(int index) {
        CustomNode toRemove = walkToNodeAtIndex(index);
        CustomNode leftOfRemoved = walkToNodeAtIndex(index - 1);
        boolean isRemovingLast = toRemove.getNext() == null;
        if (index == 0) {
            removeFirst();
        } else if (isRemovingLast) {
            removeLast();
        } else {
            leftOfRemoved.setNext(leftOfRemoved.getNext().getNext());
        }
    }

    public void removeAll(Object valueToRemove) {
    }

    public void removeLast(Object valueToRemove) {

    }

    public void removeFirst(Object valueToRemove) {

    }

}

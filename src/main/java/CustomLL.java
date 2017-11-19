import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
            CustomNode cursor = walkToNodeAtIndex(index - 1);
            CustomNode insertedNode = new CustomNode(value, cursor.getNext());
            setEndIfAtEnd(cursor, insertedNode);
            cursor.setNext(insertedNode);
        }
    }

    private void setEndIfAtEnd(CustomNode cursor, CustomNode insertedNode) {
        if(cursor == end) end = insertedNode;
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

    @Override
    public Iterator<Object> iterator() {
        return new ValueIterator();
    }

    private class ValueIterator implements java.util.Iterator<Object> {
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
    private class NodeIterator implements java.util.ListIterator<CustomNode> {
        CustomNode lastReturned = null;//CustomLL.this.end;
        CustomNode cursor = CustomLL.this.start;

        @Override
        public boolean hasNext() {
            return (cursor != null && cursor.getNext() != null);
        }

        @Override
        public CustomNode next() {
            if(lastReturned == null) cursor = start;

            if (!hasNext()) throw new java.util.NoSuchElementException();
            cursor = cursor.getNext();
            return lastReturned = cursor;
        }

        @Override
        public boolean hasPrevious() {
            if(lastReturned == null) return hasNext();
            else return cursor != null && cursor.getPrevious() != null;
        }

        @Override
        public CustomNode previous() {
            if (!hasPrevious()) throw new java.util.NoSuchElementException();
            if(lastReturned == null) cursor = end;

            cursor = cursor.getPrevious();
            return lastReturned = cursor;
        }

        @Override
        public int nextIndex() {
            throw new NotImplementedException();
        }

        @Override
        public int previousIndex() {
            throw new NotImplementedException();
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }

        @Override
        public void set(CustomNode customNode) {
            throw new NotImplementedException();
        }

        @Override
        public void add(CustomNode customNode) {
            throw new NotImplementedException();
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

    public boolean isEmpty() {
        return start == null & end == null;
    }

    public void set(int index, int value) {
        CustomNode toRemove = walkToNodeAtIndex(index);
        toRemove.setValue(value);
    }

    public void remove(int index) {
        removeNode(walkToNodeAtIndex(index));
    }

    private void removeNode(CustomNode toRemove) {
        CustomNode leftOfRemoved = toRemove.getPrevious();
        boolean isRemovingLast = toRemove.getNext() == null;
        if (toRemove == start) {
            removeFirst();
        } else if (isRemovingLast) {
            removeLast();
        } else {
            leftOfRemoved.setNext(leftOfRemoved.getNext().getNext());
        }
    }

    private class DescendingIterator implements Iterator<CustomNode> {
        private final CustomLL.NodeIterator itr = new CustomLL.NodeIterator();
        public boolean hasNext() {
            return itr.hasPrevious();
        }
        public CustomNode next() {
            return itr.previous();
        }
        public void remove() {
            itr.remove();
        }
    }

    public void removeAll(Object valueToRemove) {
        NodeIterator it = new NodeIterator();
        Stream<CustomNode> filtered = filterNodes(it, matching(valueToRemove));
        filtered.forEach(this::removeNode);
    }

    public void removeLast(Object valueToRemove) {
        DescendingIterator it = new DescendingIterator();
        Stream<CustomNode> filtered = filterNodes(it, matching(valueToRemove));
        filtered.findFirst().ifPresent(this::removeNode);
    }

    public void removeFirst(Object valueToRemove) {
        NodeIterator it = new NodeIterator();
        Stream<CustomNode> filters = filterNodes(it, matching(valueToRemove));
        filters.findFirst().ifPresent(this::removeNode);
    }

    private Predicate<CustomNode> matching(Object valueToRemove) {
        return (node) -> node.getValue().equals(valueToRemove);
    }

    private Stream<CustomNode> filterNodes(Iterator<CustomNode> iterator, Predicate<CustomNode> isMatch) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator, Spliterator.ORDERED), false)
                .filter(isMatch);
    }
}

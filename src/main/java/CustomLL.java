import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomLL implements Iterable<Object>{

    CustomNode start;
    CustomNode end;

    public CustomLL(){
    }

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
            atIndex.setNext(insertedNode);
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

    public void set(int index, int value) {

    }

    public void removeFirst() {
        if (start == null) return;
        start = start.getNext();
    }

    public void removeLast() {
    }

    public void remove(int index) {

    }

    public void removeAll(Object valueToRemove) {
    }

    public void removeLast(Object valueToRemove) {

    }

    public void removeFirst(Object valueToRemove) {

    }

}

public class CustomLL {

    CustomNode start;
    CustomNode end;

    public CustomLL(){
    }

    public void add(Object value) {
        CustomNode oldEnd = end;
        CustomNode newEnd = new CustomNode(value);
        oldEnd.setNext(newEnd);
        end = newEnd;
    }

    public Object get(int index) {
        return new Object();
    }

    public Integer size() {
        return null;
    }

    public void add(int index, Object value) {
        addFirst(value);
    }

    public void addFirst(Object value) {

    }

    public void addLast(Object value) {
    }

    public void set(int index, int value) {

    }

    public void removeFirst() {

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

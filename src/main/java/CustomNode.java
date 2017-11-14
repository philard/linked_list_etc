public class CustomNode {

    private Object value;
    CustomNode next;
    CustomNode previous;

    public CustomNode(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public CustomNode getNext() {
        return next;
    }

    public void setNext(CustomNode next) {
        this.next = next;
    }

    public CustomNode getPrevious() {
        return previous;
    }

    public void setPrevious(CustomNode previous) {
        this.previous = previous;
    }
}

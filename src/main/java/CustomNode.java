public class CustomNode {

    private Object value;
    private CustomNode next;
    private CustomNode previous;

    public CustomNode(Object value) {
        this.value = value;
    }

    public CustomNode(Object value, CustomNode next) {
        this(value);
        this.setNext(next);
    }

    public Object getValue() {
        return value;
    }

    public CustomNode getNext() {
        return next;
    }

    public void setNext(CustomNode next) {
        this.next = next;
        if(next != null) next.setPrevious(this);
    }

    public CustomNode getPrevious() {
        return previous;
    }

    private void setPrevious(CustomNode previous) {
        this.previous = previous;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

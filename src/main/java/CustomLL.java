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
}

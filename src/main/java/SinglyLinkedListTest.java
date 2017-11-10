import org.junit.Before;
import org.junit.Test;

public class SinglyLinkedListTest {

    SinglyLinkedList list;

    @Before
    public void setup() {
        list = new SinglyLinkedList();
    }

    public static void main(String[] args) {
        new SinglyLinkedListTest().mainTest();
    }

    public void mainTest() {
        setup();

        testAdditions();
        list.add(3, 2);
        System.out.println(list);
        System.out.println(list.contains(0));
        System.out.println(list.contains(2));
        System.out.println(list.contains(5));
        System.out.println();

        list.addFirst("First");
        list.addLast("Last");
        System.out.println(list);

        list.set(0, "set");
        list.set(7, "set");
        System.out.println(list);


        list.removeFirst();
        list.removeLast();
        System.out.println(list);


        // Removes the item in the specified index
        list.remove(2);
        System.out.println(list);

        // Removes the first occurrence of the specified object
        list.remove(new Integer(1));
        System.out.println(list);

        list.removeFirst(2);
        System.out.println(list);

        list.removeLast(4);
        System.out.println(list);

        list.removeAll(5);
        System.out.println(list);

        list.remove();
        System.out.println(list);

        list.add("****");
        list.add("~~~~");
        list.add("....");
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        list.clear();
        System.out.println(list);
    }


    @Test
    public void testAdditions() {
        list.add(0);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        assert(list.size() == 5);
    }
}
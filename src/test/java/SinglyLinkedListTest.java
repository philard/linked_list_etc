import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SinglyLinkedListTest {

    SinglyLinkedList list;

    @Before
    public void setup() {
        list = new SinglyLinkedList();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void shouldAdd() {
        list.add(5);
        assertThat(list.size(), equalTo(5));
        assertThat(list.get(4), equalTo(0));
    }

    @Test
    public void testAddToThirdPosition() {
        list.add(3, 1);
        System.out.println(list);
        assert(list.contains(1));
    }

    @Test
    public void testAddFirst() {
        list.addFirst("First");
        Object o = list.get(0);
        assert o instanceof String && "First".equals(o);
    }

    @Test
    public void testAddLast() {
        list.addLast("Last");
        Object o = list.get(list.size - 1);
        assert o instanceof String && "Last".equals(o);
    }

    @Test
    public void testSet() {
        list.set(0, "set");
        list.set(3, "set");
        assert "set".equals(list.get(0));
        assert"set".equals(list.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBounds() {
        list.set(4, "set");
    }

    @Test
    public void testRemove() {
        list.removeFirst();
        list.removeLast();
        assert list.size == 2;
        assert list.get(0).equals(3);
        assert list.get(1).equals(2);
    }

    @Test
    public void shouldRemoveAtIndex() {
        list.remove(2);
        assertThat(list.size, equalTo(3));
        assertThat(list.get(2), equalTo(0));
    }

    public void mainTest() {
        setup();
        //testAdditions();
//        testAddToThirdPosition();
//        testAddFirst();
//        testAddLast();
//        testSet();
//        testSetOutOfBounds();
//        testRemove();


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
    }



    public static void main(String[] args) {
        new SinglyLinkedListTest().mainTest();
    }

}
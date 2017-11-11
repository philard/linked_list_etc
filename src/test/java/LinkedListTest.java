import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LinkedListTest
{
    public LinkedList<Integer> list;

    final Integer FIRST_INDEX = 0;

    @Before
    public void setup() {
        list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void shouldAdd() {
        int value = 5;
        list.add(value);
        assertThat(list.get(4), equalTo(value));
        assertThat(list.size(), equalTo(5));
    }

    @Test
    public void shouldAddToThirdPosition() {
        this.list.add(1, 88);
        assertThat(list.get(1), equalTo(88));
    }

    @Test
    public void shouldAddFirst() {
        int value = 0;
        list.addFirst(value);
        assertThat(value, equalTo(list.get(FIRST_INDEX)));
    }

    @Test
    public void shouldAddLast() {
        int value = 5;
        list.addLast(value);
        int lastIndex = list.size() - 1;
        assertThat(list.get(lastIndex), equalTo(value));
    }

    @Test
    public void shouldSet() {
        testSet(0, 0);
        testSet(3, 4);
    }

    public void testSet(int index, int value) {
        list.set(index, value);
        assertThat(value, equalTo(list.get(index)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenSetOutOfBounds() {
        int invalidIndex = 4;
        list.set(invalidIndex, 5);
    }

    @Test
    public void shouldRemove() {
        list.removeFirst();
        list.removeLast();
        assertThat(list.get(FIRST_INDEX), equalTo(2));
        assertThat(list.get(1), equalTo(3));
        assertThat(list.size(), equalTo(2));
    }

    @Test
    public void shouldRemoveAtIndex() {
        list.remove(1);
        assertThat(list.get(FIRST_INDEX), equalTo(1));
        assertThat(list.get(1), equalTo(3));
        assertThat(list.size(), equalTo(3));
    }

}

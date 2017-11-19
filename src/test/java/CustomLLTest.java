import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CustomLLTest {

    public CustomLL list;

    final Integer FIRST_INDEX = 0;

    @Before
    public void setup() {
        list = new CustomLL();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(0);
    }

    @Test
    public void shouldGetForGivenIndex() {
        System.out.println(list.get(0));
    }

    @Test
    public void shouldAdd() {
        int value = 5;
        list.add(value);
        Object valueAndEnd = list.get(list.size() - 1);
        assertThat(valueAndEnd, equalTo(value));
        assertThat(list.size(), equalTo(9));
        list.toString();
    }

    @Test
    public void shouldAddToSecondPosition() {
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
        assertThat("value at index", value, equalTo(list.get(index)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenSetOutOfBounds() {
        int invalidIndex = 99;
        list.set(invalidIndex, 5);
    }

    @Test
    public void shouldRemoveFirstAndLast() {
        list.removeFirst();
        list.removeLast();
        assertThat("size ", list.size(), equalTo(6));
        assertThat("first index ", list.get(FIRST_INDEX), equalTo(1));
        assertThat("last index ", list.get(list.size() - 1), equalTo(1));
    }

    @Test
    public void shouldRemoveAtIndex() {
        list.remove(1);
        assertThat(list.get(FIRST_INDEX), equalTo(0));
        assertThat(list.get(1), equalTo(2));
        assertThat(list.size(), equalTo(7));
    }

    @Test
    public void shouldRemoveAtIndexLast() {
        list.remove(6);
        assertThat(list.get(5), equalTo(2));
        assertThat(list.get(6), equalTo(0));
        assertThat(list.size(), equalTo(7));
    }

    @Test
    public void shouldRemoveAllOfGivenValue() {
        list.removeAll(1);
        assertThat(list.get(0), equalTo(0));
        assertThat(list.get(1), equalTo(2));
        assertThat(list.get(2), equalTo(3));
        assertThat(list.size(), equalTo(6));
    }

    @Test
    public void shouldRemoveLastOfGivenValue() {
        list.removeLast(1);
        assertThat(list.get(4), equalTo(3));
        assertThat(list.get(5), equalTo(2));
        assertThat(list.get(6), equalTo(0));
        assertThat(list.size(), equalTo(7));
    }

    @Test
    public void shouldRemoveFirstOfGivenValue() {
        list.removeFirst(1);
        assertThat("index 0", list.get(0), equalTo(0));
        assertThat("index 1", list.get(1), equalTo(2));
        assertThat("index 2", list.get(2), equalTo(3));
        assertThat("list size is", list.size(), equalTo(7));
    }

    @Test
    public void shouldDoNothingWhenRemovingNonexistentValue() {
        list.removeFirst(42);
        assertThat("index 0", list.get(0), equalTo(0));
        assertThat("index 1", list.get(1), equalTo(1));
        assertThat("index 2", list.get(2), equalTo(2));
        assertThat("list size is", list.size(), equalTo(8));
    }

    @Test
    public void shouldRemove() {
        list.remove(new Integer(1));
        assertThat("index 0", list.get(0), equalTo(0));
        assertThat("index 1", list.get(1), equalTo(2));
        assertThat("index 2", list.get(2), equalTo(3));
        assertThat("list size is", list.size(), equalTo(7));    }

}
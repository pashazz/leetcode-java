package leetcode.java.twonum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AddTwoNumbersTest {

    public static Stream<Arguments> getNumbers() {
        return Stream.of(
              //  Arguments.of(1,2,1+2),
                Arguments.of(11,21,11+21),
                Arguments.of(0, 5, 5),
                Arguments.of(45, 8239, 45+8239)
        );
    }

    @ParameterizedTest
    @MethodSource("getNumbers")
    void testAddTwoNumbers(int input1, int input2, int result ) {
        AddTwoNumbers a = new AddTwoNumbersReversedOrder();
        var l1 = ListNodeCreator.createFromNumber(input1);
        var l2 = ListNodeCreator.createFromNumber(input2);
        assertEquals(result, ListNodeCreator.createFromNode(a.addTwoNumbers(l1,l2)));
    }

    @Test
    void reverseListTest() {
        AddTwoNumbersRegularOrder ro = new AddTwoNumbersRegularOrder();
        ListNode list = ro.reverseList(ListNodeCreator.createFromNumber(1234));
        assertEquals(1, list.val);
        assertEquals(2, list.next.val);
        assertEquals(3, list.next.next.val);
        assertEquals(4, list.next.next.next.val);
    }

}
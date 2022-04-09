package leetcode.java.twonum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ListNodeCreatorTest {

    public static Stream<Arguments> makeArgumentsFromNumber() {
        return Stream.of(
                Arguments.of(1, new ListNode(1, null)),
                Arguments.of(10, new ListNode(0, new ListNode(1, null))),
                Arguments.of(156, new ListNode(6, new ListNode(5, new ListNode(1)))));
    }

    @ParameterizedTest
    @MethodSource("makeArgumentsFromNumber")
    void createFromOne(int input, ListNode expected) {
        assertEquals(expected, ListNodeCreator.createFromNumber(input));
    }

}
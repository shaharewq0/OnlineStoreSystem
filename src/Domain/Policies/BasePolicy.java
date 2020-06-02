package Domain.Policies;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * class that contains utils for the parsers on policies classes
 */

public class BasePolicy {
    protected static String REGEX = "#";

    protected static Stack<String> stringSplitToStack(String str, String regex) {
        List<String> lst = Arrays.asList(str.split(regex));
        Collections.reverse(lst);
        Stack<String> stack = new Stack<>();
        stack.addAll(lst);
        return stack;
    }
}

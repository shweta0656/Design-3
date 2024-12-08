import java.util.Iterator;
import java.util.Stack;

/*
TC:
Worst case: O(n), where n is depth of the list in the stack
Average case: O(1) for next() and hasNext()


SC:
Worst case: O(n), where n is depth of the list in the stack
Average case: O(1)

Lazy evaluation
 */
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator()); //pushing the iterator on nestedlist in constructor
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty())
        {
            if(!st.peek().hasNext()) {
                st.pop();
            }
            else{
                nextEl = st.peek().next();
                if(nextEl.isInteger()) {
                    return true;
                }
                else{
                    st.push(nextEl.getList().iterator());
                }
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
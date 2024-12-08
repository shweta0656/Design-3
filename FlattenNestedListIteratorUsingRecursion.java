import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
Let N be the total number of integers within the nested list, L be the total number of lists within the nested list,
and D be the maximum nesting depth (maximum number of lists inside each other).

Time complexity:

1) Constructor: O(N+L).

For each list within the nested list, there will be one call to flattenList(...). The loop within flattenList(...)
will then iterate n times, where n is the number of integers within that list. Across all calls to flattenList(...),
there will be a total of N loop iterations. Therefore, the time complexity is the number of lists plus the number
of integers, giving us O(N+L).


2) next(): O(1).

Getting the next element requires incrementing position by 1 and accessing an element at a particular index of the
integers list. Both of these are O(1) operations.

3) hasNext(): O(1).

Checking whether or not there is a next element requires comparing the length of the integers list to the position
variable. This is an O(1) operation.
==================================================================================================================
Space complexity : O(N+D).

The space of integers list. The length of this is O(N).

Each time we finish processing a nested list, flattenList(...) returns and a stack space is removed. The maximum
number of stack space on the runtime stack is the maximum nesting depth, D.

Because these two operations happen one-after-the-other, and either could be the largest, we add their time
complexities together giving a final result of O(N+D).
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
public class FlattenNestedListIteratorUsingRecursion implements Iterator<Integer> {

    //Eager Evaluation
    List<Integer> list;
    int idx;

    public FlattenNestedListIteratorUsingRecursion(List<NestedInteger> nestedList) {
        this.list = new ArrayList<>();
        this.idx = 0;
        dfs(nestedList);
    }

    private void dfs(List<NestedInteger> nestedList) { //not for user

        for(NestedInteger ne : nestedList) {
            if(ne.isInteger()) {
                list.add(ne.getInteger());
            }
            else {
                dfs(ne.getList());
            }
        }
    }

    @Override
    public Integer next() { //O(1)
        return list.get(idx++);
    }

    @Override
    public boolean hasNext() { //O(1)
        return idx < list.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

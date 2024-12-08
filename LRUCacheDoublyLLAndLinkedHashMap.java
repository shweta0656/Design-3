import java.util.HashMap;
/*
TC: O(1) for both get and put.

For get:

    Check if a key is in a hash map. This costs O(1).
    Get a node associated with a key. This costs O(1).
    Call remove and add. Both methods cost O(1).

For put:

    Check if a key is in a hash map. This costs O(1).
    If it is, we get a node associated with a key and call remove. Both cost O(1).
    Create a new node and insert it into the hash map. This costs O(1).
    Call add. This method costs O(1).
    If the capacity is exceeded, we call remove and delete from the hash map. Both cost O(1).

SC: O(n), where n is total number of nodes in cache, equal to capacity
*/
class LRUCacheDoublyLLAndLinkedHashMap {

    HashMap<Integer, Node> map;
    int capacity;
    Node head, tail; //dummy nodes

    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCacheDoublyLLAndLinkedHashMap(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public void removeNode(Node curr) {
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.next = null;
        curr.prev = null;
    }

    public void addToHead(Node curr) {
        curr.next = head.next;
        curr.prev = head;
        head.next.prev = curr;
        head.next = curr;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        Node node = map.get(key);

        removeNode(node);
        addToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        }
        else {
            if(map.size() == capacity) {
                Node lruNode = tail.prev;
                removeNode(lruNode);
                map.remove(lruNode.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

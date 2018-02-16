package Assignment2;

/**
 * Incredibly oversimplified LinkedList implementation.
 *
 * @author Bryn Meekel
 */
public class LinkedList {
    private int counter;
    private Node head;

    /**
     * returns the size of the Linked List
     * @return the size of the linked list.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Adds an object to the linked list at the end of the list.
     * @param data to add.
     */
    public void add(Object data) {
        if(head == null) {
            head = new Node(data);
        } else {
            Node tempNode = new Node(data);
            Node currentNode = head;
            if(currentNode!=null){
                while(currentNode.getNext()!=null) currentNode = currentNode.getNext();
                currentNode.setNext(tempNode);
            }
        }
        counter++;
    }

    /**
     * Adds an object to the linked list at the index provided.
     * @param data to add.
     * @param index at index.
     */
    public void add(Object data, int index) {
        Node tempNode = new Node(data);
        Node currentNode = head;
        if (head == null) head = tempNode;
        if(index == 0){
            tempNode.setNext(currentNode);
            head = tempNode;
        } else {
            if (currentNode != null)
                for (int iterator = 0; iterator < index && currentNode.getNext() != null; iterator++)
                    currentNode = currentNode.getNext();
            tempNode.setNext(currentNode.getNext());
            currentNode.setNext(tempNode);
        }
        counter++;
    }

    /**
     * retrieves the object at the index.
     * @param index to retrieve
     * @return an object at index.
     */
    public Object get(int index){
        if(index < 0) return null;
        Node currentNode = head;

        if(head != null){
            for(int iterator = 0; iterator < index; iterator++){
                if (currentNode.getNext() == null) return null;
                currentNode = currentNode.getNext();
            }
        }
        return currentNode.getData();
    }

    /**
     * changes the object at the index to the newly provided object.
     * @param data to add
     * @param index at index
     */
    public void set(Object data, int index){
        if(index < 0) return;
        Node currentNode = head;

        if(head != null){
            for(int iterator = 0; iterator < index - 1; iterator++){
                if (currentNode.getNext() == null) return;
                currentNode = currentNode.getNext();
            }
        }
        
        currentNode.setData(data);
    }

    /**
     * removes an object at an index
     * @param index at index
     */
    public void remove(int index){
        if (index < 1 || index > counter) return;
        Node currentNode = head;
        if(currentNode!= null){
            for(int iterator = 0; iterator < index; iterator++) {
                if (currentNode.getNext() == null) return;
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(currentNode.getNext().getNext());
            counter--;
        }
    }

    /**
     * Sorts the Linked List according to a lexicographic sort of each Object's toString function
     */
    public void InsertionSort() {
        Node currentNode = head;
        Node tailNode = null;
        while(currentNode != null && tailNode != head ) {
            Node next = currentNode;
            while(next.getNext() != tailNode) {
                if( 0 < next.getData().toString().compareTo(next.getNext().getData().toString())) {
                    Object temp = next.getData();
                    next.setData(next.getNext().getData());
                    next.getNext().setData(temp);
                }
                next = next.getNext();
            }
            tailNode = next;
            currentNode = head;
        }
    }

    /**
     * Private subclass to implement the concept of a Node on a list.
     */
    private class Node implements Comparable{
        Node next;
        Object data;

        Node(Object data) {
            next = null;
            this.data = data;
        }


        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public int compareTo(Object o) {
            return data.toString().compareTo(o.toString());
        }

    }
}

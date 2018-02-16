package Assignment2;


/**
 * Incredibly oversimplified Vector class. Only handles references to linked list, and does not include usual components
 * of a vector such as thread safety, and stack-like options such as pushing and popping.
 *
 * @author Bryn Meekel
 */
public class Vector {
    private int capacity;
    private int size;
    private LinkedList[] vector;
    private static final double INCREMENT_FACTOR = 2;

    Vector(){
        this.size = 0;
        this.capacity = 1;
        vector = new LinkedList[capacity];
    }

    /**
     * Save a reference to a LinkedList at the desired index.
     * @param list LinkedList reference.
     * @param index index.
     */
    public void store(LinkedList list, int index){
        try {
            if(index < size) set(list, index);
            else throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException indexOutOfBounds) {
            if (index >= 0) add(list, index);
            if (index >= capacity) throw new IndexOutOfBoundsException();
        }
    }

    /**
     * returns a LinkedList at an index
     * @param index index to retrieve from
     * @return a LinkedList
     */
    public LinkedList get(int index) {
        try {
            return vector[index];
        } catch (IndexOutOfBoundsException indexOutOfBounds) {
            return null;
        }
    }

    private void set(LinkedList list, int index){
        vector[index] = list;
    }

    private void add(LinkedList list, int index){
        LinkedList[] temporary;
        if(index >= 0 && index >= capacity) capacity = (int)(capacity * INCREMENT_FACTOR);
        temporary = new LinkedList[capacity];
        System.arraycopy(vector, 0, temporary, 0, vector.length);
        vector = new LinkedList[capacity];
        System.arraycopy(temporary, 0, vector, 0, vector.length);
        System.arraycopy(temporary, index, vector, index + 1, size - index);
        vector[index] = list;
        size++;
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return capacity;
    }
}

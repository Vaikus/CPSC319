package Assignment2;

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

    public void store(LinkedList list, int index){
        try {
            if(index < size) set(list, index);
            else throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException indexOutOfBounds) {
            if (index >= 0) add(list, index);
            if (index >= capacity) throw new IndexOutOfBoundsException();
        }
    }

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

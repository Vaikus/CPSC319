package Assignment2;

public class Vector {
    private int capacity;
    private int size;
    private LinkedList[] vector;
    private static final int INCREMENT_FACTOR = 5;

    Vector(){
        this.size = 0;
        this.capacity = INCREMENT_FACTOR;
        vector = new LinkedList[capacity];
    }

    public void store(LinkedList list, int index){
        try {
            set(list, index);
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

    public void set(LinkedList list, int index){
        vector[index] = list;
    }

    public void add(LinkedList list, int index){
        LinkedList[] temporary;
        if(index >= 0 && index >= capacity) capacity = capacity + INCREMENT_FACTOR;
        temporary = new LinkedList[capacity];
        for(int iterator = 0; iterator < vector.length; iterator++)
            temporary[iterator] = vector[iterator];
        vector = new LinkedList[capacity];
        for(int iterator = 0; iterator < vector.length; iterator++)
            vector[iterator] = temporary[iterator];
        for(int iterator = index; iterator < size; iterator++)
            vector[iterator+1] = temporary[iterator];
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

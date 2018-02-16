package Assignment2;

/**
 * A Quicksort algorithm implementation using an example file from Princeton University. The quicksort takes some optimization
 * decisions such as the implementation of optimal value selection for pivoting, as well as employs an Insertion sort for small
 * sub arrays. Furthermore, the recursion calls follow the format of calling recursively on items smaller than the pivot,
 * and then running a tail call on the higher sub array as suggested by Sedgewick.
 *
 * Reference: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/QuickBentleyMcIlroy.java.html
 *
 * @author Bryn Meekel based on work by Robert Sedgewick and Kevin Wayne
 */
public class QuickSort{


    //If the sub array is of size 47 or less it is better to use insertion sort instead
    //47 Is the value selected by the OpenJDK team, although I could not find any reason why.
    private static final int INSERTION_SORT_CUTOFF = 47;

    // cutoff to median-of-3 partitioning
    private static final int MEDIAN_OF_3_CUTOFF = 80;

    private QuickSort(){} //Stop Instantiation

    public static void sort(char[] arrayToSort){
        quickSort(arrayToSort, 0, arrayToSort.length - 1);
    }

    private static void quickSort(char[] arrayToSort, int low, int high){
        if (high <= low) return;
        int median;

        if (high - low <= INSERTION_SORT_CUTOFF) {
            insertionSort(arrayToSort);
            return;
        } else if (high - low <= MEDIAN_OF_3_CUTOFF) {
            median = medianOfArray(arrayToSort, low, high);
        } else {
            // Tukey's ninther implementation
            int partitioner = (high - low + 1)/8;
            int middle  = low + (high - low)/2;
            int firstThird = medianOfArray(arrayToSort, low, low+partitioner, low+partitioner+partitioner);
            int secondThird = medianOfArray(arrayToSort, middle - partitioner, middle, middle + partitioner);
            int thirdThird = medianOfArray(arrayToSort, high - partitioner - partitioner, high - partitioner, high);
            median = medianOfArray(arrayToSort, firstThird, secondThird, thirdThird);
        }
        swap(arrayToSort, low, median);

        int partition = partition(arrayToSort, low, high);

        // The following tree optimizes Tail Recursion for the JVM
        if (low < partition - 1) {
            quickSort(arrayToSort, low, partition - 1);
        } else if (high > partition) {
            quickSort(arrayToSort, partition + 1, high);
        }
    }

    /**
     * Example implementation of the Insertion sort
     * @param arrayToSort the array to be sorted
     * @author CPSC 319 on January 22nd, 2018
     */
    private static void insertionSort(char[] arrayToSort) {
        for(int iterator = 1, innerIterator; iterator < arrayToSort.length; iterator++){
            char temporary = arrayToSort[iterator];
            for(innerIterator = iterator;
                innerIterator > 0 && temporary < arrayToSort[innerIterator - 1];
                innerIterator--)
                arrayToSort[innerIterator] = arrayToSort[innerIterator - 1];
            arrayToSort[innerIterator] = temporary;
        }
    }

    /**
     * 3 Way partition designed by Bentley and McIlroy:
     * https://www.cs.princeton.edu/~rs/talks/QuicksortIsOptimal.pdf (Slide 9)
     * @param arrayToSort to be partitioned
     * @param low beginning of vector
     * @param high end of vector
     * @return an index for recursion
     */
    private static int partition(char[] arrayToSort, int low, int high){
        int i = low-1, j = high, p = low-1, q = high; char v = arrayToSort[high];

        while (true) {
            while (true) if (arrayToSort[++i] >= v) break;
            while (v < arrayToSort[--j]) if (j == low) break;
            if (i >= j) break;
            swap(arrayToSort, i, j);
            if (arrayToSort[i] == v) {
                p++;
                swap(arrayToSort, p, i);
            }
            if (arrayToSort[j] == v) {
                q--;
                swap(arrayToSort, q, j);
            }
        }
        swap(arrayToSort, i, high);
        return i;
    }

    private static int medianOfArray(char[] arrayToSort, int low, int high) {
        int middle = low + (high - low)/2;
        return ((arrayToSort[low] < arrayToSort[middle]) ?
                ((arrayToSort[middle] < arrayToSort[high]) ? middle : (arrayToSort[low] < arrayToSort[high]) ? high : low) :
                ((arrayToSort[high] < arrayToSort[middle]) ? middle : (arrayToSort[high] < arrayToSort[low]) ? high : low));
    }

    private static int medianOfArray(char[] arrayToSort, int low, int middle, int high) {
        return ((arrayToSort[low] < arrayToSort[middle]) ?
                ((arrayToSort[middle] < arrayToSort[high]) ? middle : (arrayToSort[low] < arrayToSort[high]) ? high : low) :
                ((arrayToSort[high] < arrayToSort[middle]) ? middle : (arrayToSort[high] < arrayToSort[low]) ? high : low));
    }

    private static void swap(char[] arrayToSort, int low, int high) {
        char temporary = arrayToSort[low];
        arrayToSort[low] = arrayToSort[high];
        arrayToSort[high] = temporary;
    }

    public static void sort(Vector vector){
        quickSort(vector, 0, vector.getSize() - 1);
    }

    private static void quickSort(Vector vector, int low, int high){
        if (high <= low) return;
        int median;

        if (high - low <= INSERTION_SORT_CUTOFF) {
            insertionSort(vector);
            return;
        } else if (high - low <= MEDIAN_OF_3_CUTOFF) {
            median = medianOfVector(vector, low, high);
        } else {
            // Tukey's ninther implementation
            int partitioner = (high - low + 1)/8;
            int middle  = low + (high - low)/2;
            int firstThird = medianOfVector(vector, low, low+partitioner, low+partitioner+partitioner);
            int secondThird = medianOfVector(vector, middle - partitioner, middle, middle + partitioner);
            int thirdThird = medianOfVector(vector, high - partitioner - partitioner, high - partitioner, high);
            median = medianOfVector(vector, firstThird, secondThird, thirdThird);
        }
        swap(vector, low, median);
        swap(vector, low, median);

        int partition = partition(vector, low, high);

        // The following branch optimizes Tail Recursion for the JVM
        if (low < partition - 1) {
            quickSort(vector, low, partition - 1);
        } else if (high > partition) {
            quickSort(vector, partition, high);
        }

    }

    /**
     * Example implementation of the Insertion sort
     * @param vector the vector to be sorted
     * @author CPSC 319 on January 22nd, 2018
     */
    private static void insertionSort(Vector vector) {
        for(int iterator = 1, innerIterator; iterator < vector.getSize(); iterator++){
            LinkedList temporary = vector.get(iterator);
            for(innerIterator = iterator;
                innerIterator > 0 &&
                       0 > temporary.get(0).toString().compareTo(vector.get(innerIterator - 1).get(0).toString());
                innerIterator--)
                vector.store(vector.get(innerIterator -1), innerIterator);
            vector.store(temporary, innerIterator);
        }
    }


    /**
     * 3 Way partition designed by Bentley and McIlroy:
     * https://www.cs.princeton.edu/~rs/talks/QuicksortIsOptimal.pdf (Slide 9)
     * @param vector to be partitioned
     * @param low beginning of vector
     * @param high end of vector
     * @return an index for recursion
     */
    private static int partition(Vector vector, int low, int high){
        int i = low-1, j = high, p = low-1, q = high; LinkedList v = vector.get(high);

        while (true) {
            while (true) if (0 <= vector.get(++i).get(0).toString().compareTo(v.get(0).toString())) break;
            while (0 > vector.get(--j).get(0).toString().compareTo(v.get(0).toString())) if (j == low) break;
            if (i >= j) break;
            swap(vector, i, j);
            if (0 == vector.get(i).get(0).toString().compareTo(v.get(0).toString())) {
                p++;
                swap(vector, p, i);
            }
            if (0 == vector.get(j).get(0).toString().compareTo(vector.get(low).get(0).toString())) {
                q--;
                swap(vector, q, j);
            }
        }
        swap(vector, i, high);
        return i;
    }

    private static int medianOfVector(Vector vector, int low, int high) {
        int middle = low + (high - low)/2;
        return ((0 > vector.get(low).get(0).toString().compareTo(vector.get(middle).get(0).toString())) ?
                ((0 > vector.get(middle).get(0).toString().compareTo(vector.get(high).get(0).toString())) ?
                        middle : (0 > vector.get(low).get(0).toString().compareTo(vector.get(high).get(0).toString()) ?
                        high: low)) :
                ((0 > vector.get(high).get(0).toString().compareTo(vector.get(middle).get(0).toString())) ?
                        middle: (0 > vector.get(high).get(0).toString().compareTo(vector.get(low).get(0).toString()) ?
                        high : low)));
    }

    private static int medianOfVector(Vector vector, int low, int middle, int high) {
        return ((0 > vector.get(low).get(0).toString().compareTo(vector.get(middle).get(0).toString())) ?
                ((0 > vector.get(middle).get(0).toString().compareTo(vector.get(high).get(0).toString())) ?
                        middle : (0 > vector.get(low).get(0).toString().compareTo(vector.get(high).get(0).toString()) ?
                        high: low)) :
                ((0 > vector.get(high).get(0).toString().compareTo(vector.get(middle).get(0).toString())) ?
                        middle: (0 > vector.get(high).get(0).toString().compareTo(vector.get(low).get(0).toString()) ?
                        high : low)));
    }

    private static void swap(Vector vector, int low, int high) {
        LinkedList temporary = vector.get(low);
        vector.store(vector.get(high), low);
        vector.store(temporary, high);
    }
}
package Assignment2;

public class Sort {


    /**
     * Implementation of the QuickSort Algorithm to sort strings
     * https://www.geeksforgeeks.org/quick-sort/
     * @param arrayToSort
     * @author GeeksForGeeks
     */
    public static void quickSort(char arrayToSort[]){
        quickSort(arrayToSort, 0, arrayToSort.length - 1);
    }

    private static void quickSort(char arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    private static int partition(char arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        char temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    /**
     * Implementation of the QuickSort Algorithm to sort a vector of LinkedLists with strings
     * https://www.geeksforgeeks.org/quick-sort/
     * @param arrayToSort
     * @author GeeksForGeeks
     */
    public static void quickSort(Vector arrayToSort){
        quickSort(arrayToSort, 0, arrayToSort.getSize() - 1);
    }

    private static void quickSort(Vector arr, int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    private static int partition(Vector arr, int low, int high)
    {
        char pivot = arr.get(high).get(0).toString().charAt(0);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j).get(0).toString().charAt(0) <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                LinkedList temp = arr.get(i);
                arr.store(arr.get(j), i);
                arr.store(temp,j);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        LinkedList temp = arr.get(i+1);
        arr.store(arr.get(high), i+1);
        arr.store(temp, high);

        return i+1;
    }
}
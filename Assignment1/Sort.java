package Assignment1;

public class Sort {
    /**
     * Example implementation of the Selection Sort
     * @param arrayToSort the array to be sorted
     * @author CPSC 319 on January 22nd, 2018
     */
    public static void selectionSort(int[] arrayToSort) {
        for(int itterator = 0; itterator < arrayToSort.length - 1; itterator++){
            int minimum = itterator;
            for (int internalItterator = itterator + 1;
                 internalItterator < arrayToSort.length;
                 internalItterator++)
                    if (arrayToSort[internalItterator] < arrayToSort[minimum])
                            minimum = internalItterator;
            int temporary = arrayToSort[minimum];
            arrayToSort[minimum] = arrayToSort[itterator];
            arrayToSort[itterator] = temporary;
        }
    }

    /**
     * Example implementation of the Insertion sort
     * @param arrayToSort the array to be sorted
     * @author CPSC 319 on January 22nd, 2018
     */
    public static void insertionSort(int[] arrayToSort) {
        for(int itterator = 1, innerItterator; itterator < arrayToSort.length; itterator++){
            int temporary = arrayToSort[itterator];
            for(innerItterator = itterator;
                innerItterator > 0 && temporary < arrayToSort[innerItterator - 1];
                innerItterator--)
                    arrayToSort[innerItterator] = arrayToSort[innerItterator - 1];
            arrayToSort[innerItterator] = temporary;
        }
    }

    /**
     * An implementation of the merge sort that optimizes to limit copy back
     * https://stackoverflow.com/questions/37916543/in-what-circumstances-mergesort-is-faster-than-selection-sort
     * @param a the array to be sorted
     * @author rcgldr at StackOverflow
     */
    public static void mergeSort(int a[]) {
        if (a.length < 2)
            return;
        int []b = new int[a.length];
        MergeSortAtoA(a, b, 0, a.length);
    }

    private static void MergeSortAtoA(int a[], int b[], int ll, int ee)
    {
        if (ee - ll > 1) {
            int rr = (ll + ee)>>1;          // midpoint, start of right half
            MergeSortAtoB(a, b, ll, rr);
            MergeSortAtoB(a, b, rr, ee);
            Merge(b, a, ll, rr, ee);        // merge b to a
        }
    }

    private static void MergeSortAtoB(int a[], int b[], int ll, int ee)
    {
        if (ee - ll > 1) {
            int rr = (ll + ee)>>1;          //midpoint, start of right half
            MergeSortAtoA(a, b, ll, rr);
            MergeSortAtoA(a, b, rr, ee);
            Merge(a, b, ll, rr, ee);        // merge a to b
        } else if ((ee - ll) == 1) {
            b[ll] = a[ll];
        }
    }

    private static void Merge(int []a, int []b, int ll, int rr, int ee) {
        int o = ll;                         // b[]       index
        int l = ll;                         // a[] left  index
        int r = rr;                         // a[] right index
        while(true){                        // merge data
            if(a[l] <= a[r]){               // if a[l] <= a[r]
                b[o++] = a[l++];            //   copy a[l]
                if(l < rr)                  //   if not end of left run
                    continue;               //     continue (back to while)
                while(r < ee){              //   else copy rest of right run
                    b[o++] = a[r++];
                }
                break;                      //     and return
            } else {                        // else a[l] > a[r]
                b[o++] = a[r++];            //   copy a[r]
                if(r < ee)                  //   if not end of right run
                    continue;               //     continue (back to while)
                while(l < rr){              //   else copy rest of left run
                    b[o++] = a[l++];
                }
                break;                      //     and return
            }
        }
    }


    /**
     * Example implementation of the QuickSort Algorithm
     * https://www.geeksforgeeks.org/quick-sort/
     * @param arrayToSort
     * @author GeeksForGeeks
     */
    public static void quickSort(int arrayToSort[]){
        quickSort(arrayToSort, 0, arrayToSort.length - 1);
    }

    private static void quickSort(int arr[], int low, int high)
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

    private static int partition(int arr[], int low, int high)
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
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }
}

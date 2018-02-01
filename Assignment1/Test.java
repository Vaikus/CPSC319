package Assignment1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws IOException {
        String sortingAlgorithm = args[0];
        int arraySize = Integer.parseInt(args[1]);
        int[] arrayToSort = new int[arraySize];
        String arrayFillMethod = args[2];
        String outputFile = args[3];

        switch (arrayFillMethod.toUpperCase()){
            case "RANDOM":
                for(int itterator = 0; itterator < arraySize; itterator++)
                    arrayToSort[itterator] = new Random().nextInt();
                break;
            case "ASCENDING":
                for(int itterator = 0; itterator < arraySize; itterator++) arrayToSort[itterator] = itterator;
                break;
            case "DESCENDING":
                for(int itterator = 0; itterator < arraySize; itterator++)
                    arrayToSort[arraySize - 1 - itterator] = itterator;
                break;
            default:
                throw new IllegalArgumentException("Argument three must be 'random', 'ascending', or 'descending'");
        }

        long startTime = System.nanoTime();
        switch (sortingAlgorithm.toUpperCase()){
            case "SELECTION":
                Sort.selectionSort(arrayToSort);
                break;
            case "INSERTION":
                Sort.insertionSort(arrayToSort);
                break;
            case "MERGE":
                Sort.mergeSort(arrayToSort);
                break;
            case "QUICK":
                Sort.quickSort(arrayToSort);
                break;
            default:
                throw new IllegalArgumentException("Argument one must be 'Selection', 'Insertion', 'Merge', or 'Quick'");
        }
        long endTime = System.nanoTime();

        System.out.println("The " + sortingAlgorithm + " algorithm sorted the " + arraySize + " "
                + arrayFillMethod + " integers in " + (endTime - startTime)/1000000 + " milliseconds.");

        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFile));
        for (int sortedElement: arrayToSort) {
            outputWriter.write(Integer.toString(sortedElement));
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}

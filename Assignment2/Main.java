package Assignment2;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("The file " + args[0] + " does not exist! If you think this is" +
                    " incorrect you should read the readme provided by this program" +
                    " to make sure you are using it properly. ");
        }
        String str;

        Vector linkedLists = new Vector();
        int itemsProcessed = 0;

        long startTime = System.nanoTime();
        try {
            while ((str = in != null ? in.readLine() : null) != null) {
                itemsProcessed++;
                boolean foundMatch = false;
                if (linkedLists.get(0) != null) {
                    foundMatch = testForAnagram(linkedLists, str);
                }
                if (!foundMatch) {
                    LinkedList list = new LinkedList();
                    list.add(str);
                    linkedLists.store(list, linkedLists.getSize());
                }
            }
            long beforeInsert = System.nanoTime();
            for (int iterator = 0; iterator < linkedLists.getSize(); iterator++)
                linkedLists.get(iterator).InsertionSort();
            long afterInsert = System.nanoTime();
            System.out.println("It took our InsertionSort algorithm " + (afterInsert - beforeInsert)/1000000 + " milliseconds " +
                    "to process " + itemsProcessed + " words.");

            long beforeQuick = System.nanoTime();
            QuickSort.sort(linkedLists);
            long afterQuick = System.nanoTime();
            System.out.println("It took our QuickSort algorithm " +(afterQuick - beforeQuick)/1000000 + " milliseconds " +
                    "to process " + linkedLists.getSize() + " LinkedLists.");
        } catch (IOException e) {
            System.out.print("The file " + args[0] + " was found, but you do not have read access to it! " +
                    "Please move this program and the file to be read to a directory where you have read access. " +
                    "If this problem persists, there is an error with the source file that will require you to generate a new copy");
        }
        long endTime = System.nanoTime();

        try {
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(args[1]));
            for(int iterator = 0; iterator < linkedLists.getSize(); iterator++) {
                for(int innerIterator = 0; innerIterator < linkedLists.get(iterator).getCounter(); innerIterator++)
                    outputWriter.write(linkedLists.get(iterator).get(innerIterator).toString() + " ");
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("The output file " + args[1] + " could not be written to. " +
                    "Please check that you have write access to this directory!");
        }

        System.out.println("The entire program took " +(endTime - startTime)/1000000 + " milliseconds to execute.");
    }

    private static boolean testForAnagram(Vector linkedLists, String str) {
        char[] testCase;
        char[] sortedInput = str.toCharArray();
        QuickSort.sort(sortedInput);
        for (int iterator = 0; iterator < linkedLists.getSize(); iterator++) {
            testCase = linkedLists.get(iterator).get(0).toString().toCharArray();
            if(testCase.length != sortedInput.length) continue;
            QuickSort.sort(testCase);
            if(Arrays.equals(testCase, sortedInput)){
                linkedLists.get(iterator).add(str);
                 return true;
            }
        }
        return false;
    }
}

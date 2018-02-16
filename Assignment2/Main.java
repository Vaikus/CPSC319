package Assignment2;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String str;

        Vector linkedLists = new Vector();

        long startTime = System.nanoTime();
        while((str = in != null ? in.readLine() : null) != null){
            char[] sortedInput = str.toCharArray();
            char[] testCase;
            QuickSort.sort(sortedInput);
            boolean foundMatch = false;
            if(linkedLists.get(0) != null) {
                for (int iterator = 0; iterator < linkedLists.getSize(); iterator++) {
                    testCase = linkedLists.get(iterator).get(0).toString().toCharArray();
                    if(testCase.length != sortedInput.length) continue;
                    QuickSort.sort(testCase);
                    if(Arrays.equals(testCase, sortedInput)){
                        linkedLists.get(iterator).add(str);
                        foundMatch = true;
                        break;
                    }
                }
            }
            if(!foundMatch) {
                LinkedList list = new LinkedList();
                list.add(str);
                linkedLists.store(list,linkedLists.getSize());
            }
        }
        for(int iterator = 0; iterator < linkedLists.getSize(); iterator++)
            linkedLists.get(iterator).InsertionSort();
        QuickSort.sort(linkedLists);
        long endTime = System.nanoTime();

        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(args[1]));

        for(int iterator = 0; iterator < linkedLists.getSize(); iterator++) {
            for(int innerIterator = 0; innerIterator < linkedLists.get(iterator).getCounter(); innerIterator++)
                outputWriter.write(linkedLists.get(iterator).get(innerIterator).toString() + " ");
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();

        System.out.println("The sorting algorithm took " +(endTime - startTime)/1000000 + " milliseconds.");
    }
}

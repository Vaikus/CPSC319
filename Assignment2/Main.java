package Assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("src/Assignment2/large.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String str;

        Vector linkedLists = new Vector();

        while((str = in != null ? in.readLine() : null) != null){
            LinkedList list = new LinkedList();
            char[] sortedInput = str.toCharArray();
            char[] testCase;
            Sort.quickSort(sortedInput);
            boolean foundMatch = false;
            if(linkedLists.get(0) != null) {
                for (int iterator = 0; iterator < linkedLists.getSize(); iterator++) {
                    testCase = linkedLists.get(iterator).get(0).toString().toCharArray();
                    Sort.quickSort(testCase);

                    if(testCase.length != sortedInput.length) continue;
                    foundMatch = true;
                    for(int checker = 0; checker < testCase.length; checker++)
                        if (testCase[checker] != sortedInput[checker]) foundMatch = false;
                    if (foundMatch) {
                        linkedLists.get(iterator).add(str);
                        foundMatch = true;
                        break;
                    }
                }
            }
            if(!foundMatch || linkedLists.get(0) == null) {
                list.add(str);
                linkedLists.add(list,linkedLists.getSize());
                System.out.println("Read new substring.. Now we have " + linkedLists.getSize());
            }
        }

        for(int iterator = 0; iterator < linkedLists.getSize(); iterator++) {
            linkedLists.get(iterator).InsertionSort();
        }

        Sort.quickSort(linkedLists);

        for(int iterator = 0; iterator < linkedLists.getSize(); iterator++) {
            System.out.println("Printing Linked List: " + iterator);
            for(int innerIterator = 0; innerIterator < linkedLists.get(iterator).getCounter(); innerIterator++)
                System.out.println(linkedLists.get(iterator).get(innerIterator));
            System.out.println();
        }
    }
}

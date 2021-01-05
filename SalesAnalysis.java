import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collections;

public class SalesAnalysis {

    // private ArrayList <String> sales = new ArrayList <String> ();
    private int numofSales = 0;
    private ArrayList<Integer> numLeadingdigits = new ArrayList<Integer>(Collections.nCopies(9, 0)); // set all the
                                                                                                     // value to 0
    private ArrayList<Double> frequency = new ArrayList<Double>(Collections.nCopies(9, 0.0)); // set all the values to
                                                                                              // 0.0
    private String inputFile = "sales.csv";
    private String outputFile = "output.txt";

    public static void main(String[] args) {
        SalesAnalysis sales = new SalesAnalysis();
        // sales.inputFile = "sales.csv";
        sales.loadFile();
        sales.setFrequency();
        sales.writeFile();
    }

    /**
     * @return true if file is loaded successfully
     */
    private boolean loadFile() {
        try {

            Scanner input = new Scanner(new FileInputStream(inputFile)); // read in file
            readFile(input); // read the file in line by line and set the attribute
            System.out.println("Sales successfully read from " + inputFile);
            System.out.println();
            return true;

        } catch (Exception e) {
            System.out.println("\nError: Could not open file\n");
            return false;
            // System.exit(0);
        }
    }

    /**
     * read sales file and call neccessayry function
     */
    public void readFile(Scanner input) {

        while (input.hasNextLine()) {
            String inputStrings = input.nextLine();

            if (numofSales != 0) {// first line is the title do it doesn't count
                String[] array = inputStrings.split(",");
                // int num = 0;
                int num = Integer.parseInt(array[1]); // convert String to int
                int leadingNum = leadingDigit(num);

                setNumOfLeadingDigits(leadingNum);
            }
            numofSales++; // keep track of the number of sales that are read in
        }
        numofSales -= 1; // actual number of sales is one less because file read in title
    }

    /**
     * For a given number, find the dirst digit
     * 
     * @param num
     * @return the leading digit
     */
    private int leadingDigit(int num) {
        while (num >= 10)
            num /= 10;
        return num;
    }

    /**
     * set the number of Leading digits ArrayList (called and values updated
     * continually)
     * 
     * @param leadingDigit
     */
    private void setNumOfLeadingDigits(int leadingDigit) {
        int index = leadingDigit - 1;
        int currentValue = numLeadingdigits.get(index).intValue(); // find the current number of leading Digit stored at
                                                                   // index
        int valueToStore = currentValue + 1;
        numLeadingdigits.set(index, valueToStore);
    }

    /**
     * determine the frequency (percentage) of each leading Number based on the num
     * of occurences
     */
    private void setFrequency() {
        for (int i = 0; i < numLeadingdigits.size(); i++) {
            int num = numLeadingdigits.get(i).intValue();

            double calculateFreq = 100.0 * num / numofSales;
            frequency.set(i, calculateFreq);
        }
    }

    /**
     * write the frequencies to a file and state if fraud occured or not
     */
    private void writeFile() {

        try {
            File tempFile = new File(outputFile); // if this file already exists
            tempFile.delete(); // then delete it cause we want to "rewrite file"

            for (int i = 0; i < frequency.size(); i++) {
                PrintWriter fileWriter = new PrintWriter(new FileOutputStream(outputFile, true)); // open files to
                                                                                                  // write.
                                                                                                  // true b/c appending
                                                                                                  // to list
                double freq = frequency.get(i).doubleValue();
                int leadingNum = i + 1;
                String str = String.format("%2.02f", freq);
                String outputString = Integer.toString(leadingNum) + "  -  " + str + "%" + "  -  " + fraudOrNot(freq);
                fileWriter.println(outputString);
                fileWriter.close();
            }
        } catch (Exception e) {
            System.out.println("\nError: Could not open file\n");
            System.exit(-1);
        }
    }

    /**
     * 
     * @param frequency
     * @return a string that state if fraud occurred or not
     */
    private String fraudOrNot(double frequency) {
        if (frequency > 29 && frequency < 32) {
            return "Fraud likely did not occurred";
        } else {
            return "Fraud might have not occurred";
        }

    }
}
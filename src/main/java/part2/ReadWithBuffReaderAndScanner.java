package part2;

import java.io.*;
import java.util.Scanner;

public class ReadWithBuffReaderAndScanner {

    public static void main(String[] args) {

        String text = "";
        //text = readFileWithBufferedReader("sourceFile");
        text = readFileWithScanner("sourceFile");
        writeToFile(text, "targetFile");
    }

    private static void writeToFile(String text, String filepath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write("Previous text will be erased. Use append(): \n" + text);
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred when trying to write to a file.");
            e.printStackTrace();
        }
    }

    public static String readFileWithBufferedReader(String filepath) {
        String textFromFile = "";
        try {                    // BufferedReader takes abstract Reader as argument.
            // various Reader's: BufferedReader, CharArrayReader, FilterReader, InputStreamReader, PipedReader, StringReader, URLReader
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            while ((line = reader.readLine()) != null) {
                textFromFile += line + "\n";
            }
            System.out.println(textFromFile);
            reader.close();

        } catch (IOException e) {
            System.out.println("An error occurred when trying to write to a file.");
            e.printStackTrace();

        }
        return textFromFile;
    }

    public static String readFileWithScanner(String path) {
        String textFromFile = "";
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                textFromFile += scanner.nextLine() + "\n";
            }
            System.out.println(textFromFile);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred when trying to read a file.");
            e.printStackTrace();
        }
        return textFromFile;

    }
}

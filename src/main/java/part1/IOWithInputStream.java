package part1;

import java.io.*;
import java.util.Formatter;

// it's for raw data, not for character streams; consider using FileWriter/Reader
public class IOWithInputStream {


    public static void readWriteWithFileInputStreamFileOutputStream() {
        // IMPOSSIBLE: InputStream is abstract: InputStream input = new InputStream();
        // common subclasses: FileIS, ObjectIS(serialization), AudioIS, ByteArrayIs

        // -------- WRITE to a new file with OutputStream -------------------
        System.out.println("---------- FileOutputStream(File): out.write(byte[] = str.getBytes()); -----------");
        // IMPORTANT FileOutputStream is meant for writing streams of >>>> raw bytes such as image data.<<<
        // For writing streams of characters, consider using FileWriter.
        try (OutputStream output = new FileOutputStream("data.txt");) {   // extension doesnt matter, we''ll get text anyway with out.write(byte[])
            byte[] dataArr = ("InputStream is an abstract class and the superclass of all classes " +
                    "representing an input stream of bytes.").getBytes();
            //System.out.println(dataArr[8]); // produces 181
            output.write(dataArr);
            //output.close(); not necessary -> try with resources
// ---------------------------------------------------------------------------------------------------
            // Quick way to create file and write to it
            Formatter formatter = new Formatter("formattersfile.txt");
            formatter.format("%s %d", "whatever", 100000);
            formatter.flush(); // necessary
            formatter.close(); // should be in finally or try with resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ---------------------------------------------------------------------------------------------------
        // PROBLEM: can't set FILE ENCODING!
        try(FileReader reader = new FileReader("zzzfile.xyz")) {
             FileWriter writer = new FileWriter("zzzfile.xyz", true); // turn on append mode
             writer.write("Welcome to FileWriter!!!");
            writer.append(" This is another sentences");
            writer.flush();
            writer.close();

            char[] array = new char[100]; // will print strange signs for null char in the array
            reader.read(array);
            System.out.println(array);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // & check its been correctly written
        // -------- READ with InputStream -------------------

        System.out.println("---------- FileInputStream(File) >>> byte[] input.readAllBytes() --------------------");
        try(InputStream input = new FileInputStream("data.txt");) {

            //System.out.println("Available bytes in the file: " + input.available()); // 185
            // PROBLEM: can't set FILE ENCODING! Using Apache Commons IOUtils to read file into byte array byte
            // [] filedata = IOUtils.toByteArray(fis);
            //Read more: https://javarevisited.blogspot.com/2014/08/2-examples-to-convert-byte-array-to-String-in-Java.html#ixzz7DtR0PTfC
            byte[] byteArray = input.readAllBytes();
            //System.out.println("byteArr: " + byteArray); // [B@7a0ac6e3
            System.out.println("" + new String(byteArray));
            //input_2.close(); not necessary

        } catch (IOException e) {
            e.printStackTrace();
        }

        String a = "a";
        byte[] arr = a.getBytes(); // stores Unicode values of chars
        System.out.println(arr[0]); // 97  -->> Decimal: 97	Octal: 0141
    }

}

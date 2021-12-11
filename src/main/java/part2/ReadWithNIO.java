package part2;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ReadWithNIO {

    public static void main(String[] args) throws IOException {
        write1();
    }

    private static void write1() throws IOException {
        // write from byte[]
        Files.write(Paths.get("yyy.bytes"), "byte[] here ".getBytes(StandardCharsets.UTF_8));
        // write from String
        Files.writeString(Paths.get("uuu.txt"), "String argument", StandardCharsets.UTF_8);

        // write from List as lines of text
        Path file1 = Paths.get("xxx.txt");
        Files.write(file1, List.of("1st line", "2nd line", "3rd!"), StandardCharsets.UTF_8); // default OpenOption: CREATE, TRUNCATE_EXISTING, and WRITE
        Files.write(file1, List.of("This", "will", "be appended"), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        // now read the lines
        System.out.println("A. ------> " + Files.readAllLines(file1, StandardCharsets.UTF_8));
        System.out.println("B. ------> ");
        Files.lines(file1).forEach(System.out::println);
        System.out.println("C. ------> " + Files.readString(file1));
/* 1st line
2nd line
3rd!
This
will
be appended*/

        ///////////// join nie dizała tutaj!!! /////////////////////////
        String str = Files.readString(file1);
        String[] arr = str.split("\n");
        String joined = String.join(" ", arr);
        System.out.println("444. ARRAY JOINED(): " + joined);

        List<String> list = Arrays.asList(arr); // [1st line, 2nd line, 3rd!, This, will, be appended]

        String list_joined = String.join("-", list);
        System.out.println("555. LIST JOINED(): " + list_joined);

        /* Not working
        Set<String> collection = new HashSet<>();
        collection.add("a");
        collection.add("b");
        collection.add("c");
        String setJoined = String.join("-" + collection);
        System.out.println("666. " + setJoined);*/

        // a to działa:
        String message = String.join("-", "Java", "is", "cool");
        System.out.println("777. " + message);



    }


}

package part1;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        try {
            //createDirsAndFiles();
            //deleteThoseCreated();

            //part1.DirsFilesPATH.testPATHmethods();
            readWith_3_methodsOfFiles();

            //part1.IOWithInputStream.readWriteWithFileInputStreamFileOutputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void createDirsAndFiles() throws IOException {
        //Paths.get(String...) get(URI)--------------------------------

        //1. the parent directories must already exist!!!
        Path alansmilesub = Paths.get("alan" + "smile" + "sub");
        Files.createDirectory(alansmilesub); // creates alansmilesub

        //2. creates NON-existent parent directories too. It's ok if brown/beige already exists - /blue will be added
        Files.createDirectories(Paths.get("brown" + File.separator + "beige" + File.separator + "blue"));
        Path abc = Paths.get("c/d/e");
        Files.createDirectories(abc);
        // 3. URI
        try {
            URI uri = new URI("clown");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println("Directories created!");

        // 4. create FILE - We add 2 Strings i Path: "c/d/e/" + file.txt (Without File.separator we will get "efile.txt"
        Files.createFile(Paths.get(abc + File.separator + "file.txt"));

        System.out.println("File file.txt created!");

    }

    private static void deleteThoseCreated() throws IOException {
        // DEletes files & dirs, so generally the last element of path!!!
        // If the folder is not empty a DirectoryNotEmptyException is thrown
        // NoSuchFileException if the dir desnt exist
        Files.delete(Paths.get("alan" + "smile" + "sub")); // treats this as a singl file & deletes

        // only blue is deleted - the last element
        Files.delete(Paths.get("brown" + File.separator + "beige" + File.separator + "blue"));
        assert Files.exists(Paths.get("brown" + File.separator + "beige")) : "Directory does not exist!!!";

        //Files.delete(Paths.get("c/d/e/file.txt"));

        //ALWAYS onl the LAST element is deleted!!!!!!!!!!
        //Files.delete(Paths.get("c/d/e")); // DirectoryNotEmptyException ->if  file/txt is in the lowest directory

        // try-with-resources to ensure that the stream's open directories are closed
        if (!Files.exists(Paths.get("c")))
            return;

        try (Stream<Path> walk = Files.walk(Paths.get("c"))) { // pass only the root of the tree
            // it will delete all name elements of the path c/d/e/file.txt
            /* Without Comparator sort() produces this ordering - just like for String(?!):
c
c/d
c/d/e
c/d/e/file.txt
and 4 java.nio.file.DirectoryNotEmptyException
* */
            walk.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    System.out.println(path); // prints c/d/e/file.txt, then c/d/e, c/d, c
                    Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void readWith_3_methodsOfFiles() throws IOException {
        System.out.println("---------List<String> Files.readAllLines----------------");
        List<String> lineList = null;
        lineList = Files.readAllLines(Paths.get("rootfile.txt"));

        AtomicInteger counter = new AtomicInteger();
        lineList.forEach(line -> {
                    System.out.println(counter.getAndIncrement() + ". " + line);
                }
        );

        /////////////////////////////////////////////////////////////////////////////////
        System.out.println("---------Files.readString----------------");
        String text = Files.readString(Paths.get("rootfile.txt"));
        System.out.println(text);

        /////////////////////////////////////////////////////////////////////////////////
        System.out.println("---------Stream<String> Files.lines----------------");
        Stream<String> lines = Files.lines(Paths.get("rootfile.txt")); // theres also Stream<String> str.lines() faster than This method provides better performance than split("\R")
        lines.filter(x -> x.contains("Path"))
                .map(x -> "?! " + x.toUpperCase())
                .forEach(System.out::println); // method reference has no ()->{}
        System.out.println("-------------------------");
    }

}

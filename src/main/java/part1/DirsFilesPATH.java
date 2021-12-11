package part1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class DirsFilesPATH {

    public static void testPATHmethods() {

        try {
            Path path1 = Paths.get("ddd" + File.separator + "eee" + File.separator + "fff" + File.separator + "ggg");
            Files.createDirectories(path1);
            Path path_0to2 = path1.subpath(0, 2);
            System.out.println(path_0to2);
            Path absolute = path1.toAbsolutePath();
            System.out.println(absolute);

            System.out.println("----------- ITERATING A ELEMENT PATH------------------");
            Iterator iterator = path1.iterator();
            while(iterator.hasNext()){
                //System.out.println(iterator.next());
                iterator.forEachRemaining(System.out::println);
            }
            System.out.println("----------- END ITERATING ------------------");

            Path qqq = Paths.get("ddd" + File.separator + "qqq");
            Files.createDirectories(qqq);
            Path qqqRelativeTopath1 = qqq.relativize(path1);
            System.out.println(qqqRelativeTopath1); // ../eee/fff/ggg

            Path path1RelativeToQqq = path1.relativize(qqq);//   ../../../qqq
            System.out.println(path1RelativeToQqq);

            Path fileToWrite = path1.resolve("tekstowa.file"); // ddd/eee/fff/ggg/tekstowa.file
            File file = fileToWrite.toFile();
            FileWriter writer = new FileWriter(file);
            writer.write("Turning Path to File is easy!");
            writer.flush();
            writer.close();

            FileReader reader = new FileReader(file);
            char[] textFromtekstowafile = new char[100];
            reader.read(textFromtekstowafile);
            System.out.println(textFromtekstowafile); // with byte[] you must new String(byteArr) to turn to String
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

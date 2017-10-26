import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * @Author Jack <e.kobets>
 * 10/25/17
 */
class FileWriterTest {
     static void main(String[] args) {

         File file = new File("src/new.txt")
         file.write "This is the first line\n"
         file << "This is the second line\n"

         List<String> fromFile =
                 Files.lines(Paths.get("src/new.txt"))
                         .collect(Collectors.toList());

         println(fromFile);
    }
}

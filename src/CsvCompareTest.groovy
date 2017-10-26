import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * @Author Jack <e.kobets>
 */
class CsvCompareTest {
    static void main(String[] args) {
        List<String> newCsvSeparatedStrings2 =
                Files.lines(Paths.get("/home/jack/IdeaProjects/untitled1/src/01--translation_241017 (1).csv"))
                        .collect(Collectors.toList());
        List<String> onlyKeysFromNewCsv = newCsvSeparatedStrings2.collect { i -> i.tokenize(';')[3] }.toList();

        List<String> oldCsvSeparatedStringsTranslationWebSpecific =
                Files.lines(Paths.get("/home/jack/IdeaProjects/untitled1/src/01--translation.web.specific.csv"))
                        .collect(Collectors.toList());
        List<String> onlyKeysFromTranslationWebSpecificCsv = oldCsvSeparatedStringsTranslationWebSpecific
                .collect { i -> i.tokenize(';')[3] }.toList();

        LinkedHashSet<String> duplicatedKeysWithTranslationSpecific =
                onlyKeysFromNewCsv.stream()
                        .filter { i -> onlyKeysFromTranslationWebSpecificCsv.contains(i) }
                        .collect(Collectors.toSet());

        println('Duplicated keys count: ' + duplicatedKeysWithTranslationSpecific.size())
//        duplicatedKeysWithTranslationSpecific.forEach { i -> println(i) };

        File file = new File("src/new.txt")
        //Загоняет строки в файл если есть пересечение с ключами в второй таблице
        LinkedHashSet<String> duplicatedKeysWithTranslationSpecific2 =
                newCsvSeparatedStrings2.stream()
                        .filter { i -> onlyKeysFromTranslationWebSpecificCsv.contains(i.tokenize(';')[3]) }
                        .map{ i -> file << i + ';W' + '\n'} //write current line to the file
                        .collect(Collectors.toSet());

//        duplicatedKeysWithTranslationSpecific2.forEach { i -> println(i) };
        Files.lines(Paths.get("src/new.txt"))
                .forEach{ i -> println(i)};
    }
}

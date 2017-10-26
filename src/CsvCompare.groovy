import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * @Author Jack <e.kobets>
 * 10/24/17
 */
class CsvCompare {
    static void main(String[] args) {

        String newCsv = "";

        String oldCsv = "";

        def newCsvSeparatedStrings = newCsv.trim().tokenize('\n');
        def onlyKeys = newCsvSeparatedStrings.collect { i -> i.tokenize(';')[3] }.join('\n');

        List<String> newCsvSeparatedStrings2 = Files.lines(Paths.get("/home/jack/IdeaProjects/untitled1/src/01--translation_241017 (1).csv"))
                .collect(Collectors.toList());
        def onlyKeysFromNewCsv = newCsvSeparatedStrings2.collect { i -> i.tokenize(';')[3] }.join('\n');

        List<String> oldCsvSeparatedStringsTranslationWebSpecific = Files.lines(Paths.get("/home/jack/IdeaProjects/untitled1/src/01--translation.web.specific.csv"))
                .collect(Collectors.toList());
        def onlyKeysFromTranslationWebSpecificCsv = oldCsvSeparatedStringsTranslationWebSpecific
                .collect { i -> i.tokenize(';')[3] }.join('\n');

        List<String> oldCsvSeparatedStringsTranslationSpecific =
                Files.lines(Paths.get("/home/jack/IdeaProjects/untitled1/src/01--translation.specific.csv"))
                .collect(Collectors.toList());
        def onlyKeysFromTranslationSpecificCsv = oldCsvSeparatedStringsTranslationWebSpecific
                .collect { i -> i.tokenize(';')[3] }.join('\n');

//        println(onlyKeysFromOldCsv)

        LinkedHashSet<String> duplicatedKeysWithTranslationSpecific = Stream.of(onlyKeysFromTranslationWebSpecificCsv)
                .filter{i -> oldCsvSeparatedStringsTranslationWebSpecific.contains(i) }
                .collect(Collectors.toSet());

        println('Duplicated keys: \n\n' + duplicatedKeysWithTranslationSpecific)
    }
}

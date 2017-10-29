import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * @Author Jack <e.kobets>
 * 10/29/17
 */
class AppendKeysThatNotContainsInTheOtherTables {
    static void main(String[] args) {

        String oldPath = "src/01--translation.specific.csv";
        String oldPath2 = "src/01--translation.web.specific.csv";
        String newPath = "src/01--translation_241017 (1).csv"

        appendRowsNotContainsInTheOtherTables(oldPath, oldPath2, newPath);
    }

    static File appendRowsNotContainsInTheOtherTables(final String pathToOldFile, final String pathToOldFile2,
                                                      final String pathToNewFile) {
        File result = new File("src/result.txt");

        List<String> oldCsvSeparatedStringsTranslationWebSpecific =
                Files.lines(Paths.get(pathToOldFile)).collect(Collectors.toList());

        List<String> oldCsvSeparatedStringsTranslationWebSpecificKeys = oldCsvSeparatedStringsTranslationWebSpecific.stream()
                .map { line -> line.tokenize(';')[3] }.collect(Collectors.toList());

        List<String> oldCsvSeparatedStringsTranslationSpecific =
                Files.lines(Paths.get(pathToOldFile2)).collect(Collectors.toList());

        List<String> oldCsvSeparatedStringsTranslationSpecificKeys = oldCsvSeparatedStringsTranslationSpecific.stream()
                .map { line -> line.tokenize(';')[3] }.collect(Collectors.toList());

        List<String> newCsvSeparatedStrings =
                Files.lines(Paths.get(pathToNewFile)).collect(Collectors.toList());
        Map<String, String> keysAndRowsFromNewCsv =
                newCsvSeparatedStrings.collectEntries {
                    [it.tokenize(';')[3], it]
                };

        newCsvSeparatedStrings.stream().filter { newRow ->
            !oldCsvSeparatedStringsTranslationWebSpecificKeys.contains(newRow.tokenize(';')[3]) &&
                    !oldCsvSeparatedStringsTranslationSpecificKeys.contains(newRow.tokenize(';')[3])
        }.forEach { filtered -> result.append(filtered + ';0' + '\n') };

        print "BEFORE METHOD END: \n\n" + result.text;
        return result;
    }
}

package MergeTranslationsbyTranslationKey

import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * @Author Jack <e.kobets>
 * 10/29/17
 */
class MergeTranslationsByTranslationKey {
     static void main(String[] args) {

         final String oldPath = "src/01--translation.specific.csv";
         final String newPath = "src/01--translation_241017 (1).csv"

         mergeCsvTranslationSpecific(oldPath, newPath);
    }

    static File mergeCsvTranslationSpecific(final String pathToOldFile, final String pathToNewFile) {
        File result = new File("src/result.txt");

        List<String> oldCsvSeparatedStringsTranslationSpecific =
                Files.lines(Paths.get(pathToOldFile)).collect(Collectors.toList());

        List<String> newCsvSeparatedStrings =
                Files.lines(Paths.get(pathToNewFile)).collect(Collectors.toList());
        Map<String, String> keysAndRowsFromNewCsv =
                newCsvSeparatedStrings.collectEntries {
                    [it.tokenize(';')[3], it]
                };

        oldCsvSeparatedStringsTranslationSpecific.stream().forEach { oldCsvRow ->
            keysAndRowsFromNewCsv.containsKey(oldCsvRow.tokenize(';')[3]) ?
                    result << (keysAndRowsFromNewCsv.get(oldCsvRow.tokenize(';')[3]) + ';0' + '\n') :
                    result << (oldCsvRow + '\n')
        };

        print "BEFORE METHOD END: \n\n" + result.text;
        return result;
    }
}

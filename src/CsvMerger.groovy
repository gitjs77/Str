import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * @Author Jack <e.kobets>
 * 10/26/17
 */
class CsvMerger {
    static void main(String[] args) {

        /**
         * Алгоритм:
         * Идем по исходному файлу, каждую строчку старого файла пишем в новый файл, до того момента, пока не встретится
         * дубликат ключей. В случае дубликата ключей, мы записываем файл строку из нового файла.
         * Таким образрм на выходе мы получаем на выходе файл исходной структуры, но с замененными записями имеющихся ключей
         * из нового файла.
         *
         * Декомпозиция:
         * 1. Получаем List<String> строк из старого файла.
         * 2. Получаем List<String> строк нового файла.
         * 3. Из List<String> строк формируем Map<String, String>, где
         * key - translation key, а value - вся строка их этого файла, где этот translation key
         * 4. Далее запускаем стрим по строкам исходного файла:
         * -Если translation key из текущей строки содержится в ключах Map<String, String>, полученной
         * из нового файла, то мы пишем в результирующий файл строку из нового файла, которая находится
         * в value данной Map<String, String>;
         * -Если translation key не содержится в ключах Map<String, String>, полученной
         * из нового файла, то мы пишем в результирующий файл текущую строку из старого файла.
         *
         * Таким образом на выходе мы получим результирующий файл, который содержит в себе
         * все строки из исходного старого файла, но при этом строки и совпадающими translation key
         * заменяются на строки из нового файла.
         */
        String oldPath = "src/01--translation.web.specific.csv";
        String newPath = "src/01--translation_241017 (1).csv"

        mergeCsvTranslationWebSpecific(oldPath, newPath);
    }

    static File mergeCsvTranslationWebSpecific(final String pathToOldFile, final String pathToNewFile) {
        File result = new File("src/result.txt");

        List<String> oldCsvSeparatedStringsTranslationWebSpecific =
                Files.lines(Paths.get(pathToOldFile)).collect(Collectors.toList());

        List<String> newCsvSeparatedStrings =
                Files.lines(Paths.get(pathToNewFile)).collect(Collectors.toList());
        Map<String, String> keysAndRowsFromNewCsv =
                newCsvSeparatedStrings.collectEntries {
                    [it.tokenize(';')[3], it]
                };
        //=======
        println(keysAndRowsFromNewCsv);
        println(keysAndRowsFromNewCsv.containsKey("translation_key"));
        println(keysAndRowsFromNewCsv.containsKey("translation_key"));
        println('======================')
        println(oldCsvSeparatedStringsTranslationWebSpecific.stream()
        .filter{ i -> keysAndRowsFromNewCsv.containsKey(i.tokenize(';')[3]) }.count());
        //=======

//        oldCsvSeparatedStringsTranslationWebSpecific.stream().forEach{ oldCsvRow ->
//            keysAndRowsFromNewCsv.containsKey(oldCsvRow.tokenize(';')[3]) ?
////                    {println "TRUE"; result.append (keysAndRowsFromNewCsv.get(oldCsvRow.tokenize(';')[3]) + '\n')}  :
////                    {println "FALSE"; result.append (oldCsvRow.toString() + '\n')}
//                    result.append (keysAndRowsFromNewCsv.get(oldCsvRow.tokenize(';')[3]) + '\n')  :
//                    result.append (oldCsvRow + '\n')
//        };

        /*oldCsvSeparatedStringsTranslationWebSpecific.stream().forEach{ oldCsvRow ->
           if(keysAndRowsFromNewCsv.containsKey(oldCsvRow.tokenize(';')[3])) {
               result.append(keysAndRowsFromNewCsv.get(oldCsvRow.tokenize(';')[3]) + '\n');
           }else {
               result.append(oldCsvRow + '\n');
           }
        };*/


//        for(String oldCsvRow : oldCsvSeparatedStringsTranslationWebSpecific){
//            if(keysAndRowsFromNewCsv.containsKey(oldCsvRow.tokenize(';')[3])) {
//                result << (keysAndRowsFromNewCsv.get(oldCsvRow.tokenize(';')[3]) + ';W' + '\n');
//            }else {
//                result << (oldCsvRow + '\n');
//            }
//        };

        oldCsvSeparatedStringsTranslationWebSpecific.stream().forEach { oldCsvRow ->
            keysAndRowsFromNewCsv.containsKey(oldCsvRow.tokenize(';')[3]) ?
                    result << (keysAndRowsFromNewCsv.get(oldCsvRow.tokenize(';')[3]) + ';W' + '\n') :
                    result << (oldCsvRow + '\n')
        };

        print "BEFORE METHOD END: \n\n" + result.text;
        return result;
    }
}

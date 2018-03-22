import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collector
import java.util.stream.Collectors

/**
 * @Author Jack <e.kobets>
 * 10/26/17
 */
class TestCreationMapFromList {
     static void main(String[] args) {


         List<String> oldCsvSeparatedStringsTranslationWebSpecific =
                 Files.lines(Paths.get("/home/jack/IdeaProjects/untitled1/src/01--translation.web.specific.csv"))
                         .collect(Collectors.toList());

         /*Converts list of Strings to Map where key is translation key,
         value - all translation row*/
         Map<String, String> onlyKeysFromNewCsv =
                 oldCsvSeparatedStringsTranslationWebSpecific.collectEntries {
                     [it.tokenize(';')[3], it]
                 };

         onlyKeysFromNewCsv.keySet().forEach{ i -> println(i)};
         println('======================');
         println(onlyKeysFromNewCsv.get('ps_Active'));
         println(onlyKeysFromNewCsv.keySet().contains('ps_Active'));

    }
}

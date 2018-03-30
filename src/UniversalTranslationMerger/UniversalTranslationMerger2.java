package UniversalTranslationMerger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Author Jack <e.kobets>
 */
public class UniversalTranslationMerger2 {
    public static void main(String[] args) throws IOException {

        final String outputFile = "src/UniversalTranslationMerger/newTranslationUa.csv";
        final String inputFile = "src/UniversalTranslationMerger/01--translation.mobile.csv";

        universalTranslationMerger(outputFile, inputFile,
                3, 3,
                2, 2);
    }


    public static File universalTranslationMerger(final String pathToOutputTranslationsFile, final String pathToInputTranslationFile,
                                                  final int outputFileTranslationKeyIndex, final int inputFileTranslationKeyIndex,
                                                  final int outputFileDataIndex, final int inputFileDataIndex) throws IOException {

        final File result = new File("src/UniversalTranslationMerger/result.txt");
        final FileWriter fw = new FileWriter(result);

        final List<String> outputTranslationsFileSeparatedStrings =
                Files.lines(Paths.get(pathToOutputTranslationsFile)).collect(Collectors.toList());

        final Map<String, String> translationKeyAndLineMapForOutputTranslationFile = outputTranslationsFileSeparatedStrings.stream()
                .collect(
                        Collectors.toMap(outFileRow -> outFileRow.split(";")[outputFileTranslationKeyIndex],
                                outFileRow -> outFileRow,
                                throwingMerger(),
                                LinkedHashMap::new
                        )
                );
        System.out.println("\ntranslationKeyAndLineMapForOutputTranslationFile size: " + translationKeyAndLineMapForOutputTranslationFile.size());
        translationKeyAndLineMapForOutputTranslationFile.forEach((k, v) -> System.out.println(k + ": " + v));

        final List<String> inputTranslationsFileSeparatedStrings =
                Files.lines(Paths.get(pathToInputTranslationFile))
                        .skip(2)
                        .collect(Collectors.toList());

        System.out.println("\ninputTranslationsFileSeparatedStrings size: " + inputTranslationsFileSeparatedStrings.size());
        System.out.println("inputTranslationsFileSeparatedStrings:");
        inputTranslationsFileSeparatedStrings.forEach(System.out::println);

        // Append to the result file first and second settings lines
        final List<String> settingsLineOnTheInputTranslationsFile = Files.lines(Paths.get(pathToInputTranslationFile))
                .limit(2)
                .collect(Collectors.toList());

        fw.append(settingsLineOnTheInputTranslationsFile.get(0)).append("\n")
                .append(settingsLineOnTheInputTranslationsFile.get(1)).append("\n");
        // End Append to the result file first and second settings lines

        inputTranslationsFileSeparatedStrings.forEach(inputFileString ->
                {
                    if (translationKeyAndLineMapForOutputTranslationFile.containsKey(inputFileString.split(";")[inputFileTranslationKeyIndex])) {
                        final String[] currentInputFileLineSeparatedBySemicolon = inputFileString.split(";");

                        currentInputFileLineSeparatedBySemicolon[inputFileDataIndex] =
                                translationKeyAndLineMapForOutputTranslationFile.get(inputFileString.split(";")[inputFileTranslationKeyIndex])
                                .split(";")[outputFileDataIndex];


                        Arrays.stream(currentInputFileLineSeparatedBySemicolon).forEach(el -> {
                            try {
                                fw.append(el).append(";");
                                fw.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        try {
                            fw.append("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                        else {
                        try {
                            fw.append(inputFileString).append("\n");
                            fw.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        return result;
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); };
    }

    public static List asList(Object value) {
        if (value == null) {
            return Collections.EMPTY_LIST;
        } else if (value instanceof List) {
            return (List)value;
        } else if (value.getClass().isArray()) {
            return Arrays.asList((Object[])((Object[])value));
        } else if (!(value instanceof Enumeration)) {
            return Collections.singletonList(value);
        } else {
            List answer = new ArrayList();
            Enumeration e = (Enumeration)value;

            while(e.hasMoreElements()) {
                answer.add(e.nextElement());
            }

            return answer;
        }
    }
}

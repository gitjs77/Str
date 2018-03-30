package UniversalTranslationMerger.TranslationGeneralMerger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author Jack <e.kobets>
 * 3/30/18
 */
public class TranslationMergeService {


    public File universalTranslationMerger(final String pathToOutputTranslationsFile, final String pathToInputTranslationFile,
                                           final int outputFileTranslationKeyIndex, final int inputFileTranslationKeyIndex,
                                           final int outputFileDataIndex, final int inputFileDataIndex) throws IOException {

        final File result = new File(pathToInputTranslationFile.substring(0, pathToInputTranslationFile.lastIndexOf(".")) + "_MERGED"
                + pathToInputTranslationFile.substring(pathToInputTranslationFile.lastIndexOf(".")));
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
                    if (inputFileString != null && !inputFileString.isEmpty()) {
                        if (translationKeyAndLineMapForOutputTranslationFile.containsKey(inputFileString.split(";")[inputFileTranslationKeyIndex])) {
                            final String[] currentInputFileLineSeparatedBySemicolon = inputFileString.split(";");

                            currentInputFileLineSeparatedBySemicolon[inputFileDataIndex] =
                                    translationKeyAndLineMapForOutputTranslationFile.get(inputFileString.split(";")[inputFileTranslationKeyIndex])
                                            .split(";")[outputFileDataIndex];

                            final AtomicInteger iterationCounter = new AtomicInteger();
                            Arrays.stream(currentInputFileLineSeparatedBySemicolon).forEach(el -> {
                                try {
                                    if (iterationCounter.get() == currentInputFileLineSeparatedBySemicolon.length - 1) {
                                        fw.append(el);
                                    } else {
                                        fw.append(el).append(";");
                                    }
                                    fw.flush();
                                    iterationCounter.getAndIncrement();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                            try {
                                fw.append("\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                fw.append(inputFileString).append("\n");
                                fw.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        return result;
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }

    public boolean validateMergedFileByRegexSignature(final String pathToFile, final String fileSignatureRegex) throws IOException {

        final AtomicInteger dontMatchRegexLineCount = new AtomicInteger();
        final boolean isInputFileCorrect = Files.lines(Paths.get(pathToFile))
                .skip(2)
                .allMatch(fileLine -> {
                    final boolean isFileLineMatchRegex = Pattern.compile(fileSignatureRegex).matcher(fileLine).matches();
                    if (!isFileLineMatchRegex) {
                        System.out.println("Don't match regex. Line: " + fileLine);
                        dontMatchRegexLineCount.getAndIncrement();
                    }

                    return isFileLineMatchRegex;
                });

        System.out.println("dontMatchRegexLineCount: " + dontMatchRegexLineCount.get());
        return isInputFileCorrect;
    }
}

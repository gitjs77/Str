//package UniversalTranslationMerger;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.*;
//import java.util.function.BinaryOperator;
//import java.util.stream.Collectors;
//
///**
// * @Author Jack <e.kobets>
// */
//public class UniversalTranslationMerger {
//    public static void main(String[] args) throws IOException {
//
//        final String outputFile = "src/UniversalTranslationMerger/newTranslationUa.csv";
//        final String inputFile = "src/UniversalTranslationMerger/01--translation.mobile.csv";
//
//        universalTranslationMerger(outputFile, inputFile,
//                3, 3,
//                2, 2);
//    }
//
//
//    public static File universalTranslationMerger(final String pathToOutputTranslationsFile, final String pathToInputTranslationFile,
//                                                  final int outputFileTranslationKeyIndex, final int inputFileTranslationKeyIndex,
//                                                  final int outputFileDataIndex, final int inputFileDataIndex) throws IOException {
//
//        File result = new File("src/UniversalTranslationMerger/result.txt");
//        FileWriter fw = new FileWriter(result);
//
//        List<String> outputTranslationsFileSeparatedStrings =
//                Files.lines(Paths.get(pathToOutputTranslationsFile)).collect(Collectors.toList());
//
//        List<String> inputTranslationsFileSeparatedStrings =
//                Files.lines(Paths.get(pathToInputTranslationFile)).collect(Collectors.toList());
//
//        System.out.println("\ninputTranslationsFileSeparatedStrings size: " + inputTranslationsFileSeparatedStrings.size());
//        System.out.println("inputTranslationsFileSeparatedStrings:");
//        inputTranslationsFileSeparatedStrings.forEach(System.out::println);
//
//        /*Map translationKeyAndLineMapForInputTranslationFile = inputTranslationsFileSeparatedStrings.stream()
//                .skip(2)
////                .collect(Collectors.toMap(inputFileRow -> inputFileRow.split(";")[inputFileTranslationKeyIndex], inputFileRow -> inputFileRow));
//                .collect(
//                        Collectors.toMap(inputFileRow -> asList(new StringTokenizer(inputFileRow, ";")).get(inputFileTranslationKeyIndex),
//                                inputFileRow -> inputFileRow,
//                                throwingMerger(),
//                                LinkedHashMap::new
//                        )
//                );*/
//        Map<String, String> translationKeyAndLineMapForInputTranslationFile = inputTranslationsFileSeparatedStrings.stream()
//                .skip(2)
////                .collect(Collectors.toMap(inputFileRow -> inputFileRow.split(";")[inputFileTranslationKeyIndex], inputFileRow -> inputFileRow));
//                .collect(
//                        Collectors.toMap(inputFileRow -> inputFileRow.split(";")[inputFileTranslationKeyIndex],
//                                inputFileRow -> inputFileRow,
//                                throwingMerger(),
//                                LinkedHashMap::new
//                        )
//                );
//        System.out.println("\ntranslationKeyAndLineMapForInputTranslationFile size: " + translationKeyAndLineMapForInputTranslationFile.size());
//        translationKeyAndLineMapForInputTranslationFile.forEach((k, v) -> System.out.println(k + ": " + v));
//
//        // Append to the result file first and second util lines
//        fw.append(inputTranslationsFileSeparatedStrings.get(0)).append("\n")
//                .append(inputTranslationsFileSeparatedStrings.get(1)).append("\n");
//
//        outputTranslationsFileSeparatedStrings.forEach(outputFileRow ->
//                {
//                    if (translationKeyAndLineMapForInputTranslationFile.containsKey(outputFileRow.split(";")[outputFileTranslationKeyIndex])) {
//                        String currentInputFileLine =  translationKeyAndLineMapForInputTranslationFile.get(outputFileRow.split(";")[outputFileTranslationKeyIndex]);
//                        String[] currentInputFileLineSeparatedBySemicolon = currentInputFileLine.split(";");
//
//                        currentInputFileLineSeparatedBySemicolon[inputFileDataIndex] = outputFileRow.split(";")[outputFileDataIndex];
//                        Arrays.stream(currentInputFileLineSeparatedBySemicolon).forEach(el -> {
//                            try {
//                                fw.append(el).append(";");
//                                fw.flush();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        });
//
//                        try {
//                            fw.append("\n");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
////                        else {
////                        String currentInputFileLine =  translationKeyAndLineMapForInputTranslationFile.get(outputFileRow.split(";")[outputFileTranslationKeyIndex]);
////                        String[] currentInputFileLineSeparatedBySemicolon = currentInputFileLine.split(";");
////
////                        Arrays.stream(currentInputFileLineSeparatedBySemicolon).forEach(el -> {
////                            try {
////                                fw.append(el).append(";");
////                                fw.flush();
////                            } catch (IOException e) {
////                                e.printStackTrace();
////                            }
////                        });
////
////                        try {
////                            fw.append("\n");
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                    }
//                }
//        );
//
//        return result;
//    }
//
//    private static <T> BinaryOperator<T> throwingMerger() {
//        return (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); };
//    }
//
//    public static List asList(Object value) {
//        if (value == null) {
//            return Collections.EMPTY_LIST;
//        } else if (value instanceof List) {
//            return (List)value;
//        } else if (value.getClass().isArray()) {
//            return Arrays.asList((Object[])((Object[])value));
//        } else if (!(value instanceof Enumeration)) {
//            return Collections.singletonList(value);
//        } else {
//            List answer = new ArrayList();
//            Enumeration e = (Enumeration)value;
//
//            while(e.hasMoreElements()) {
//                answer.add(e.nextElement());
//            }
//
//            return answer;
//        }
//    }
//}

package ChangeDataInCsv

import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * @Author Jack <e.kobets>
 */
class ChangeDataInBankCsv {
    static void main(String[] args) {

        final String inputFilePath = "src/03--bank.csv";
        final int columnForChangeIndex = 4;
        final String newValue = 'null';

        changeDataInCsvByCulumnIndex(inputFilePath, columnForChangeIndex, newValue);
    }

    static void changeDataInCsvByCulumnIndex(
            final String inputFilePath, final int columnForChangeIndex, final String newValue) {

        // Save every file line to list
        final List<String> fileLines = Files.lines(Paths.get(inputFilePath)).skip(3).collect(Collectors.toList());
//        fileLines.forEach{ it -> println(it)};
//        println("fileLines size: " + fileLines.size());
        // Save every file line to list of columns list
        final List<List<String>> fileLinesWithColumnList = fileLines.stream()
                .map { fileLine -> fileLine.tokenize(';') }
                .collect(Collectors.toList());
//        fileLinesWithColumnList.forEach{ it -> println(it)};
//        println("fileLinesWithColumnList size: " + fileLinesWithColumnList.size());

        // Rewrite value in the columnForChangeIndex for newValue on each elements in the fileLinesWithColumnListAfterChangeValueByIndex
        /*final List<List<String>> fileLinesWithColumnListAfterChangeValueByIndex = fileLinesWithColumnList.stream()
                .peek { it -> println(it.forEach { column -> print(column + ' | ') }) }
                .map { fileLineWithColumnList ->
            fileLineWithColumnList.size() >= columnForChangeIndex + 1 ? fileLineWithColumnList.set(columnForChangeIndex, newValue) : fileLineWithColumnList
        }
        .collect(Collectors.toList());*/

        List<List<String>> fileLinesWithColumnListAfterChangeValueByIndex = new ArrayList<>();
        for(List<String> columnList : fileLinesWithColumnList){

            if(columnList.size() >= columnForChangeIndex+1){
                columnList.set(columnForChangeIndex, newValue);
//                fileLinesWithColumnListAfterChangeValueByIndex.add(columnList);
            }
            fileLinesWithColumnListAfterChangeValueByIndex.add(columnList);
        }
        fileLinesWithColumnListAfterChangeValueByIndex.stream().forEach{ it -> println(it.forEach { column -> print(column + ' | ') }) };
        println(fileLinesWithColumnListAfterChangeValueByIndex.size());
//        fileLinesWithColumnListAfterChangeValueByIndex.forEach{ it -> println(it)};
//        println("fileLinesWithColumnListAfterChangeValueByIndex size: " + fileLinesWithColumnListAfterChangeValueByIndex.size());

//        fileLinesWithColumnListAfterChangeValueByIndex.forEach{ it -> println(it)};

        File result = new File("src/03--bank--fixed.csv");

        fileLinesWithColumnListAfterChangeValueByIndex
//                .forEach { it -> result << it != null ? it.stream().collect(Collectors.joining(';')) + '\n' : '' + '\n' };
                .forEach { it -> result << it.stream().collect(Collectors.joining(';')) + '\n'};
    }
}

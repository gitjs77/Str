package RecursiveFileSearch

import java.lang.reflect.Array
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * @Author Jack <e.kobets>
 */
class FileSesrchGroovy {
    static void main(String[] args) {
        File input = new File("src/data");
        File result = new File("src/result.txt");
        result <<'DIRRECTORY;FILE;ACTION' + '\n';

        method(input, result);
    }

    /*static void method() {
        File result = new File("src/result.txt");

        File input = new File("src/testdir");

        if (!input.isDirectory()) {
            Files.lines(Paths.get(input.getPath()))
                    .forEach { line ->
                if (line.contains('actionType')) {
                    result << (line.substring(line.indexOf('actionType'))) + '\n';
                }
            }
        } else {
            for (File file : input.listFiles()) {
                *//*result << file.name + ' : ' + file.getPath() + '\n';*//*
                List<String> linesList = Files.lines(Paths.get(file.getPath())).collect(Collectors.toList());
                linesList.stream().forEach { line ->
                    line.contains('actionType') ?
                            result << file.name + ' : ' + (line.substring(line.indexOf('actionType'))) + '\n' :
                            result << file.name + ' : ' + 'has a default action' + '\n'
                };
            }
        }
    }*/

//    static void method(final File input, final File result) {
//
//        if (!input.isDirectory()) {
//            writeResults(input, result);
//        } else {
//            for (File file : input.listFiles()) {
//                file.isDirectory() ? method(file, result) : writeResults(file, result);
//            }
//        }
//    }
//
//    static void writeResults(final File file, final File result){
//        List<String> linesList = Files.lines(Paths.get(file.getPath())).collect(Collectors.toList());
//        linesList.stream().forEach { line ->
//            line.contains('actionType') ?
//                    result << file.name + ' : ' + (line.substring(line.indexOf('actionType'))) + '\n' :
//                    result << file.name + ' : ' + 'has a default action' + '\n'
//        };
//    }

    /*static void method(final File input, final File result) {
        if (input.isDirectory()) {
            result << '\n    ' + input.name + ':' + '\n';
            for (File file : input.listFiles().sort()) {
                file.isDirectory() ? method(file, result) : writeResults(file, result);
            }
        } else {
            for (File file : input.listFiles()) {
                writeResults(input, result);
            }
        }
    }

    static void writeResults(final File file, final File result){
        List<String> linesList = Files.lines(Paths.get(file.getPath())).collect(Collectors.toList());
        linesList.stream().skip(1).limit(1).forEach { line ->
            line.contains('actionType') ?
                    result << file.name + ' in: ' + file.getPath() + ' : ' + (line.substring(line.indexOf('actionType'))) + '\n' :
                    result << file.name + ' in: ' + file.getPath() + ' : ' + 'has a default action' + '\n'
        };
    }*/

    static void method(final File input, final File result) {
        if (input.isDirectory()) {
//            result << '\n    ' + input.name + ':' + '\n';
            for (File file : input.listFiles().sort()) {
                file.isDirectory() ? method(file, result) : writeResults(file, result);
            }
        } else {
            for (File file : input.listFiles()) {
                writeResults(input, result);
            }
        }
    }

    static void writeResults(final File file, final File result){
        List<String> linesList = Files.lines(Paths.get(file.getPath())).collect(Collectors.toList());
        linesList.stream().skip(1).limit(1).forEach { line ->
            line.contains('actionType') ?
                    result <<file.getPath().substring(0, file.getPath().lastIndexOf('/')) + ';' + file.name + ';'  + (line.substring(line.indexOf('actionType'))) + '\n' :
                    result <<file.getPath().substring(0, file.getPath().lastIndexOf('/')) + ';' + file.name + ';'  + 'has a default action' + '\n'
        };
    }
}

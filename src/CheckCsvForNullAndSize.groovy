import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Paths

/**
 * @Author Jack <e.kobets>
 */
class CheckCsvForNullAndSize {
    static void main(String[] args) {

        final String csvForCheckPath = 'src/result.txt'
        println checkCsvForNullsCellAndCorrectStructure(csvForCheckPath);
    }

    static boolean checkCsvForNullsCellAndCorrectStructure(final String csvForCheckPath) {

        if (csvForCheckPath == null || csvForCheckPath.isEmpty()) {
            throw new InvalidPathException(csvForCheckPath, "Empty path or path == null");
        }

        return Files.lines(Paths.get(csvForCheckPath))
                .skip(2)
                .peek { row -> row.tokenize(';').size() == 5 ? println("All rows have correct size.") : println("size() == 5: " + row) }
                .peek { row ->
            row.tokenize(';').stream().anyMatch { cell -> !cell.isEmpty() && cell != null }.asBoolean() ?
                    println("All cell are correct") : println("Row with incorrect cell: " + row)
        }
        .allMatch { row ->
            row.tokenize(';').size() == 5 &&
                    row.tokenize(';').stream().anyMatch { cell -> !cell.isEmpty() && cell != null }.asBoolean()
        }
        .asBoolean();
    }
}

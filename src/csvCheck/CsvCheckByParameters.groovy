package csvCheck

import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Paths

/**
 * @Author Jack <e.kobets>
 */
class CsvCheckByParameters {
    static void main(String[] args) {

        final String pathToFile = "src/csvCheck/fake.csv"
        final int columnCount = 2;
        final int skipLines = 0;

        println checkCsvByParameters(pathToFile, columnCount, skipLines);
    }

    static boolean checkCsvByParameters(final String csvForCheckPath, final int columnCount, final int skipLines) {

        if (new File(csvForCheckPath).length() == 0) {
            throw new IncorrectDataException("File is empty.");
        }

        if (csvForCheckPath == null || csvForCheckPath.isEmpty()) {
            throw new InvalidPathException(csvForCheckPath, "Empty path or path == null");
        }

        if (columnCount == 0) {
            throw new IncorrectDataException("Incorrect column count: " + columnCount);
        }

        return Files.lines(Paths.get(csvForCheckPath))
                .skip(skipLines)
                .peek { row -> println("Skip count of lines: " + skipLines) }
                .peek { row -> row.tokenize(';').size() == columnCount ? print("All rows have correct size.") : println("size() != " + columnCount + ": " + row) }
                .peek { row ->
            row.tokenize(';').stream().anyMatch { cell -> !cell.isEmpty() && cell != null }.asBoolean() ?
                    println("All cells are correct") : println("Row with incorrect cell: " + row)
        }
        .allMatch { row ->
            row.tokenize(';').size() == columnCount &&
                    row.tokenize(';').stream().anyMatch { cell -> !cell.isEmpty() && cell != null }.asBoolean()
        }
        .asBoolean();
    }
}

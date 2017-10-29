import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Paths

/**
 * @Author Jack <e.kobets>
 * 10/29/17
 */
class CheckInputCsvFrombank {
    static void main(String[] args) {

        final String csvForCheckPath = 'src/01--translation_241017 (1).csv'
        println checkIncomingCsvFromBank(csvForCheckPath);
    }

    static boolean checkIncomingCsvFromBank(final String csvForCheckPath) {

        if (csvForCheckPath == null || csvForCheckPath.isEmpty()) {
            throw new InvalidPathException(csvForCheckPath, "Empty path or path == null");
        }

        return Files.lines(Paths.get(csvForCheckPath))
//                .skip(2)
                .peek { row -> row.tokenize(';').size() == 4 ? println("All rows have correct size.") : println("size() != 4: " + row) }
                .peek { row ->
            row.tokenize(';').stream().anyMatch { cell -> !cell.isEmpty() && cell != null }.asBoolean() ?
                    println("All cells are correct") : println("Row with incorrect cell: " + row)
        }
        .allMatch { row ->
            row.tokenize(';').size() == 4 &&
                    row.tokenize(';').stream().anyMatch { cell -> !cell.isEmpty() && cell != null }.asBoolean()
        }
        .asBoolean();
    }
}
